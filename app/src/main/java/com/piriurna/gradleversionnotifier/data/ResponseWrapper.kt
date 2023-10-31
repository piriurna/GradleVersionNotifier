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

fun <T, U> ResponseWrapper<T>.asResult(
    operator: (T) -> U
): Result<U> {
    return when (this) {
        is ResponseWrapper.Success -> Result.success(operator(data))
        is ResponseWrapper.Error -> Result.failure(exception)
    }
}
