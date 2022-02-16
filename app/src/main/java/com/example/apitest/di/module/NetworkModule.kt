package com.example.apitest.di.module

import com.example.apitest.network.HttpService
import com.example.apitest.network.NullOnEmptyConverterFactory
import com.google.gson.Gson
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Qualifier
import javax.inject.Singleton

@Qualifier
annotation class ApiService

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {
    var BASE_URL = "http://testapi.super-brain.co.kr"
//    val httpLogger = HttpLoggingInterceptor().apply { level = HttpLoggingInterceptor.Level.BASIC }

    @ApiService
    @Provides
    @Singleton
    fun provideHttpService(): HttpService {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(ScalarsConverterFactory.create())
            .addConverterFactory(GsonConverterFactory.create(provideGson()))
            .addConverterFactory(NullOnEmptyConverterFactory())
            .client(provideOkHttpClient())
            .build()
            .create(HttpService::class.java)
    }

    @Provides
    fun provideOkHttpClient(): OkHttpClient = OkHttpClient
        .Builder()
//        .addInterceptor(httpLogger)
        //.authenticator(tokenAuthenticatorV2) // Called when access token or refresh token expired
        .connectTimeout(30, TimeUnit.SECONDS)
        .build()

    private fun provideGson() = Gson()
}