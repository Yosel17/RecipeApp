package yos.develop.recipeapp.splash.domain

import yos.develop.recipeapp.core.utils.Resource

interface SplashRepository {

    suspend fun getSession(): Resource<Boolean>
}