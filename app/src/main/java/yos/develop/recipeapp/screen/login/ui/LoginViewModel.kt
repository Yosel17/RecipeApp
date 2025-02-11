package yos.develop.recipeapp.screen.login.ui

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
import yos.develop.recipeapp.core.utils.Constants
import yos.develop.recipeapp.core.utils.Resource
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
            is LoginEvent.ChangeTextFields -> {
                changeTextFields(type = event.type, newValue = event.newValue)
            }
            LoginEvent.Login -> {
                login()
            }
            LoginEvent.ChangeSuccess -> {
                state = state.copy(successLogin = !state.successLogin)
            }
        }
    }

    private fun changeTextFields(type: Int, newValue: String) {
        when(type){
            Catalog.EMAIL_TEXT_FIELD ->{
                state = state.copy(email = newValue)
                state = state.copy(isValidEmail = state.email == Constants.EMAIL_KOALIT)
                state = state.copy(enableButton = validateFieldsLogin())
            }
            Catalog.PASSWORD_TEXT_FIELD ->{
                state = state.copy(password = newValue)
                state = state.copy(isValidPassword = state.password == Constants.PASSWORD_KOALIT)
                state = state.copy(enableButton = validateFieldsLogin())
            }
        }
    }

    private fun validateFieldsLogin(): Boolean{
        return state.email == Constants.EMAIL_KOALIT && state.password == Constants.PASSWORD_KOALIT
    }

    private fun login() {
        state = state.copy(isLoadingLogin = true)
        state = state.copy(enableButton = false)
        state = state.copy(enableTextFields = false)

        viewModelScope.launch(Dispatchers.IO) {
            delay(1000)
            val response = loginRepository.login(
                email = state.email,
                password = state.password
            )

            withContext(Dispatchers.Main){
                when(response){
                    is Resource.Failure -> {
                        state = state.copy(errorMessage = response.exception.localizedMessage ?: "---")
                        state = state.copy(isError = true)
                        state = state.copy(isLoadingLogin = false)
                        state = state.copy(enableButton = true)
                        state = state.copy(enableTextFields = true)
                    }
                    is Resource.Success -> {
                        state = state.copy(isLoadingLogin = false)
                        state = state.copy(enableButton = true)
                        state = state.copy(enableTextFields = true)
                        state = state.copy(successLogin = true)
                    }
                }
            }
        }
    }
}