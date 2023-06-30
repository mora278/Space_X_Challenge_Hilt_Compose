package com.example.spacexchallenge.data.api.retrofit

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RetrofitModule {
    @Singleton
    @Provides
    fun provideRetrofit(): Retrofit = Retrofit
        .Builder()
        .baseUrl("https://api.spacexdata.com/v5/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    @Singleton
    @Provides
    fun provideRetrofitService(): RetrofitService = provideRetrofit().create(RetrofitService::class.java)
}