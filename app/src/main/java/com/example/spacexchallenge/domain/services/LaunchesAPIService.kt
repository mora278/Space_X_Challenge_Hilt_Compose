package com.example.spacexchallenge.domain.services

import com.example.spacexchallenge.data.api.APIResult
import com.example.spacexchallenge.domain.models.LaunchInfo

interface LaunchesAPIService {
    suspend fun getLaunchByPage(page: Int): APIResult<List<LaunchInfo>>
    suspend fun getLaunchById(launchId: String): APIResult<LaunchInfo>
}