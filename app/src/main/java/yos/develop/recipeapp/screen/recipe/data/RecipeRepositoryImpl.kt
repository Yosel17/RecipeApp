package yos.develop.recipeapp.screen.recipe.data

import yos.develop.recipeapp.core.model.RecipeModel
import yos.develop.recipeapp.core.room.tables.recipe.RecipeDao
import yos.develop.recipeapp.core.utils.Resource
import yos.develop.recipeapp.core.utils.toEntity
import yos.develop.recipeapp.core.utils.toModel
import yos.develop.recipeapp.screen.recipe.domain.RecipeRepository
import javax.inject.Inject

class RecipeRepositoryImpl @Inject constructor(
    private val recipeDao: RecipeDao
): RecipeRepository {

    override suspend fun getRecipe(idRecipe: Int): Resource<RecipeModel> {
        return try {
            val recipe = recipeDao.getRecipeForId(idRecipe = idRecipe)
            Resource.Success(recipe.toModel())
        }catch (e: Exception){
            Resource.Failure(exception = e)
        }
    }

    override suspend fun insertRecipe(recipe: RecipeModel): Resource<Unit> {
        return try {
            recipeDao.insertRecipe(recipeEntity = recipe.toEntity())
            Resource.Success(Unit)
        }catch (e: Exception){
            Resource.Failure(exception = e)
        }
    }

    override suspend fun saveFavoriteRecipe(recipe: RecipeModel): Resource<Unit> {
        return try {
            recipeDao.updateRecipe(recipeEntity = recipe.toEntity())
            Resource.Success(Unit)
        }catch (e: Exception){
            Resource.Failure(exception = e)
        }
    }
}