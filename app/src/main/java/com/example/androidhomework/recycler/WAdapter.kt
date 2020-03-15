package com.example.androidhomework.recycler

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.androidhomework.respone.Weather

class WAdapter(
    var weatherList: List<Weather>,
    private val clickLambda: (Weather) -> Unit
) : RecyclerView.Adapter<WHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WHolder =
        WHolder.create(
            parent,
            clickLambda
        );

    override fun getItemCount(): Int = weatherList.size;

    override fun onBindViewHolder(holder: WHolder, position: Int) =
        holder.bind(weatherList[position])
}
