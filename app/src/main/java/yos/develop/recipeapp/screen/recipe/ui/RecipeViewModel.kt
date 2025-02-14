package yos.develop.recipeapp.screen.recipe.ui

import android.app.Application
import android.content.ContentValues
import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.ImageDecoder
import android.net.Uri
import android.os.Build
import android.os.Environment
import android.provider.MediaStore
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.internal.Contexts.getApplication
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import yos.develop.recipeapp.core.model.RecipeModel
import yos.develop.recipeapp.core.utils.Catalog
import yos.develop.recipeapp.core.utils.Constants
import yos.develop.recipeapp.core.utils.Resource
import yos.develop.recipeapp.screen.recipe.domain.RecipeRepository
import java.io.File
import java.io.FileOutputStream
import java.io.OutputStream
import java.util.UUID
import javax.inject.Inject

@HiltViewModel
class RecipeViewModel @Inject constructor(
    application: Application,
    private val recipeRepository: RecipeRepository
): AndroidViewModel(application) {

    var state by mutableStateOf(RecipeState())

    fun onRecipeEvent(event: RecipeEvent){
        when(event){
            RecipeEvent.ToggleShowDialogError -> {
                state = state.copy(isError = !state.isError)
            }
            is RecipeEvent.GetRecipe -> {
                getRecipe(idRecipe = event.idRecipe)
            }
            is RecipeEvent.AddUriImage -> {
                state = state.copy(imageUri = event.uri)
            }
            is RecipeEvent.ChangeInputs -> {
                changeInputs(type = event.type, newValue = event.newValue)
            }
            RecipeEvent.ChangeFavorite -> {
                state = state.copy(recipe = state.recipe.copy(favorite = !state.recipe.favorite))
            }
            RecipeEvent.SaveRecipe -> {
                saveRecipe()
            }
            RecipeEvent.ToggleSuccess -> {
                state = state.copy(successSaveRecipe = !state.successSaveRecipe)
            }
        }
    }



    private fun getRecipe(idRecipe: Int){
        if (idRecipe == Catalog.ID_FOR_ADD_RECIPE){
            state = state.copy(isLoadingDataInitial = false)
        }else{
            viewModelScope.launch(Dispatchers.IO) {
                delay(1000)
                val response = recipeRepository.getRecipe(idRecipe = idRecipe)

                withContext(Dispatchers.Main){
                    when(response){
                        is Resource.Failure -> {
                            state = state.copy(errorMessage = response.exception.localizedMessage ?: "---")
                            state = state.copy(isError = true)
                            state = state.copy(isLoadingDataInitial = false)
                        }
                        is Resource.Success -> {
                            state = state.copy(recipe = response.result)
                            state = state.copy(isLoadingDataInitial = false)
                        }
                    }
                }
            }
        }
    }

    private fun changeInputs(type: Int, newValue: String) {
        when(type){
            Catalog.TITLE_TEXT_FIELD ->{
                state = state.copy(title = newValue)
            }
            Catalog.DESCRIPTION_TEXT_FIELD ->{
                state = state.copy(description = newValue)
            }
            Catalog.PREPARATION_TIME_TEXT_FIELD ->{
                state = state.copy(preparationTime = newValue)
            }
        }
    }

    private fun saveRecipe() {
        val emptyFields = mutableListOf<String>()
        if (state.title.isBlank()) emptyFields.add(Constants.TITLE_LABEL)
        if (state.description.isBlank()) emptyFields.add(Constants.DESCRIPTION_LABEL)
        if (state.preparationTime.isBlank()) emptyFields.add(Constants.PREPARATION_TIME_LABEL)

        if (emptyFields.isNotEmpty()){
            state = state.copy(errorMessage = "${Constants.YOU_MUST_COMPLETE_THE_FOLLOWING_FIELDS} ${emptyFields.joinToString(", ")} ${Constants.TO_SAVE_THE_RECIPE}")
            state = state.copy(isError = true)
        }else{

            state = state.copy(enableButton = false)
            state = state.copy(isLoadingSaveRecipe = true)

            viewModelScope.launch(Dispatchers.IO) {

                state.imageUri?.let {
                    saveImageLocal()
                }

                delay(1000)
                val response = recipeRepository.insertRecipe(
                    recipe = RecipeModel(
                        title = state.title,
                        description = state.description,
                        preparationTime = state.preparationTime.toInt(),
                        favorite = state.recipe.favorite,
                        routeImage = state.routeImage
                    )
                )

                withContext(Dispatchers.Main){
                    when(response){
                        is Resource.Failure -> {
                            state = state.copy(errorMessage = response.exception.localizedMessage?:"---")
                            state = state.copy(isError = true)
                            state = state.copy(enableButton = true)
                            state = state.copy(isLoadingSaveRecipe = false)
                        }
                        is Resource.Success -> {
                            state = state.copy(enableButton = true)
                            state = state.copy(isLoadingSaveRecipe = false)
                            state = state.copy(successSaveRecipe = true)
                            clearFields()
                        }
                    }
                }
            }

        }
    }

    private suspend fun saveImageLocal() {
        withContext(Dispatchers.IO){
            val bitmap = getBitmapUri(uri = state.imageUri)

            bitmap?.let {
                saveImageToPNG(bitmap = it)
            }
        }

    }

    private fun getBitmapUri(uri: Uri?): Bitmap? {
        val context = getApplication<Application>().applicationContext
        if (uri == null){
            return null
        }
        return try {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
                val source = ImageDecoder.createSource(context.contentResolver, uri)
                ImageDecoder.decodeBitmap(source)
            } else {
                context.contentResolver.openInputStream(uri)?.use { inputStream ->
                    BitmapFactory.decodeStream(inputStream)
                }
            }
        } catch (e: Exception) {
            null
        }
    }

    private fun saveImageToPNG(
        bitmap: Bitmap,
    ) {
        val context = getApplication<Application>().applicationContext

        val fos: OutputStream
        val filePath: String
        val folder = Constants.RECIPE_APP_FOLDER
        val name = "IMG_${UUID.randomUUID()}"
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            val resolver = context.contentResolver
            val contentValues = ContentValues()
            contentValues.put(MediaStore.MediaColumns.DISPLAY_NAME, name)
            contentValues.put(MediaStore.MediaColumns.MIME_TYPE, "image/jpeg")
            contentValues.put(MediaStore.MediaColumns.RELATIVE_PATH, "DCIM/$folder")
            val imageUri =
                resolver.insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, contentValues)
            fos = resolver.openOutputStream(imageUri!!)!!
            filePath = imageUri.toString()
        } else {
            val imagesDir = Environment.getExternalStoragePublicDirectory(
                Environment.DIRECTORY_DCIM
            ).toString() + File.separator + folder

            val file = File(imagesDir)

            if (!file.exists()) {
                file.mkdir()
            }

            val image = File(imagesDir, "$name.jpeg")
            fos = FileOutputStream(image)
            filePath = image.toString()

        }
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, fos)

        state = state.copy(routeImage = filePath)

        fos.flush()
        fos.close()
    }

    private fun clearFields() {
        state = state.copy(imageUri = null)
        state = state.copy(routeImage = "")
        state = state.copy(title = "")
        state = state.copy(description = "")
        state = state.copy(preparationTime = "")
        state = state.copy(recipe = RecipeModel())
    }
}