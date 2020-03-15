package com.example.androidhomework.recycler

import com.example.androidhomework.respone.Weather
import com.google.gson.annotations.SerializedName

data class WeatherDTO(
    @SerializedName("message")
    var message: String,
    @SerializedName("accurate")
    var accurate: String,
    @SerializedName("cod")
    var cod: String,
    @SerializedName("count")
    var count: String,
    @SerializedName("list")
    var list: List<Weather>
)