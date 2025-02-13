package yos.develop.recipeapp.screen.recipe.ui

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import yos.develop.recipeapp.core.utils.Catalog
import yos.develop.recipeapp.core.utils.Resource
import yos.develop.recipeapp.screen.recipe.domain.RecipeRepository
import javax.inject.Inject

@HiltViewModel
class RecipeViewModel @Inject constructor(
    private val recipeRepository: RecipeRepository
): ViewModel() {

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
}