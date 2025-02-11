package yos.develop.recipeapp.screen.login.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import yos.develop.recipeapp.core.components.DialogError

@Composable
fun LoginScreen(
    state: LoginState,
    onEvent:(LoginEvent) -> Unit
) {
    Scaffold(
        modifier = Modifier.fillMaxSize()
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.surface)
                .padding(paddingValues)
                .verticalScroll(rememberScrollState())
                .imePadding()
                .padding(16.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally

        ) {
            BodyLogin(
                modifier = Modifier
                    .fillMaxWidth(),
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