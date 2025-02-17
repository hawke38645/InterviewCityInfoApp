package com.example.interviewcityinfoapp

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class CityInfoRepository() {

    suspend fun getCityInfoList(): List<CityInfo> {
        return withContext(Dispatchers.IO) {
            RetrofitClient.provideCityInfoService().getCityInfo().body() ?: emptyList()
        }
    }
}