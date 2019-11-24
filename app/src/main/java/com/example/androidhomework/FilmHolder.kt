package com.example.androidhomework

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.extensions.LayoutContainer

class FilmHolder(
    override val containerView: View,
    private val clickLambda: (Film) -> Unit
) : RecyclerView.ViewHolder(containerView), LayoutContainer {

    fun bind(film: Film) {
        tv_name_main.text = film.name
        tv_producer_main.text = film.producer
        tv_description_main.text = film.description
        iv_main.setImageResource(film.image)

        itemView.setOnClickListener {
            clickLambda(film)
        }
    }

    companion object {
        fun create(
            parent: ViewGroup,
            clickLambda: (Film) -> Unit
        ) =
            FilmHolder(
                LayoutInflater.from(parent.context).inflate(R.layout.item_film, parent, false),
                clickLambda
            )
    }
}
