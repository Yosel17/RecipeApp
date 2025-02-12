package yos.develop.recipeapp.screen.home.ui

import yos.develop.recipeapp.core.model.RecipeModel

data class HomeState(
    val errorMessage: String = "",
    val isError: Boolean = false,
    val isLoadingDataInitial: Boolean = true,
    val recipes: List<RecipeModel> = emptyList(),
    val successLogout: Boolean = false,
    val isFilterApplied: Boolean = false

)
