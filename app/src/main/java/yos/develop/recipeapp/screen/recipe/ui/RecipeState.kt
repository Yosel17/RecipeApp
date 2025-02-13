package yos.develop.recipeapp.screen.recipe.ui

import yos.develop.recipeapp.core.model.RecipeModel

data class RecipeState(
    val errorMessage: String = "",
    val isError: Boolean = false,
    val isLoadingDataInitial: Boolean = true,
    val recipe: RecipeModel = RecipeModel()
)
