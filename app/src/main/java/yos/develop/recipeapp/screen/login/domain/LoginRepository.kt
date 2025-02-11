package yos.develop.recipeapp.screen.login.domain

import yos.develop.recipeapp.core.utils.Resource

interface LoginRepository {

    suspend fun login(email: String, password: String): Resource<Boolean>
}