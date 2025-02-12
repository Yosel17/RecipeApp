package yos.develop.recipeapp.core.room.tables.recipe

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Update
import androidx.room.Upsert
import kotlinx.coroutines.flow.Flow

@Dao
interface RecipeDao {

    @Query("select * from recipeentity")
    fun getRecipes(): Flow<List<RecipeEntity>>

    @Query("select * from recipeentity where idRecipe = :idRecipe")
    suspend fun getRecipeForId(idRecipe: Int): RecipeEntity?

    @Upsert
    suspend fun insertRecipe(recipeEntity: RecipeEntity)

    @Update
    suspend fun updateRecipe(recipeEntity: RecipeEntity)
}