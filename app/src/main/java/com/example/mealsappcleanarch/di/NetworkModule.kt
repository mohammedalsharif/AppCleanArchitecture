package com.example.mealsappcleanarch.di

import com.example.data.remote.ApiServices
import dagger.Component
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Singleton
    @Provides
    fun provideOkHttp(): OkHttpClient {
        return OkHttpClient.Builder()
            .writeTimeout(20, TimeUnit.SECONDS)
            .readTimeout(20, TimeUnit.SECONDS)
            .build()
    }

    @Singleton
    @Provides
    fun provideRetrofit(httpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder().client(httpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl("https://www.themealdb.com/api/json/v1/1/")
            .build()
    }

    @Singleton
    @Provides
    fun provideApiService(retrofit: Retrofit):ApiServices{
        return retrofit.create(ApiServices::class.java)

    }
}