package yos.develop.recipeapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.navigation.compose.rememberNavController
import com.example.compose.AppTheme
import dagger.hilt.android.AndroidEntryPoint
import yos.develop.recipeapp.core.navigation.NavGraphGlobal
import yos.develop.recipeapp.splash.ui.SplashViewModel
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    @Inject
    lateinit var splashViewModel: SplashViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        installSplashScreen().apply {
            setKeepOnScreenCondition{
                !splashViewModel.isReady
            }
            setOnExitAnimationListener{screen ->
                screen.iconView.scaleX = 0.5f
                screen.iconView.scaleY = 0.5f
                screen.remove()
            }
        }
        enableEdgeToEdge()
        setContent {
            AppTheme {
                val navHostController = rememberNavController()

                NavGraphGlobal(
                    navHostController = navHostController,
                    startDestination = splashViewModel.currentScreen
                )
            }
        }
    }
}