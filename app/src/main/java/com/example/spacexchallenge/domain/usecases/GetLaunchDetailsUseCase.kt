package com.example.spacexchallenge.domain.usecases

import com.example.spacexchallenge.data.repositories.LaunchesRepository
import com.example.spacexchallenge.domain.models.LaunchInfo
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetLaunchDetailsUseCase @Inject constructor(
    private val launchesRepository: LaunchesRepository
) {
    suspend operator fun invoke(launchId: String): Flow<LaunchInfo> = flow {
        emit(launchesRepository.getLaunchById(launchId))
    }
}