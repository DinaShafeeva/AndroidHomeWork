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
        var wind: String
        if (deg >= wind8 && deg <= wind1) {
            wind = "N"
        } else if (deg > wind1 && deg < wind2) {
            wind = "NE"
        } else if (deg >= wind2 && deg <= wind3) {
            wind = "E"
        } else if (deg > wind3 && deg < wind4) {
            wind = "SE"
        } else if (deg >= wind4 && deg <= wind5) {
            wind = "S"
        } else if (deg > wind5 && deg < wind6) {
            wind = "SW"
        } else if (deg >= wind6 && deg <= wind7) {
            wind = "W"
        } else
            wind = "NW"
        return wind
    }

    companion object {
        const val wind1 = 22.5
        const val wind2 = 67.5
        const val wind3 = 112.5
        const val wind4 = 157.5
        const val wind5 = 202.5
        const val wind6 = 247.5
        const val wind7 = 292.5
        const val wind8 = 337.5

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
