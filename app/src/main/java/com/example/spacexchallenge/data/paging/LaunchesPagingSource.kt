package com.example.spacexchallenge.data.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.spacexchallenge.domain.models.APIResult
import com.example.spacexchallenge.domain.models.LaunchInfo
import com.example.spacexchallenge.domain.services.LaunchesAPIService

class LaunchesPagingSource(
    private val launchesAPIService: LaunchesAPIService
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
            when(val result = launchesAPIService.getLaunchByPage(page)) {
                is APIResult.Error -> throw Throwable(result.message)
                is APIResult.Success -> {
                    LoadResult.Page(
                        data = result.data,
                        prevKey = null,
                        nextKey = if (result.data.isEmpty()) null else page.inc()
                    )
                }
            }
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }

}

