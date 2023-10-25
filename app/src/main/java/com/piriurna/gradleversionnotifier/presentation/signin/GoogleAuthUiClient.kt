package com.piriurna.gradleversionnotifier.presentation.signin

import android.content.Context
import android.content.Intent
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.piriurna.gradleversionnotifier.R

class GoogleAuthUiClient (
    private val context: Context
) {
    private val googleSignInOptions by lazy {
        GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(context.getString(R.string.default_web_client_id))
            .requestEmail()
            .build()
    }

    private val googleSignInClient by lazy {
        GoogleSignIn.getClient(context, googleSignInOptions)
    }

    fun getSignInIntent(): Intent = googleSignInClient.signInIntent

    fun signOut() {
        googleSignInClient.signOut()
    }
}