package yos.develop.recipeapp.core.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.animateLottieCompositionAsState
import com.airbnb.lottie.compose.rememberLottieComposition
import yos.develop.recipeapp.R
import yos.develop.recipeapp.core.utils.Constants

@Composable
fun DialogError(
    isError: Boolean,
    errorMessage: String,
    onDismiss: () -> Unit
) {
    val composition by rememberLottieComposition(
        spec = LottieCompositionSpec.RawRes(R.raw.error)
    )

    val progress by animateLottieCompositionAsState(
        composition = composition,
        isPlaying = true,
        iterations = LottieConstants.IterateForever,
        restartOnPlay = true
    )

    if (isError) {
        Dialog(
            onDismissRequest = {},
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight()
                    .background(MaterialTheme.colorScheme.surfaceVariant)
                    .verticalScroll(rememberScrollState()),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Spacer(modifier = Modifier.size(16.dp))
                LottieAnimation(
                    composition = composition,
                    progress = progress,
                    modifier = Modifier
                        .width(200.dp)
                        .height(200.dp)
                )
                Spacer(modifier = Modifier.size(16.dp))
                Text(
                    modifier = Modifier
                        .padding(start = 16.dp, end = 16.dp),
                    text = errorMessage,
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                    style = MaterialTheme.typography.bodySmall,
                )
                Spacer(modifier = Modifier.size(32.dp))
                ButtonGlobal(
                    textButton = Constants.I_UNDERSTAND_TEXT_BUTTON,
                    enable = true,
                    isLoading = false,
                    onClick = onDismiss
                )
                Spacer(modifier = Modifier.size(16.dp))
            }
        }
    }
}