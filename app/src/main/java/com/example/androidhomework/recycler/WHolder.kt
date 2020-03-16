package com.example.androidhomework.recycler

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.androidhomework.R
import com.example.androidhomework.respone.Weather
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.item_weather.*


class WHolder(
    override val containerView: View,
    private val clickLambda: (Weather) -> Unit
) : RecyclerView.ViewHolder(containerView), LayoutContainer {


    fun bind(weather: Weather) {


        tv_city.text = weather.name
        if (weather.main.temp < firstBorder) {
            tv_temp.setTextColor(R.color.temp1)
        } else if (weather.main.temp >= firstBorder && weather.main.temp < secondBorder) {
            tv_temp.setTextColor(R.color.temp2)
        } else if (weather.main.temp >= secondBorder && weather.main.temp < thirdBorder) {
            tv_temp.setTextColor(R.color.temp3)
        } else if (weather.main.temp >= thirdBorder && weather.main.temp < fourthBorder) {
            tv_temp.setTextColor(R.color.temp4)
        } else tv_temp.setTextColor(R.color.temp5)

        tv_temp.text = weather.main.temp.toString()

        itemView.setOnClickListener {
            clickLambda(weather)
        }
    }

    companion object {
        const val firstBorder = -10.0
        const val secondBorder: Double = 0.0
        const val thirdBorder: Double = 1.0
        const val fourthBorder: Double = 10.0

        fun create(
            parent: ViewGroup,
            clickLambda: (Weather) -> Unit
        ) =
            WHolder(
                LayoutInflater.from(parent.context).inflate(R.layout.item_weather, parent, false),
                clickLambda
            )
    }
}
