package com.example.androidhomework

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

class MusicAdapter(
    private var musicList: List<Music>,
    private val clickLambda: (Music) -> Unit
) : RecyclerView.Adapter<MusicHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MusicHolder =
        MusicHolder.create(parent, clickLambda);

    override fun getItemCount(): Int = musicList.size;

    override fun onBindViewHolder(holder: MusicHolder, position: Int) =
        holder.bind(musicList[position])
}