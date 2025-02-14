@file:OptIn(ExperimentalMaterial3Api::class)

package yos.develop.recipeapp.core.components

import android.net.Uri
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.automirrored.filled.Logout
import androidx.compose.material.icons.filled.Cancel
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FilterAlt
import androidx.compose.material.icons.filled.HideImage
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material3.Badge
import androidx.compose.material3.BadgedBox
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
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
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.animateLottieCompositionAsState
import com.airbnb.lottie.compose.rememberLottieComposition
import yos.develop.recipeapp.R
import yos.develop.recipeapp.core.model.RecipeModel
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

@Composable
fun ItemRecipe(
    modifier: Modifier = Modifier,
    recipe: RecipeModel,
    onClick:(Int) -> Unit
) {
    Card(
        modifier = modifier,
        onClick = {
            onClick(recipe.idRecipe)
        }
    ) {
        Column(
            modifier = Modifier.fillMaxWidth()
        ) {
            if(recipe.routeImage.isNullOrEmpty()){
                Box(
                    modifier = Modifier
                        .height(200.dp)
                        .fillMaxWidth()
                        .background(MaterialTheme.colorScheme.surfaceVariant),
                    contentAlignment = Alignment.Center
                ){
                    Icon(
                        imageVector = Icons.Filled.HideImage,
                        contentDescription = Constants.NOT_IMAGE_IC
                    )
                }
            }else{
                AsyncImage(
                    modifier = Modifier.fillMaxWidth().height(200.dp),
                    model = Uri.parse(recipe.routeImage),
                    contentDescription = null,
                    contentScale = ContentScale.Crop
                )
            }
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                modifier = Modifier.padding(horizontal = 16.dp),
                text = recipe.title,
                style = MaterialTheme.typography.titleSmall.copy(fontWeight = FontWeight.Bold),
                maxLines = 2,
                overflow = TextOverflow.Ellipsis
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                modifier = Modifier.padding(horizontal = 16.dp),
                text = "${Constants.TIME} ${recipe.preparationTime} min.",
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                style = MaterialTheme.typography.labelSmall
            )
            Icon(
                modifier = Modifier.padding(16.dp).align(Alignment.End),
                imageVector = if(recipe.favorite) Icons.Filled.Favorite else Icons.Outlined.FavoriteBorder,
                contentDescription = Constants.FAVORITE_IC
            )
        }
    }
}

@Composable
fun TitleAndExit(
    modifier: Modifier = Modifier,
    title: String,
    textColor: Color = MaterialTheme.colorScheme.onSurface,
    onClick: () -> Unit
) {
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            modifier = Modifier.weight(1f),
            text = title,
            color = textColor,
            style = MaterialTheme.typography.headlineSmall,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis
        )
        IconButton(onClick = onClick) {
            Icon(
                imageVector = Icons.Filled.Cancel,
                contentDescription =Constants.CANCEL_IC,
                tint = MaterialTheme.colorScheme.error
            )
        }
    }
}