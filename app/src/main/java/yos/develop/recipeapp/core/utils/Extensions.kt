package yos.develop.recipeapp.core.utils

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import yos.develop.recipeapp.core.model.RecipeModel
import yos.develop.recipeapp.core.room.tables.recipe.RecipeEntity

fun Flow<List<RecipeEntity>>.asFlowRecipeModelList(): Flow<List<RecipeModel>>{
    return this.map { recipeEntityList ->
        recipeEntityList.map{ recipeEntity ->
            recipeEntity.toModel()
        }
    }
}

fun RecipeEntity.toModel(): RecipeModel{
    return RecipeModel(
        idRecipe,
        title,
        description,
        preparationTime,
        favorite,
        routeImage
    )
}