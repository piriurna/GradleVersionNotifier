package com.piriurna.gradleversionnotifier.domain.repositories

import com.piriurna.gradleversionnotifier.data.ResponseWrapper
import com.piriurna.gradleversionnotifier.data.signin.SaveUserRequestBody
import com.piriurna.gradleversionnotifier.data.signin.models.UserResponse

interface AuthRepository : BaseRepository<UserResponse> {

    suspend fun saveUserToDatabase(userRequestBody: SaveUserRequestBody): ResponseWrapper<UserResponse>
}