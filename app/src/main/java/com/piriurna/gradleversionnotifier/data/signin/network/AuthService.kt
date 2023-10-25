package com.piriurna.gradleversionnotifier.data.signin.network

import com.piriurna.gradleversionnotifier.data.signin.SaveUserRequestBody
import com.piriurna.gradleversionnotifier.data.signin.models.UserResponse
import retrofit2.http.Body
import retrofit2.http.POST

interface AuthService {

    @POST("users/register")
    suspend fun saveUserToDatabase(
        @Body user: SaveUserRequestBody
    ): UserResponse
}