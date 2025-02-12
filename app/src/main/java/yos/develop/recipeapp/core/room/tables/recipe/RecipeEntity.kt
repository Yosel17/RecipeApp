package yos.develop.recipeapp.core.room.tables.recipe

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class RecipeEntity(
    @PrimaryKey(autoGenerate = true)
    val idRecipe: Int = 0,
    val title: String,
    val description: String,
    val preparationTime: Int,
    val favorite: Boolean,
    val routeImage: String?
)
