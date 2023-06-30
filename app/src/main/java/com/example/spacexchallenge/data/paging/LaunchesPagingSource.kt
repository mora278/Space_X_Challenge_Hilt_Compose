package com.example.spacexchallenge.data.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.spacexchallenge.data.repositories.LaunchesRepository
import com.example.spacexchallenge.domain.models.LaunchInfo

class LaunchesPagingSource(
    private val launchesRepository: LaunchesRepository
) : PagingSource<Int, LaunchInfo>() {
    override fun getRefreshKey(state: PagingState<Int, LaunchInfo>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.inc() ?: anchorPage?.nextKey?.dec()
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, LaunchInfo> {
        return try {
            val page = params.key ?: 1
            val launches = launchesRepository.getLaunchByPage(page)
            LoadResult.Page(
                data = launches,
                prevKey = null,
                nextKey = if (launches.isEmpty()) null else page.inc()
            )
        } catch (e: Exception) {
            println("Error: $e")
            LoadResult.Error(e)
        }
    }

}

