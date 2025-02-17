package com.example.interviewcityinfoapp

import retrofit2.Response
import retrofit2.http.GET

interface CityInfoService {

    @GET("cities.json")
    suspend fun getCityInfo(): Response<List<CityInfo>>

}