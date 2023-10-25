package com.piriurna.gradleversionnotifier.domain.repositories

import com.piriurna.gradleversionnotifier.data.ResponseWrapper

interface BaseRepository<T> {

    suspend fun apiCall(apiCall: suspend () -> T): ResponseWrapper<T> {
        return try {
            ResponseWrapper.Success(apiCall())
        } catch (e: Exception) {
            ResponseWrapper.Error(e)
        }
    }
}