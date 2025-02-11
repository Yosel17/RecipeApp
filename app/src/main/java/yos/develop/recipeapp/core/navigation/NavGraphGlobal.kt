package yos.develop.recipeapp.core.navigation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable

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
            Text("Login")
        }

        composable<Screens.HomeScreen> {
            Text("Home")
        }
    }
}