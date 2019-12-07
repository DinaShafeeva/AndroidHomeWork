package com.example.androidhomework

import android.annotation.SuppressLint
import android.app.Service
import android.content.Intent
import android.media.MediaPlayer
import android.os.Handler
import android.os.IBinder
import android.view.MotionEvent
import android.view.View
import android.widget.Button
import android.widget.SeekBar
import android.widget.Toast
import androidx.annotation.Nullable


class MyService : Service() {
    private var mediaPlayer: MediaPlayer? = null
    private var buttonPlayStop: Button? = null
    private var seekBar: SeekBar? = null
    private var handler = Handler()

    @SuppressLint("ClickableViewAccessibility")
    override fun onCreate() {
        super.onCreate()
        Toast.makeText(
            this, "Служба создана",
            Toast.LENGTH_SHORT
        ).show()
        mediaPlayer = MediaPlayer.create(this, R.raw.roots)
        mediaPlayer?.setLooping(false)

        mediaPlayer?.duration?.let { seekBar?.setMax(it) }
        seekBar?.setOnTouchListener(object : View.OnTouchListener {
            override fun onTouch(v: View, event: MotionEvent): Boolean {
                seekChange(v)
                return false
            }
        });
    }

    private fun seekChange(v: View) {
        val mp = mediaPlayer
        if (mp != null) {
            if (mp.isPlaying) {
                val sb = v as SeekBar
                mediaPlayer?.seekTo(sb.progress)
            }
        }
    }


    override fun onStartCommand(intent: Intent, flags: Int, startId: Int): Int {
        Toast.makeText(
            this, "Служба запущена",
            Toast.LENGTH_SHORT
        ).show()
        mediaPlayer?.start()
        return super.onStartCommand(intent, flags, startId)
    }


    fun onPause() {
        Toast.makeText(
            this, "Песня остановлена",
            Toast.LENGTH_SHORT
        ).show()
        mediaPlayer?.stop()
        //pause();
    }

    override fun onDestroy() {
        super.onDestroy()
        Toast.makeText(
            this, "Служба остановлена",
            Toast.LENGTH_SHORT
        ).show()
        mediaPlayer?.stop()
    }

    @Nullable
    override fun onBind(intent: Intent): IBinder? {
        return null
    }
}
