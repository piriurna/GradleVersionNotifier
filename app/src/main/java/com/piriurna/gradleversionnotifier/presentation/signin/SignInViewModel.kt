package com.piriurna.gradleversionnotifier.presentation.signin

import android.content.Context
import android.content.Intent
import androidx.activity.result.ActivityResultLauncher
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.firebase.auth.FirebaseUser
import com.piriurna.gradleversionnotifier.domain.entities.User
import com.piriurna.gradleversionnotifier.domain.signin.usecases.SendCreatedUserToApiUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject
import androidx.compose.runtime.State

data class SignInUiState(
    val email: String = "",
    val password: String = "",
    val isLoading: Boolean = false,
    val error: Boolean = false,
    val user: User? = null,
)

@HiltViewModel
class SignInViewModel @Inject constructor(
    private val sendCreatedUserToApiUseCase: SendCreatedUserToApiUseCase
): ViewModel() {

    private val _uiState = mutableStateOf(SignInUiState())
    val uiState: State<SignInUiState> = _uiState

    fun onEmailChanged(email: String) {
        _uiState.value = uiState.value.copy(email = email)
    }

    fun onPasswordChanged(password: String) {
        _uiState.value = uiState.value.copy(password = password)
    }

    fun onSignInClicked() {
        _uiState.value = uiState.value.copy(isLoading = true)
    }

    fun onGoogleLoginPressed(
        context: Context,
        token: String,
        launcher: ActivityResultLauncher<Intent>
    ) {
        _uiState.value = uiState.value.copy(isLoading = true)
        val gso =
            GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(token)
                .requestEmail()
                .build()
        val googleSignInClient = GoogleSignIn.getClient(context, gso)
        launcher.launch(googleSignInClient.signInIntent)
    }

    fun onSignInSuccess(user: FirebaseUser?) {
        if(user == null) {
            onSignInError()
            return
        }

        viewModelScope.launch {
            val result = sendCreatedUserToApiUseCase(user.uid)

            onLoginEnded(result)
        }
    }

    private fun onLoginEnded(result: Result<User>) {

        when {
             result.isSuccess -> {
                _uiState.value = uiState.value.copy(isLoading = false, user = result.getOrThrow())

            }
            result.isFailure -> {
                onSignInError()
            }

        }
    }

    fun onSignInError() {
        _uiState.value = uiState.value.copy(isLoading = false, error = true, user = null)
    }
}