package yos.develop.recipeapp.core.components

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import yos.develop.recipeapp.core.utils.Constants

@Composable
fun ButtonGlobal(
    modifier: Modifier = Modifier,
    textButton: String,
    enable: Boolean,
    isLoading: Boolean,
    onClick: () -> Unit,
    textIsLoading: String = Constants.LOADING_TEXT_BUTTON,
    shape: Shape = ButtonDefaults.shape,
    backgroundColor: Color = MaterialTheme.colorScheme.primary,
    textColor: Color = MaterialTheme.colorScheme.onPrimary,
    textColorEnable: Color = MaterialTheme.colorScheme.primary
) {
    Button(
        modifier = modifier,
        onClick = onClick,
        enabled = enable,
        shape = shape,
        colors = ButtonDefaults.buttonColors(
            containerColor = backgroundColor,
        )
    ) {
        if (isLoading) {
            Row {
                Text(
                    text = textIsLoading,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                    color = if (enable) textColor else textColorEnable
                )
                Spacer(modifier = Modifier.size(4.dp))
                CircularProgressIndicator(
                    modifier = Modifier
                        .width(24.dp)
                        .height(24.dp)
                )
            }
        } else {
            Text(
                text = textButton,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                color = if (enable) textColor else textColorEnable
            )
        }

    }
}