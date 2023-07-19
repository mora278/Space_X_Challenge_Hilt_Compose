package com.example.spacexchallenge.domain.models

sealed class UIState<out T> {
    object Loading : UIState<Nothing>()
    data class Error(val message: String) : UIState<Nothing>()
    data class Success<T>(val data: T) : UIState<T>()
}