package yos.develop.recipeapp.screen.recipe.domain

import yos.develop.recipeapp.core.model.RecipeModel
import yos.develop.recipeapp.core.utils.Resource

interface RecipeRepository {

    suspend fun getRecipe(idRecipe: Int): Resource<RecipeModel>

    suspend fun insertRecipe(recipe: RecipeModel): Resource<Unit>

    suspend fun saveFavoriteRecipe(recipe: RecipeModel): Resource<Unit>
}