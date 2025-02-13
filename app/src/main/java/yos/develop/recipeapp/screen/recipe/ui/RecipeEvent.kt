package yos.develop.recipeapp.screen.recipe.ui

import android.net.Uri

sealed class RecipeEvent {

    data object ToggleShowDialogError: RecipeEvent()

    data class GetRecipe(val idRecipe: Int): RecipeEvent()

    data class AddUriImage(val uri: Uri): RecipeEvent()
}