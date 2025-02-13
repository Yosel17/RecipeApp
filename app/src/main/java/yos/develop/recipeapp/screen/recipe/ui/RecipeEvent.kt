package yos.develop.recipeapp.screen.recipe.ui

sealed class RecipeEvent {

    data object ToggleShowDialogError: RecipeEvent()

    data class GetRecipe(val idRecipe: Int): RecipeEvent()
}