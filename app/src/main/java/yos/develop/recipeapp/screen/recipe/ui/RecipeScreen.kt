package yos.develop.recipeapp.screen.recipe.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import yos.develop.recipeapp.core.components.DialogError
import yos.develop.recipeapp.core.components.LoadingScreen
import yos.develop.recipeapp.core.components.TopBarGlobal
import yos.develop.recipeapp.core.utils.Catalog
import yos.develop.recipeapp.core.utils.Constants

@Composable
fun RecipeScreen(
    state: RecipeState,
    idRecipe: Int,
    onEvent:(RecipeEvent) -> Unit,
    onBack:() -> Unit
) {

    LaunchedEffect(key1 = true) {
        onEvent(RecipeEvent.GetRecipe(idRecipe = idRecipe))
    }

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            TopBarGlobal(
                text = if (idRecipe == Catalog.ID_FOR_ADD_RECIPE) Constants.ADD_RECIPE else Constants.DETAIL_RECIPE,
                showBack = true,
                onClick = onBack
            )
        }
    ) { paddingValues ->

        Column(
            modifier = Modifier
                .padding(paddingValues)
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.surface)
                .verticalScroll(rememberScrollState())
        ) {
            if(state.isLoadingDataInitial){
                LoadingScreen(
                    modifier = Modifier.fillMaxSize()
                )
            }else{
                BodyRecipe(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(16.dp),
                    state = state,
                    onEvent = onEvent
                )
            }
        }
    }

    DialogError(
        isError = state.isError,
        errorMessage = state.errorMessage,
        onDismiss = {
            onEvent(RecipeEvent.ToggleShowDialogError)
        }
    )
}