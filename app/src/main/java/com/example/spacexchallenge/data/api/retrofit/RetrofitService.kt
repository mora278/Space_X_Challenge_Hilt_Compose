package com.example.spacexchallenge.data.api.retrofit

import com.example.spacexchallenge.data.dto.LaunchDTO
import com.example.spacexchallenge.data.dto.PostResponseDTO
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface RetrofitService {
    @GET("launches/{launchId}")
    suspend fun getLaunchById(@Path("launchId") launchId: String): Response<LaunchDTO>

    @POST("launches/query")
    suspend fun getLaunchesByPage(@Body body: TestOptionsBody): Response<PostResponseDTO>
}