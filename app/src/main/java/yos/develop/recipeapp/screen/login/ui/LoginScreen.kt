package yos.develop.recipeapp.screen.login.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import yos.develop.recipeapp.core.components.DialogError

@Composable
fun LoginScreen(
    state: LoginState,
    onEvent:(LoginEvent) -> Unit
) {
    Scaffold { paddingValues ->
        Column(
            modifier = Modifier
                .padding(paddingValues)
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.surface)
        ) {
            BodyLogin(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp),
                state = state,
                onEvent = onEvent
            )
        }
    }

    DialogError(
        isError = state.isError,
        errorMessage = state.errorMessage,
        onDismiss = {
            onEvent(LoginEvent.ToggleShowDialogError)
        }
    )


}