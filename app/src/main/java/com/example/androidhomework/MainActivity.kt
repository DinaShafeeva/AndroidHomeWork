package com.example.androidhomework

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*

var index: Int? = 0;

class MainActivity : AppCompatActivity() {

    private var adapter: MusicAdapter? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        adapter = MusicAdapter(getDataSource()) { music ->
            navigateToSecondActivity(music)
            index = getDataSource().indexOf(music)
        }
        rv_music.adapter = adapter
    }

    private fun navigateToSecondActivity(music: Music) {
        startActivity(SecondActivity.createIntent(this, music))
    }


}

fun getDataSource(): ArrayList<Music> = arrayListOf(
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
