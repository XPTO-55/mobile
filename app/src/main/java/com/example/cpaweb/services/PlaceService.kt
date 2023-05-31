package com.example.cpaweb.services

import com.example.cpaweb.models.places.Place
import com.example.cpaweb.models.users.professionals.Professional
import retrofit2.Call
import retrofit2.http.GET

interface PlaceService {
    @GET("/places")
    fun getPlaces(): Call<List<Place>>
}