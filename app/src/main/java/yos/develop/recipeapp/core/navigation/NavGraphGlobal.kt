package yos.develop.recipeapp.core.navigation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.toRoute
import yos.develop.recipeapp.core.utils.Catalog
import yos.develop.recipeapp.screen.home.ui.HomeScreen
import yos.develop.recipeapp.screen.home.ui.HomeViewModel
import yos.develop.recipeapp.screen.login.ui.LoginScreen
import yos.develop.recipeapp.screen.login.ui.LoginViewModel
import yos.develop.recipeapp.screen.recipe.ui.RecipeScreen
import yos.develop.recipeapp.screen.recipe.ui.RecipeViewModel

@Composable
fun NavGraphGlobal(
    navHostController: NavHostController,
    startDestination: Screens
) {
    NavHost(
        navController = navHostController,
        startDestination = startDestination
    ) {
        composable<Screens.EmptyScreen> {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(MaterialTheme.colorScheme.surface)
            )
        }

        composable<Screens.LoginScreen> {
            val viewModel = hiltViewModel<LoginViewModel>()
            val state = viewModel.state

            LoginScreen(
                state = state,
                onEvent = { event ->
                    viewModel.onLoginEvent(event = event)
                },
                onNavigation = { screen ->
                    navHostController.navigate(screen){
                        popUpTo(0){
                            inclusive = true
                        }
                    }
                }
            )
        }

        composable<Screens.HomeScreen> {
            val viewModel = hiltViewModel<HomeViewModel>()
            val state = viewModel.state

            HomeScreen(
                state = state,
                onEvent = { event ->
                    viewModel.onHomeEvent(event = event)
                },
                onGoLogin = {
                    navHostController.popBackStack()
                    navHostController.navigate(Screens.LoginScreen)
                },
                onNavigation = { screen ->
                    navHostController.navigate(screen)
                }
            )
        }

        composable<Screens.RecipeScreen> {
            val recipeScreen: Screens.RecipeScreen = it.toRoute()
            val viewModel = hiltViewModel<RecipeViewModel>()
            val state = viewModel.state

            RecipeScreen(
                state =  state,
                idRecipe = recipeScreen.idRecipe,
                onEvent = { event ->
                    viewModel.onRecipeEvent(event = event)
                },
                onBack = {
                    navHostController.navigateUp()
                }
            )
        }
    }
}