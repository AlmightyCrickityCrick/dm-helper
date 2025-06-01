package com.example.dmhelper.data.common

sealed class Result<out T : Any>() {
    data class Success<out T : Any>(val value: T? = null) : Result<T>()
    data class ValidationError(val error: List<String>?) : Result<Nothing>()
    data object ServerError : Result<Nothing>()
}