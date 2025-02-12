@file:OptIn(ExperimentalMaterial3Api::class)

package yos.develop.recipeapp.core.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.automirrored.filled.Logout
import androidx.compose.material.icons.filled.FilterAlt
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material3.Badge
import androidx.compose.material3.BadgedBox
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.animateLottieCompositionAsState
import com.airbnb.lottie.compose.rememberLottieComposition
import yos.develop.recipeapp.R
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

@Composable
fun TopBarGlobal(
    text: String,
    showBack:Boolean = true,
    icon: ImageVector = Icons.AutoMirrored.Filled.ArrowBack,
    onClick: () -> Unit = {},
    showAction: Boolean = false,
    showFilter: Boolean = false,
    showBadged: Boolean = false,
    onClickFilter: () -> Unit = {},
    showLogout: Boolean = false,
    onClickLogout: () -> Unit = {}
) {
    TopAppBar(
        title = {
            Text(
                text = text,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
        },
        navigationIcon = {
            if(showBack){
                IconButton(onClick = onClick) {
                    Icon(imageVector = icon, contentDescription = text)
                }
            }
        },
        actions = {
            if (showAction) {
                Row {
                    if (showLogout){
                        Icon(
                            Icons.AutoMirrored.Filled.Logout,
                            contentDescription = Constants.LOGOUT_IC,
                            tint = MaterialTheme.colorScheme.onSurface,
                            modifier = Modifier
                                .size(32.dp)
                                .clickable {
                                    onClickLogout()
                                }
                        )
                    }
                    Spacer(Modifier.width(8.dp))
                    if (showFilter){
                        BadgedBox(
                            modifier = Modifier
                                .padding(end = 16.dp),
                            badge = {
                                if (showBadged) {
                                    Badge {}
                                }

                            }) {
                            Icon(
                                Icons.Filled.FilterAlt,
                                contentDescription = Constants.FILTER_IC,
                                tint = MaterialTheme.colorScheme.onSurface,
                                modifier = Modifier
                                    .size(32.dp)
                                    .clickable {
                                        onClickFilter()
                                    }
                            )
                        }
                    }
                }
            }
        }
    )
}

@Composable
fun LoadingScreen(modifier: Modifier = Modifier) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        CircularProgressIndicator()
    }
}

@Composable
fun EmptyList(
    modifier: Modifier = Modifier,
    text: String = Constants.NO_DATA_TO_SHOW,
    size: Dp = 200.dp,
    style: TextStyle = MaterialTheme.typography.titleMedium
) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        val composition by rememberLottieComposition(
            spec = LottieCompositionSpec.RawRes(R.raw.empty)
        )
        val progress by animateLottieCompositionAsState(
            composition = composition,
            isPlaying = true,
            iterations = LottieConstants.IterateForever,
            restartOnPlay = true
        )
        Spacer(Modifier.height(16.dp))
        LottieAnimation(
            composition = composition,
            progress = progress,
            modifier = Modifier
                .size(size)
        )
        Text(
            text = text,
            color = MaterialTheme.colorScheme.onSurface,
            style = style,
            textAlign = TextAlign.Center
        )
    }
}