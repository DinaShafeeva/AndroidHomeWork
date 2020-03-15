package com.example.androidhomework

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_second.*
import kotlinx.coroutines.*

class SecondActivity : AppCompatActivity(), CoroutineScope by MainScope() {

    private lateinit var service: WeatherService

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)
        service = ApiFactory.weatherService

        launch {
            val response = withContext(context = Dispatchers.IO) {
                service.weatherById(intent.extras?.getInt(KEY_ID) ?: 0)
            }
            val direction: String = getDirection(response.wind.deg)
            tv_second_city.text = response.name
            tv_second_temp.text = response.main.temp.toString()
            tv_second_humidity.text = "Humidity: " + response.main.humidity
            tv_second_wind.text =
                "Wind: " + direction + " " + response.wind.speed.toString() + " m/s"
        }
    }

    private fun getDirection(deg: Int): String {
        if (deg >= 337.5 && deg <= 22.5) {
            return "N"
        } else if (deg > 22.5 && deg < 67.5) {
            return "NE"
        } else if (deg >= 67.5 && deg <= 112.5) {
            return "E"
        } else if (deg > 112.5 && deg < 157.5) {
            return "SE"
        } else if (deg >= 157.5 && deg <= 202.5) {
            return "S"
        } else if (deg > 202.5 && deg < 247.5) {
            return "SW"
        } else if (deg >= 247.5 && deg <= 292.5) {
            return "W"
        } else
            return "NW"
    }

    companion object {
        private const val KEY_ID = "id"
        fun createIntent(
            activity: Activity,
            id: Int
        ) =
            Intent(activity, SecondActivity::class.java).apply {
                putExtra(KEY_ID, id)
            }
    }
}
