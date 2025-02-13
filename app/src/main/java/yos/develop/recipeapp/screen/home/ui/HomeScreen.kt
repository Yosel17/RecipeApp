package yos.develop.recipeapp.screen.home.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import yos.develop.recipeapp.core.components.DialogError
import yos.develop.recipeapp.core.components.EmptyList
import yos.develop.recipeapp.core.components.LoadingScreen
import yos.develop.recipeapp.core.components.TopBarGlobal
import yos.develop.recipeapp.core.navigation.Screens
import yos.develop.recipeapp.core.utils.Catalog
import yos.develop.recipeapp.core.utils.Constants

@Composable
fun HomeScreen(
    state: HomeState,
    onEvent:(HomeEvent) -> Unit,
    onGoLogin:() -> Unit,
    onNavigation:(Screens) -> Unit
) {

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            TopBarGlobal(
                text = Constants.RECIPE_TOB_BAR,
                showBack = false,
                showAction = true,
                showFilter = true,
                showBadged = state.isFilterApplied,
                onClickFilter = {},
                showLogout = true,
                onClickLogout = {
                    onEvent(HomeEvent.Logout)
                }
            )
        },
        floatingActionButton = {
            ExtendedFloatingActionButton(
                onClick = {
                    onNavigation(Screens.RecipeScreen(idRecipe = Catalog.ID_FOR_ADD_RECIPE))
                }
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        Icons.Filled.Add,
                        contentDescription = Constants.ADD_RECIPE_IC
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(text = Constants.NEW_RECIPE_FLOATING)
                }

            }

        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .padding(paddingValues)
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.surface)
        ) {
            if (state.isLoadingDataInitial){
                LoadingScreen(
                    modifier = Modifier.fillMaxSize()
                )
            }else{
                if (state.recipes.isEmpty()){
                    EmptyList(
                        modifier = Modifier.fillMaxSize(),
                        text = Constants.YOU_HAVE_NOT_ADDED_A_RECIPE_YET
                    )
                }else{
                    LazyColumn {
                        items(state.recipes){
                            Text(it.title)
                        }
                    }
                }
            }
        }
    }

    DialogError(
        isError = state.isError,
        errorMessage = state.errorMessage,
        onDismiss = {
            onEvent(HomeEvent.ToggleShowDialogError)
        }
    )

    LaunchedEffect(key1 = state.successLogout) {
        if(state.successLogout){
            onGoLogin()
            onEvent(HomeEvent.ToggleSuccess)
        }
    }
}