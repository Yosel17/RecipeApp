package yos.develop.recipeapp.core.navigation

import kotlinx.serialization.Serializable

sealed interface Screens {

    @Serializable
    data object EmptyScreen: Screens

    @Serializable
    data object LoginScreen: Screens

    @Serializable
    data object HomeScreen: Screens
}