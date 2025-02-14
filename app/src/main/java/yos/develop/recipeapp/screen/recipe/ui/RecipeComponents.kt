package yos.develop.recipeapp.screen.recipe.ui

import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.HideImage
import androidx.compose.material.icons.filled.PhotoLibrary
import androidx.compose.material.icons.filled.Timer
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import yos.develop.recipeapp.core.components.ButtonGlobal
import yos.develop.recipeapp.core.utils.Catalog
import yos.develop.recipeapp.core.utils.Constants

@Composable
fun BodyRecipe(
    modifier: Modifier = Modifier,
    state: RecipeState,
    onEvent:(RecipeEvent) -> Unit
) {
    Column(
        modifier = modifier
    ) {
        val pickMediaLauncher = rememberLauncherForActivityResult(
            contract = ActivityResultContracts.PickVisualMedia(),
            onResult = { uri ->
                uri?.let {
                    onEvent(RecipeEvent.AddUriImage(uri = it))
                }
            }
        )
        val focusManager = LocalFocusManager.current

        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier
                    .fillMaxWidth()
                    .height(250.dp)
                    .border(2.dp, MaterialTheme.colorScheme.primary)
                    .weight(1f),
                contentAlignment = Alignment.Center
            ) {
                this@Row.AnimatedVisibility(visible = state.imageUri != null) {
                    AsyncImage(
                        modifier = Modifier.fillMaxSize(),
                        model = state.imageUri,
                        contentDescription = null,
                        contentScale = ContentScale.Crop
                    )
                }

                this@Row.AnimatedVisibility(visible = state.imageUri == null) {
                    Icon(
                        imageVector = Icons.Filled.HideImage,
                        contentDescription = Constants.NOT_IMAGE_IC
                    )
                }
            }
            if(state.recipe.idRecipe == Catalog.ID_FOR_ADD_RECIPE){
                TextButton(
                    onClick = {
                        pickMediaLauncher.launch(PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.ImageOnly))
                    }
                ) {
                    Row {
                        Icon(
                            imageVector = Icons.Filled.PhotoLibrary,
                            contentDescription = Constants.SELECTED_IMAGE_IC
                        )
                        Text(
                            text = Constants.SELECT_IMAGE_TEXT_BUTTON,
                            maxLines = 1,
                            overflow = TextOverflow.Ellipsis
                        )
                    }
                }
            }else{
                Box(modifier = Modifier.fillMaxSize().weight(1f))
            }
        }

        Spacer(Modifier.height(16.dp))

        if (state.recipe.idRecipe == Catalog.ID_FOR_ADD_RECIPE) {
            OutlinedTextField(
                modifier = Modifier.fillMaxWidth(),
                value = state.title,
                onValueChange = { newValue ->
                    onEvent(RecipeEvent.ChangeInputs(type = Catalog.TITLE_TEXT_FIELD, newValue = newValue))
                },
                label = {
                    Text(
                        text = Constants.TITLE_LABEL
                    )
                },
                maxLines = 1,
                singleLine = true,
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Text,
                    imeAction = ImeAction.Next,
                    capitalization = KeyboardCapitalization.Sentences
                ),
                keyboardActions = KeyboardActions(
                    onNext = {
                        focusManager.moveFocus(FocusDirection.Next)
                    }
                )
            )
        }else{
            Text(
                text = state.recipe.title,
                style = MaterialTheme.typography.titleLarge,
                color = MaterialTheme.colorScheme.onSurface
            )
        }

        Spacer(Modifier.height(16.dp))

        if (state.recipe.idRecipe == Catalog.ID_FOR_ADD_RECIPE){

            OutlinedTextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(150.dp),
                value = state.description,
                onValueChange = { newValue ->
                    onEvent(RecipeEvent.ChangeInputs(type = Catalog.DESCRIPTION_TEXT_FIELD, newValue = newValue))
                },
                label = {
                    Text(
                        text = Constants.DESCRIPTION_LABEL
                    )
                },
                maxLines = 5,
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Text,
                    imeAction = ImeAction.Next,
                    capitalization = KeyboardCapitalization.Sentences
                ),
                keyboardActions = KeyboardActions(
                    onNext = {
                        focusManager.moveFocus(FocusDirection.Next)
                    }
                )
            )
        } else{
            Text(
                text = state.recipe.description,
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurface
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(
            value = state.preparationTime,
            onValueChange = { newValue ->
                if (newValue.all { it.isDigit() }) {
                    onEvent(RecipeEvent.ChangeInputs(type = Catalog.PREPARATION_TIME_TEXT_FIELD, newValue = newValue))
                }
            },
            label = { Text(Constants.PREPARATION_TIME_LABEL) },
            keyboardOptions = KeyboardOptions.Default.copy(
                keyboardType = KeyboardType.Number
            ),
            singleLine = true,
            trailingIcon = {
                Row {
                    Icon(
                        imageVector = Icons.Filled.Timer,
                        contentDescription = null
                    )
                    Spacer(modifier = Modifier.width(4.dp))
                    Text(
                        modifier = Modifier.padding(end = 8.dp),
                        text = "min.",
                        style = MaterialTheme.typography.labelLarge.copy(fontWeight = FontWeight.Bold)
                    )
                }

            }
        )

        Spacer(modifier = Modifier.height(32.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = if(state.recipe.favorite) Constants.FAVORITE else Constants.ADD_FAVORITE,
                style = MaterialTheme.typography.titleLarge,
                color = MaterialTheme.colorScheme.onSurface
            )
            Spacer(modifier = Modifier.width(16.dp))
            IconButton(
                onClick = {
                    onEvent(RecipeEvent.ChangeFavorite)
                }
            ) {
                Icon(
                    modifier = Modifier.size(40.dp),
                    imageVector = if(state.recipe.favorite) Icons.Filled.Favorite else Icons.Filled.FavoriteBorder,
                    contentDescription = Constants.FAVORITE_IC,
                    tint = MaterialTheme.colorScheme.primary
                )
            }
        }

        Spacer(modifier = Modifier.height(32.dp))

        if (state.recipe.idRecipe == Catalog.ID_FOR_ADD_RECIPE){
            ButtonGlobal(
                modifier = Modifier.align(Alignment.CenterHorizontally),
                textButton = Constants.SAVE_RECIPE_TEXT_BUTTON,
                enable = state.enableButton,
                isLoading = state.isLoadingSaveRecipe,
                onClick = {
                    onEvent(RecipeEvent.SaveRecipe)
                }
            )
            Spacer(modifier = Modifier.height(32.dp))
        }
    }
}