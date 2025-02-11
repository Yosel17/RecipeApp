package yos.develop.recipeapp.screen.login.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import yos.develop.recipeapp.R
import yos.develop.recipeapp.core.components.ButtonGlobal
import yos.develop.recipeapp.core.utils.Catalog
import yos.develop.recipeapp.core.utils.Constants

@Composable
fun BodyLogin(
    modifier: Modifier = Modifier,
    state: LoginState,
    onEvent:(LoginEvent) -> Unit
) {

    val focusManager = LocalFocusManager.current
    val keyboardController = LocalSoftwareKeyboardController.current
    val passwordVisibility = rememberSaveable { mutableStateOf(false) }

    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Image(
            painter = painterResource(id = R.drawable.ic_recipe),
            contentDescription = Constants.LOGO_APP_IC,
            modifier = Modifier.size(200.dp)
        )

        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = Constants.RECIPE_APP,
            style = MaterialTheme.typography.headlineSmall.copy(fontWeight = FontWeight.W500),
            color = MaterialTheme.colorScheme.onSurface
        )

        Spacer(modifier = Modifier.height(32.dp))

        OutlinedTextField(
            value = state.email,
            onValueChange = { value ->
                onEvent(LoginEvent.ChangeTextFields(type = Catalog.EMAIL_TEXT_FIELD, newValue = value))
            },
            modifier = Modifier.fillMaxWidth(),
            maxLines = 1,
            singleLine = true,
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Email,
                imeAction = ImeAction.Next
            ),
            keyboardActions = KeyboardActions(
                onNext = {
                    focusManager.moveFocus(FocusDirection.Next)
                }
            ),
            label = {
                Text(
                    text = Constants.EMAIL_LABEL
                )
            },
            supportingText = {
                if (!state.isValidEmail) {
                    Text(text = Constants.THIS_EMAIL_DOES_NOT_CORRESPOND_TO_KOALIT_USER)
                }
            },
            isError = !state.isValidEmail,
            enabled = state.enableTextFields,
            visualTransformation = VisualTransformation.None
        )

        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(
            value = state.password,
            onValueChange = { value ->
                onEvent(LoginEvent.ChangeTextFields(type = Catalog.PASSWORD_TEXT_FIELD, newValue = value))
            },
            modifier = Modifier
                .fillMaxWidth(),
            maxLines = 1,
            singleLine = true,
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Password,
                imeAction = ImeAction.Done
            ),
            keyboardActions = KeyboardActions(
                onDone = {
                    keyboardController?.hide()
                }
            ),
            trailingIcon = {
                val iconTrailing = if (passwordVisibility.value) {
                    Icons.Filled.Visibility
                } else {
                    Icons.Filled.VisibilityOff
                }
                IconButton(
                    onClick = { passwordVisibility.value = !passwordVisibility.value }
                ) {
                    Icon(
                        imageVector = iconTrailing,
                        contentDescription = Constants.SEE_IC
                    )
                }
            },
            visualTransformation = if (passwordVisibility.value) {
                VisualTransformation.None
            } else {
                PasswordVisualTransformation()
            },
            label = {
                Text(
                    text = Constants.PASSWORD_LABEL
                )
            },
            supportingText = {
                if (!state.isValidPassword) {
                    Text(text = Constants.THIS_PASSWORD_DOES_NOT_CORRESPOND_TO_KOALIT_USER)
                }
            },
            isError = !state.isValidPassword,
            enabled = state.enableTextFields
        )

        Spacer(modifier = Modifier.height(24.dp))

        ButtonGlobal(
            textButton = Constants.GET_INTO_TEXT_BUTTON,
            enable = state.enableButton,
            isLoading = state.isLoadingLogin,
            onClick = {
                onEvent(LoginEvent.Login)
            }
        )

    }
}