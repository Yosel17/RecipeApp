package yos.develop.recipeapp.core.model

data class RecipeModel(
    val idRecipe: Int = 0,
    val title: String = "",
    val description: String = "",
    val preparationTime: Int = 0,
    val favorite: Boolean = false,
    val routeImage: String? = null
)