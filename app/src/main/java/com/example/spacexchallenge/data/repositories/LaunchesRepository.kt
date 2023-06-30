package com.example.spacexchallenge.data.repositories

import com.example.spacexchallenge.data.api.APIResult
import com.example.spacexchallenge.domain.models.LaunchInfo
import com.example.spacexchallenge.domain.services.LaunchesAPIService
import javax.inject.Inject

class LaunchesRepository @Inject constructor(
    private val launchesAPIService: LaunchesAPIService
) {
    suspend fun getLaunchByPage(page: Int): List<LaunchInfo> =
        when (val result = launchesAPIService.getLaunchByPage(page)) {
            is APIResult.Error -> throw Throwable(result.message)
            is APIResult.Success -> result.data
        }

    suspend fun getLaunchById(launchId: String): LaunchInfo =
        when (val result = launchesAPIService.getLaunchById(launchId)) {
            is APIResult.Error -> throw Throwable(result.message)
            is APIResult.Success -> result.data
        }
}