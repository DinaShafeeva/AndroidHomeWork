package com.example.androidhomework

import android.media.MediaPlayer

class MusicSource {
    companion object {

        var musicList =
            getDataSource()
        var index = -1
        var mediaPlayer = MediaPlayer()

        fun getDataSource(): List<Music> = arrayListOf(
            Music("АТ ВИН ТА", "Pin", "Chanson", R.drawable.otvinta, R.raw.otvinta),
            Music("Roots", "Imagine Dragons", "Rock", R.drawable.roots, R.raw.roots),
            Music("АТ ВИН ТА", "Pin", "Chanson", R.drawable.otvinta, R.raw.otvinta),
            Music("Roots", "Imagine Dragons", "Rock", R.drawable.roots, R.raw.roots),
            Music("АТ ВИН ТА", "Pin", "Chanson", R.drawable.otvinta, R.raw.otvinta),
            Music("Roots", "Imagine Dragons", "Rock", R.drawable.roots, R.raw.roots),
            Music("АТ ВИН ТА", "Pin", "Chanson", R.drawable.otvinta, R.raw.otvinta),
            Music("Roots", "Imagine Dragons", "Rock", R.drawable.roots, R.raw.roots),
            Music("АТ ВИН ТА", "Pin", "Chanson", R.drawable.otvinta, R.raw.otvinta),
            Music("Roots", "Imagine Dragons", "Rock", R.drawable.roots, R.raw.roots),
            Music("АТ ВИН ТА", "Pin", "Chanson", R.drawable.otvinta, R.raw.otvinta),
            Music("Roots", "Imagine Dragons", "Rock", R.drawable.roots, R.raw.roots),
            Music("АТ ВИН ТА", "Pin", "Chanson", R.drawable.otvinta, R.raw.otvinta),
            Music("Roots", "Imagine Dragons", "Rock", R.drawable.roots, R.raw.roots),
            Music("АТ ВИН ТА", "Pin", "Chanson", R.drawable.otvinta, R.raw.otvinta),
            Music("Roots", "Imagine Dragons", "Rock", R.drawable.roots, R.raw.roots)
        )
    }
}
