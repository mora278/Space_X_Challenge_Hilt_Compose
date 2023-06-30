package com.example.spacexchallenge.ui.states

import androidx.paging.PagingData
import com.example.spacexchallenge.data.dto.LaunchDTO
import com.example.spacexchallenge.domain.models.LaunchInfo
import kotlinx.coroutines.flow.StateFlow

sealed class HomeUIStates {
    object Loading: HomeUIStates()
    data class Success(val launchesPagingData: StateFlow<PagingData<LaunchInfo>>): HomeUIStates()
    data class Error(val message: String): HomeUIStates()
}