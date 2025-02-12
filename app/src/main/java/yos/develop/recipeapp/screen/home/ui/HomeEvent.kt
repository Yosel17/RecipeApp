package yos.develop.recipeapp.screen.home.ui

sealed class HomeEvent {

    data object ToggleShowDialogError: HomeEvent()

    data object Logout: HomeEvent()

    data object ToggleSuccess: HomeEvent()
}