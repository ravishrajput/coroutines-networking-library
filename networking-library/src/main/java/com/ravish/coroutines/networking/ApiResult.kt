package com.ravish.coroutines.networking

import retrofit2.HttpException
import java.io.IOException

sealed class ApiResult<out T> {
    open fun value(): T? = null
    open fun error(): Error? = null

    val isSuccess: Boolean get() = value() !is Error
    val isFailure: Boolean get() = value() is Error
}

data class Success<out T>(val value: T) : ApiResult<T>() {
    override fun value() = value
}

data class Error(val code: Int? = null, val error: Throwable? = null) : ApiResult<Nothing>() {
    override fun error(): Error? = Error(code, error)
}

inline fun <T> ApiResult<T>.onSuccess(action: (value: T) -> Unit): ApiResult<T> {
    if (isSuccess) value()?.let { action(it) }
    return this
}

inline fun <T> ApiResult<T>.onFailure(action: (exception: Error) -> Unit): ApiResult<T> {
    if (isFailure) action(error() as Error)
    return this
}

suspend fun <T> callCatching(apiCall: suspend () -> T): ApiResult<T> {
    val result = runCatching { apiCall() }
    result.onSuccess {
        if (it == null) return Error(204, EmptyBodyException("Empty Response Body"))
        return Success(it)
    }

    result.onFailure {
        return when (it) {
            is IOException -> Error(error = it)
            is HttpException -> {
                Error(it.code(), it)
            }
            else -> {
                Error(null, it)
            }
        }
    }
    return Error(null, null)
}

data class EmptyBodyException(override val message: String) : Throwable()