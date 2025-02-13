package yos.develop.recipeapp.screen.recipe.ui

import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.PhotoLibrary
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import yos.develop.recipeapp.core.utils.Constants

@Composable
fun BodyRecipe(
    modifier: Modifier = Modifier,
    state: RecipeState,
    onEvent:(RecipeEvent) -> Unit
) {
    Column(
        modifier = modifier
    ) {
        val pickMediaLauncher = rememberLauncherForActivityResult(
            contract = ActivityResultContracts.PickVisualMedia(),
            onResult = { uri ->
                uri?.let {
                    onEvent(RecipeEvent.AddUriImage(uri = it))
                }
            }
        )

        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier
                    .fillMaxWidth()
                    .height(250.dp)
                    .border(2.dp, MaterialTheme.colorScheme.primary)
                    .weight(1f),
                contentAlignment = Alignment.Center
            ) {
                this@Row.AnimatedVisibility(visible = state.imageUri != null) {
                    AsyncImage(
                        modifier = Modifier.fillMaxSize(),
                        model = state.imageUri,
                        contentDescription = null,
                        contentScale = ContentScale.Crop
                    )
                }
            }
            TextButton(
                onClick = {
                    pickMediaLauncher.launch(PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.ImageOnly))
                }
            ) {
                Row {
                    Icon(
                        imageVector = Icons.Filled.PhotoLibrary,
                        contentDescription = Constants.SELECTED_IMAGE_IC
                    )
                    Text(
                        text = Constants.SELECT_IMAGE_TEXT_BUTTON,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )
                }
            }
        }
    }
}