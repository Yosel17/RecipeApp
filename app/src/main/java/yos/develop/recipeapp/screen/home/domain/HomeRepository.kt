package yos.develop.recipeapp.screen.home.domain

import kotlinx.coroutines.flow.Flow
import yos.develop.recipeapp.core.model.RecipeModel
import yos.develop.recipeapp.core.utils.Resource

interface HomeRepository {

    suspend fun getRecipes(): Resource<Flow<List<RecipeModel>>>

    suspend fun logout(): Resource<Unit>
}