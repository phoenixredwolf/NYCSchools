package com.phoenixredwolf.barretvogtmannycschools.network

import com.phoenixredwolf.barretvogtmannycschools.BuildConfig
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

object SchoolApi {

    private const val BASE_URL = "https://data.cityofnewyork.us/resource/"

    private val moshi = Moshi.Builder().add(KotlinJsonAdapterFactory()).build()

    private val logger = HttpLoggingInterceptor()

    private val httpClient = if (BuildConfig.DEBUG) {
        OkHttpClient.Builder().apply {
            addInterceptor{
                    chain ->
                val builder = chain.request().newBuilder()
                builder.header("X-App-Token", BuildConfig.APP_TOKEN)
                return@addInterceptor chain.proceed(builder.build())
            }
            logger.level = HttpLoggingInterceptor.Level.BODY
            addNetworkInterceptor(logger)
        }.build()
    } else {
        OkHttpClient.Builder().apply {
            addInterceptor{
                    chain ->
                val builder = chain.request().newBuilder()
                builder.header("X-App-Token", BuildConfig.APP_TOKEN)
                return@addInterceptor chain.proceed(builder.build())
            }
        }.build()
    }

    private val retrofit = Retrofit
        .Builder()
        .client(httpClient)
        .baseUrl(BASE_URL)
        .addConverterFactory(MoshiConverterFactory.create(moshi))
        .build()

    val retrofitService: SchoolDataService by lazy {
        retrofit.create(SchoolDataService::class.java)
    }
}