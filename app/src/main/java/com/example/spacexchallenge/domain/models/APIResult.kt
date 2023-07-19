package com.example.spacexchallenge.domain.models

sealed class APIResult<out T> {
    data class Success<T>(val data: T): APIResult<T>()
    data class Error(val message: String): APIResult<Nothing>()

    companion object {
        const val ERROR_BODY_RESULT_EMPTY = "Error -- body result empty"
        const val ERROR_GENERIC = "Something gone wrong"
    }
}