package yos.develop.recipeapp.screen.login.ui

import yos.develop.recipeapp.core.utils.Constants

data class LoginState(
    val errorMessage: String = "",
    val isError: Boolean = false,
    val email: String = Constants.EMAIL_KOALIT,
    val password: String = "",
    val isValidEmail: Boolean = true,
    val enableButton: Boolean = false,
    val isValidPassword: Boolean = true,
    val passwordVisibility: Boolean = false,
)
