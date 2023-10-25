package com.piriurna.gradleversionnotifier.data

sealed class ResponseWrapper<out T> {
    data class Success<out T>(val data: T) : ResponseWrapper<T>()
    data class Error(val exception: Exception) : ResponseWrapper<Nothing>()

    suspend fun <T> apiCall(apiCall: suspend () -> T): ResponseWrapper<T> {
        return try {
            Success(apiCall())
        } catch (e: Exception) {
            Error(e)
        }
    }
}

