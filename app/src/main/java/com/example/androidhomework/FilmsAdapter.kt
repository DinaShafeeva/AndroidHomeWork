package com.example.androidhomework

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

class FilmsAdapter(
    private var filmList: List<Film>,
    private val clickLambda: (String, String, String, Int, Int, Film) -> Unit
) : RecyclerView.Adapter<FilmHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FilmHolder =
        FilmHolder.create(parent, clickLambda);

    override fun getItemCount(): Int = filmList.size;

    override fun onBindViewHolder(holder: FilmHolder, position: Int) =
        holder.bind(filmList[position])
}
