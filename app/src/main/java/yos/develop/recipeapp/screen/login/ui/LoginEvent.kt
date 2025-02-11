package yos.develop.recipeapp.screen.login.ui

sealed class LoginEvent {

    data object ToggleShowDialogError: LoginEvent()

    data class ChangeTextFields(val type: Int, val newValue: String): LoginEvent()

    data object Login: LoginEvent()

    data object ChangeSuccess: LoginEvent()
}