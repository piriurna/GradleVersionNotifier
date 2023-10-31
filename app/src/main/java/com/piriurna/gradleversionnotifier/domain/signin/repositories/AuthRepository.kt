package com.piriurna.gradleversionnotifier.domain.signin.repositories

import com.piriurna.gradleversionnotifier.data.ResponseWrapper
import com.piriurna.gradleversionnotifier.data.signin.SaveUserRequestBody
import com.piriurna.gradleversionnotifier.domain.entities.User
import com.piriurna.gradleversionnotifier.domain.repositories.BaseRepository

interface AuthRepository : BaseRepository<Unit> {

    suspend fun saveUserToDatabase(userRequestBody: SaveUserRequestBody): ResponseWrapper<Unit>

    suspend fun saveUserToLocal(userResponse: User): ResponseWrapper<Unit>
}