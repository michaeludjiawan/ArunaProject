package com.michaeludjiawan.arunaproject.ui

import com.michaeludjiawan.arunaproject.data.api.Result

sealed class UiResult<out T : Any> {
    data class Loading<out T : Any>(val data: T? = null) : UiResult<T>()
    data class Success<out T : Any>(val data: T) : UiResult<T>()
    data class Error<out T : Any>(val throwable: Throwable) : UiResult<T>()
}

fun <T : Any> Result<T>.toUiResult(): UiResult<T> {
    return when (this) {
        is Result.Success -> {
            UiResult.Success(data)
        }
        is Result.Error -> {
            UiResult.Error(throwable)
        }
    }
}