package yos.develop.recipeapp.screen.home.data


import javax.inject.Inject
import kotlinx.coroutines.flow.Flow
import yos.develop.recipeapp.core.model.RecipeModel
import yos.develop.recipeapp.core.room.tables.recipe.RecipeDao
import yos.develop.recipeapp.core.room.tables.user.UserDao
import yos.develop.recipeapp.core.utils.Resource
import yos.develop.recipeapp.core.utils.asFlowRecipeModelList
import yos.develop.recipeapp.screen.home.domain.HomeRepository

class HomeRepositoryImpl @Inject constructor(
    private val recipeDao: RecipeDao,
    private val userDao: UserDao
): HomeRepository {

    override suspend fun getRecipes(): Resource<Flow<List<RecipeModel>>> {
        return try {
            val recipes = recipeDao.getRecipes().asFlowRecipeModelList()
            Resource.Success(recipes)
        }catch (e: Exception){
            Resource.Failure(exception = e)
        }

    }

    override suspend fun logout(): Resource<Unit> {
        return try {
            userDao.deleteUser()
            Resource.Success(Unit)
        }catch (e: Exception){
            Resource.Failure(exception = e)
        }
    }
}