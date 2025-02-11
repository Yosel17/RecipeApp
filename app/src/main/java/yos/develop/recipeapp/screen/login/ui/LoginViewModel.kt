package yos.develop.recipeapp.screen.login.ui

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import yos.develop.recipeapp.screen.login.domain.LoginRepository
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val loginRepository: LoginRepository
): ViewModel() {

    var state by mutableStateOf(LoginState())

    fun onLoginEvent(event: LoginEvent){
        when(event){
            LoginEvent.ToggleShowDialogError -> {
                state = state.copy(isError = !state.isError)
            }
        }
    }
}