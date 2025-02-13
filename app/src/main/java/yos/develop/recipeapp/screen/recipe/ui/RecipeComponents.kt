package yos.develop.recipeapp.screen.recipe.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun BodyRecipe(
    modifier: Modifier = Modifier,
    state: RecipeState,
    onEvent:(RecipeEvent) -> Unit
) {
    Column(
        modifier = modifier
    ) {
        Row(
            modifier = Modifier.fillMaxWidth()
        ) {
            
        }
    }
}