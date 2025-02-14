package yos.develop.recipeapp.screen.home.ui

import yos.develop.recipeapp.core.model.RecipeModel
import yos.develop.recipeapp.core.utils.Catalog

data class HomeState(
    val errorMessage: String = "",
    val isError: Boolean = false,
    val isLoadingDataInitial: Boolean = true,
    val recipes: List<RecipeModel> = emptyList(),
    val successLogout: Boolean = false,
    val isFilterApplied: Boolean = false,
    val showDialogFilter: Boolean = false,
    val selectedFilter: Int = Catalog.ALL_FILTER,
    val recipesFilter: List<RecipeModel> = emptyList()

)
