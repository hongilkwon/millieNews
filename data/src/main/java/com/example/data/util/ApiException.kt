package com.example.data.util

class ApiException(
    val code: Int,
    override val message: String?,
) : Exception() {
    override fun toString() = "code: $code, message: $message"

    companion object {
        const val RESPONSE_IS_NULL = 101
        const val UNKNOWN_ERROR_CODE = 102
    }
}