package com.piriurna.gradleversionnotifier.presentation.signin

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.piriurna.gradleversionnotifier.R
import com.piriurna.gradleversionnotifier.ui.components.signin.rememberFirebaseAuthLauncher

@Composable
fun SignInScreen(
    viewModel: SignInViewModel,
) {
    val uiState = viewModel.uiState

    val context = LocalContext.current

    val launcher = rememberFirebaseAuthLauncher(
        onAuthComplete = { result ->
            viewModel.onSignInSuccess(result.user)
        },
        onAuthError = {
            viewModel.onSignInError()
        }
    )
    val token = stringResource(R.string.default_web_client_id)

    SignInScreenContent(
        uiState = uiState,
        onSignInClicked = viewModel::onSignInClicked,
        onEmailChanged = viewModel::onEmailChanged,
        onPasswordChanged = viewModel::onPasswordChanged,
        onGoogleLoginPressed = {
            viewModel.onGoogleLoginPressed(context, token, launcher)
        },
    )
}

@Composable
fun SignInScreenContent(
    uiState: SignInUiState,
    onSignInClicked: () -> Unit,
    onEmailChanged: (String) -> Unit,
    onPasswordChanged: (String) -> Unit,
    onGoogleLoginPressed: () -> Unit,
) {

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center
    ) {
        EmailPasswordLoginCard(
            email = uiState.email,
            onEmailChanged = onEmailChanged,
            password = uiState.password,
            onPasswordChanged = onPasswordChanged,
            onSignInClicked = onSignInClicked
        )
        Spacer(modifier = Modifier.height(32.dp))
        GoogleLoginButton(
            onClick = onGoogleLoginPressed
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EmailPasswordLoginCard(
    email: String,
    onEmailChanged: (String) -> Unit,
    password: String,
    onPasswordChanged: (String) -> Unit,
    onSignInClicked: () -> Unit
) {
    ElevatedCard(
        modifier = Modifier.padding(16.dp),
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                text = "Dependency Notifier",
                style = MaterialTheme.typography.headlineMedium
            )
            Spacer(modifier = Modifier.height(16.dp))
            TextField(
                value = email,
                onValueChange = onEmailChanged,
                placeholder = { Text(text = "Email") }
            )
            Spacer(modifier = Modifier.height(16.dp))
            TextField(
                value = password,
                onValueChange = onPasswordChanged,
                placeholder = { Text(text = "Password") }
            )
            Spacer(modifier = Modifier.height(32.dp))
            Button(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp),
                onClick = onSignInClicked
            ) {
                Text(
                    text = "Sign-in"
                )
            }
            Spacer(modifier = Modifier.height(16.dp))
        }
    }
}

@Composable
fun GoogleLoginButton(
    onClick: () -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp)
    ) {
        Button(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp),
            onClick = onClick
        ) {
            Text(
                text = "Sign-in with Google"
            )
        }
    }
}

@Preview
@Composable
private fun SignInScreenPreview() {
    SignInScreenContent(
        uiState = SignInUiState(),
        onSignInClicked = { /*TODO*/ },
        onEmailChanged = {},
        onPasswordChanged = {}
    ) {

    }
}
