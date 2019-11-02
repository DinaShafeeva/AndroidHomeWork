package com.example.androidhomework

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.item_film.*


class FilmHolder(
    override val containerView: View,
    private val clickLambda: (String, String, String, Int, Int, Film) -> Unit
) : RecyclerView.ViewHolder(containerView), LayoutContainer {

    fun bind(film: Film) {
        tv_name_main.text = film.name
        tv_producer_main.text = film.producer
        tv_description_main.text = film.description
        iv_main.setImageResource(film.image)

        itemView.setOnClickListener {
            clickLambda(film.name, film.producer, film.description, film.image, 62, film)
        }
    }

    companion object {
        fun create(
            parent: ViewGroup,
            clickLambda: (String, String, String, Int, Int, Film) -> Unit
        ) =
            FilmHolder(
                LayoutInflater.from(parent.context).inflate(R.layout.item_film, parent, false),
                clickLambda
            )
    }
}
