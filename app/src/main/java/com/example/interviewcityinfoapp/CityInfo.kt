package com.example.interviewcityinfoapp

import com.google.gson.annotations.SerializedName

data class CityInfo(
    @SerializedName("name")
    var name  : String,
    @SerializedName("state")
    var state : String,
    @SerializedName("id")
    var id    : Int
) {

}