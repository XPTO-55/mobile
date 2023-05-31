package com.example.cpaweb.rest

import AuthInterceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


object Api {
    private val retrofit: Retrofit =
            Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl("http://10.18.7.89:7000/")
                .client(
                    OkHttpClient.Builder()
                        .addInterceptor(AuthInterceptor())
                        .build()
                )
                .build()

    fun <T> createService(serviceClass:Class<T>):T {
        return retrofit.create(serviceClass)
    }
}