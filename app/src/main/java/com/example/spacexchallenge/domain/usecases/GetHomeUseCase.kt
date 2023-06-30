package com.example.spacexchallenge.domain.usecases

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.spacexchallenge.data.paging.LaunchesPagingSource
import com.example.spacexchallenge.data.repositories.LaunchesRepository
import com.example.spacexchallenge.domain.models.LaunchInfo
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetHomeUseCase @Inject constructor(
    private val launchesRepository: LaunchesRepository
) {
    operator fun invoke(): Flow<PagingData<LaunchInfo>> = Pager(
        config = PagingConfig(pageSize = Integer.MAX_VALUE),
        initialKey = 1,
        pagingSourceFactory = {
            LaunchesPagingSource(launchesRepository)
        }
    ).flow
}