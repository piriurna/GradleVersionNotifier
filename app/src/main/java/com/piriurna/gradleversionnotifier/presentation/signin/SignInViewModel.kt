package com.piriurna.gradleversionnotifier.presentation.signin

import android.content.Context
import android.content.Intent
import androidx.activity.result.ActivityResultLauncher
import androidx.lifecycle.ViewModel
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.firebase.auth.FirebaseUser
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import javax.inject.Inject

data class SignInUiState(
    val email: String = "",
    val password: String = "",
    val isLoading: Boolean = false,
    val error: Boolean = false,
    val user: FirebaseUser? = null
)

@HiltViewModel
class SignInViewModel @Inject constructor(

): ViewModel() {

    private val _uiState = MutableStateFlow(SignInUiState())
    val uiState = _uiState.value

    private fun updateUiState(uiState: SignInUiState) {
        _uiState.value = uiState
    }

    fun updateUser(user: FirebaseUser?) {
        updateUiState(uiState.copy(user = user))
    }

    fun onEmailChanged(email: String) {
        _uiState.value = uiState.copy(email = email)
    }

    fun onPasswordChanged(password: String) {
        _uiState.value = uiState.copy(password = password)
    }

    fun onSignInClicked() {
        _uiState.value = uiState.copy(isLoading = true)
    }

    fun onGoogleLoginPressed(
        context: Context,
        token: String,
        launcher: ActivityResultLauncher<Intent>
    ) {
        _uiState.value = uiState.copy(isLoading = true)
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



        _uiState.value = uiState.copy(isLoading = false, user = user)
    }

    fun onSignInError() {
        _uiState.value = uiState.copy(isLoading = false, error = true, user = null)
    }
}