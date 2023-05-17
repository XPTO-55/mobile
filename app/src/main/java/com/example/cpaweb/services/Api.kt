package com.example.cpaweb.services

import AuthInterceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create


object Api {
    private val retrofit: Retrofit =
            Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl("http://192.168.15.20:7000/")
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