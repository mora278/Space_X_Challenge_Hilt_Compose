package com.example.spacexchallenge.ui.states

import com.example.spacexchallenge.data.dto.LaunchDTO
import com.example.spacexchallenge.domain.models.LaunchInfo

sealed class LaunchDetailsUIStates {
    object Loading: LaunchDetailsUIStates()
    data class Success(val launch: LaunchInfo): LaunchDetailsUIStates()
    data class Error(val message: String): LaunchDetailsUIStates()
}