package com.example.spacexchallenge.data.network

import android.content.Context
import com.example.spacexchallenge.data.api.retrofit.RetrofitLaunchesImpl
import com.example.spacexchallenge.domain.services.LaunchesAPIService
import com.example.spacexchallenge.data.api.retrofit.RetrofitService
import com.example.spacexchallenge.domain.services.URIHandlerService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {
    @Singleton
    @Provides
    fun providesURIHandler(@ApplicationContext context: Context): URIHandlerService =
        URIHandlerServiceImpl(context)

    @Singleton
    @Provides
    fun providesLaunchesService(retrofitService: RetrofitService): LaunchesAPIService =
        RetrofitLaunchesImpl(retrofitService)
}

//class VolleyImpl(): LaunchesAPIService {
//    override suspend fun getLaunchByPage(page: Int): APIResult<List<LaunchDTO>> {
//        TODO("Not yet implemented")
//    }
//
//    override suspend fun getLaunchById(launchId: String): APIResult<LaunchDTO> {
//        TODO("Not yet implemented")
//    }
//}