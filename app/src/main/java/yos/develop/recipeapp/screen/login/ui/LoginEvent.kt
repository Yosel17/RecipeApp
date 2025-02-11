package yos.develop.recipeapp.screen.login.ui

sealed class LoginEvent {

    data object ToggleShowDialogError: LoginEvent()
}