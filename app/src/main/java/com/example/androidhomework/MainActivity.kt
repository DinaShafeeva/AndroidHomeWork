package com.example.androidhomework

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.androidhomework.MusicSource.Companion.index
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private var adapter: MusicAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        adapter = MusicAdapter(MusicSource.getDataSource()) { music ->
            navigateToSecondActivity(music)
            index = MusicSource.getDataSource().indexOf(music)
        }
        rv_music.adapter = adapter
    }

    private fun navigateToSecondActivity(music: Music) {
        //MyService.startService(this, musicList.indexOf(music), music)
        startActivity(SecondActivity.createIntent(this, this, music, index))
    }
}
