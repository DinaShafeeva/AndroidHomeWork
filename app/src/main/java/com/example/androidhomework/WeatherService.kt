package com.example.androidhomework

import com.example.androidhomework.recycler.WeatherDTO
import com.example.androidhomework.respone.Weather
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherService {
    //https://api.openweathermap.org/data/2.5/
    // weather?q=Kazan&appid=baab15c756ebdf17914576272126c8aa
    @GET("weather")
    suspend fun weatherByName(@Query("q") name: String): Weather

    @GET("weather")
    suspend fun weatherById(@Query("id") id: Int): Weather

    @GET("find")
    suspend fun weatherByLatLon(
        @Query("lat") lat: Double, @Query("lon") lon: Double,
        @Query("cnt") count: Int
    ): WeatherDTO
}
