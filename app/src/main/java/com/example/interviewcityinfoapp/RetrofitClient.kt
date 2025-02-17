package com.example.interviewcityinfoapp

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClient {

    fun provideCityInfoService(): CityInfoService {
        return Retrofit.Builder()
            .baseUrl(BuildConfig.API_KEY)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(CityInfoService::class.java)
    }

}