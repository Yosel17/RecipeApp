package yos.develop.recipeapp.splash.ui

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import yos.develop.recipeapp.core.navigation.Screens
import yos.develop.recipeapp.core.utils.Resource
import yos.develop.recipeapp.splash.domain.SplashRepository
import javax.inject.Inject

class SplashViewModel @Inject constructor(
    private val splashRepository: SplashRepository
): ViewModel() {

    var isReady by mutableStateOf(false)

    var currentScreen by mutableStateOf<Screens>(Screens.EmptyScreen)

    init {
        getSession()
    }

    private fun getSession() {
        viewModelScope.launch(Dispatchers.IO) {

            val response = splashRepository.getSession()

            withContext(Dispatchers.Main){
                when(response){
                    is Resource.Failure -> {
                        currentScreen = Screens.LoginScreen
                        isReady = true
                    }
                    is Resource.Success -> {
                        if (response.result){
                            currentScreen = Screens.HomeScreen
                        }else{
                            currentScreen = Screens.LoginScreen
                        }
                        isReady = true
                    }
                }
            }
        }
    }
}