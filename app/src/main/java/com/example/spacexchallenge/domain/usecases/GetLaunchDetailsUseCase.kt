package com.example.spacexchallenge.domain.usecases

import com.example.spacexchallenge.domain.models.APIResult
import com.example.spacexchallenge.domain.models.LaunchInfo
import com.example.spacexchallenge.domain.services.LaunchesAPIService
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetLaunchDetailsUseCase @Inject constructor(
    private val launchesAPIService: LaunchesAPIService
) {
    suspend operator fun invoke(launchId: String): Flow<LaunchInfo> = flow {
        when (val result = launchesAPIService.getLaunchById(launchId)) {
            is APIResult.Error -> throw Throwable(result.message)
            is APIResult.Success -> emit(result.data)
        }
    }
}