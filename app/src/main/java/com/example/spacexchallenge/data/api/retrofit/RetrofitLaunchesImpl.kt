package com.example.spacexchallenge.data.api.retrofit

import com.example.spacexchallenge.data.api.APIResult
import com.example.spacexchallenge.data.utils.Mapper.toDomain
import com.example.spacexchallenge.domain.models.LaunchInfo
import com.example.spacexchallenge.domain.services.LaunchesAPIService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class RetrofitLaunchesImpl(private val retrofitService: RetrofitService) : LaunchesAPIService {
    override suspend fun getLaunchByPage(page: Int): APIResult<List<LaunchInfo>> =
        withContext(Dispatchers.IO) {
            val errorBuilder = StringBuilder()
            runCatching {
                retrofitService.getLaunchesByPage(RetrofitHelper.createBodyForPaging(page))
            }.onSuccess { result ->
                result.body()?.data?.map { it.toDomain() }?.let { launches ->
                    return@withContext APIResult.Success(launches)
                } ?: errorBuilder.append(APIResult.ERROR_BODY_RESULT_EMPTY)
            }.onFailure { errorBuilder.append(getError(it)) }
            return@withContext getAPIResultError(errorBuilder.toString())
        }

    override suspend fun getLaunchById(launchId: String): APIResult<LaunchInfo> =
        withContext(Dispatchers.IO) {
            val errorBuilder = StringBuilder()
            runCatching {
                retrofitService.getLaunchById(launchId)
            }.onSuccess { result ->
                result.body()?.toDomain()?.let { singleLaunch ->
                    return@withContext APIResult.Success(singleLaunch)
                } ?: errorBuilder.append(APIResult.ERROR_BODY_RESULT_EMPTY)
            }.onFailure { errorBuilder.append(getError(it)) }
            return@withContext getAPIResultError(errorBuilder.toString())
        }

    private fun getError(result: Throwable): String = result.message ?: ""

    private fun <T> getAPIResultError(error: String): APIResult<T> =
        APIResult.Error(message = "${RetrofitLaunchesImpl::class.simpleName}: $error")
}