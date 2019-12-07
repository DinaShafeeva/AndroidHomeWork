package com.example.androidhomework

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.music_item.*

class MusicHolder(
    override val containerView: View,
    private val clickLambda: (Music) -> Unit
) : RecyclerView.ViewHolder(containerView), LayoutContainer {

    fun bind(music: Music) {
        val singer_genre = music.singer + " " + music.genre

        tv_title.text = music.title ?: "Unknown"
        tv_singer_and_genre.text = singer_genre
        iv_poster.setImageResource(music.poster)

        itemView.setOnClickListener {
            clickLambda(music)
        }
    }

    companion object {
        fun create(
            parent: ViewGroup,
            clickLambda: (Music) -> Unit
        ) =
            MusicHolder(
                LayoutInflater.from(parent.context).inflate(R.layout.music_item, parent, false),
                clickLambda
            )
    }
}