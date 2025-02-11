package yos.develop.recipeapp.splash.ui

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import yos.develop.recipeapp.core.navigation.Screens
import javax.inject.Inject

class SplashViewModel @Inject constructor(): ViewModel() {

    var isReady by mutableStateOf(false)

    var currentScreen by mutableStateOf<Screens>(Screens.EmptyScreen)
}