package com.piriurna.gradleversionnotifier.data.signin.network

import com.piriurna.gradleversionnotifier.data.signin.SaveUserRequestBody
import com.piriurna.gradleversionnotifier.domain.repositories.AuthRepository
import javax.inject.Inject

class AuthRepositoryImpl @Inject constructor(
    private val authService: AuthService
): AuthRepository {

    override suspend fun saveUserToDatabase(userRequestBody: SaveUserRequestBody) = apiCall {
        authService.saveUserToDatabase(userRequestBody)
    }
}