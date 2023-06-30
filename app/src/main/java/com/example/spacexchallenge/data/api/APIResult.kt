package com.example.spacexchallenge.data.api

sealed class APIResult<T> {
    data class Success<T>(val data: T): APIResult<T>()
    data class Error<T>(val message: String): APIResult<T>()

    companion object {
        const val ERROR_BODY_RESULT_EMPTY = "Error -- body result empty"
        const val ERROR_GENERIC = "Something gone wrong"
    }
}