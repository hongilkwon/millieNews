package com.example.domain.util

sealed class ApiResult<out T> {
    data class Success<out T>(val data: T) : ApiResult<T>()
    data class Failure(val baseException: Throwable) : ApiResult<Nothing>()
}