package com.example.androidhomework

import android.app.NotificationManager
import android.app.Service
import android.content.Context
import android.content.Intent
import android.media.MediaPlayer
import android.os.Binder
import android.os.IBinder
import com.example.androidhomework.Constans.Companion.EXTRA_ICON
import com.example.androidhomework.Constans.Companion.EXTRA_POSITION
import com.example.androidhomework.Constans.Companion.EXTRA_SONG
import com.example.androidhomework.MusicSource.Companion.index
import com.example.androidhomework.MusicSource.Companion.mediaPlayer
import com.example.androidhomework.MusicSource.Companion.musicList

class MyService : Service() {
    private var startId: Int = 1
    private val mBinder: IBinder? = LocalBinder()

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        super.onCreate()
        val notificationManager =
            getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        val notification = Notification()
        val music = intent?.extras?.getInt(EXTRA_SONG) ?: 1
        val index = intent?.extras?.getInt(EXTRA_POSITION) ?: 1

        when (intent?.action) {
            Constans.PAUSE_ACTION -> {
                pause()
                notification.showNotification(notificationManager, this, musicList[index])
            }
            Constans.NEXT_SONG_ACTION -> {
                nextSong(this)
                notification.showNotification(notificationManager, this, musicList[index])
            }
            Constans.PREV_ACTION -> {
                previousSong(this)
                notification.showNotification(notificationManager, this, musicList[index])
            }
        }
        return START_NOT_STICKY
    }

    override fun onBind(intent: Intent?): IBinder? {
        val position = intent?.extras?.getInt(EXTRA_POSITION) ?: 1
        val music = intent?.extras?.getInt(EXTRA_SONG) ?: 1
        intent?.putExtra(EXTRA_ICON, R.drawable.ic_pause)
        startSong(music, position)
        startId++
        return mBinder
    }

    inner class LocalBinder : Binder() {
        fun getService() = MyService()
    }

    fun startSong(music: Int, position: Int) {
        if (index != position) {
            mediaPlayer.release()
            mediaPlayer = MediaPlayer.create(this, music)
            mediaPlayer.start()
            index = position
        }
    }

    fun nextSong(context: Context) {
        mediaPlayer.release()
        if (index < MusicSource.musicList.size - 1) {
            index++

        } else {
            index = 0
            mediaPlayer = MediaPlayer.create(context, MusicSource.musicList[MusicSource.index].song)
        }
        mediaPlayer = MediaPlayer.create(context, MusicSource.musicList[MusicSource.index].song)
        mediaPlayer.start()
    }

    fun previousSong(context: Context) {
        mediaPlayer.release()
        if (index > 0) {
            index--
            mediaPlayer =
                MediaPlayer.create(context, musicList[index].song)
        } else {
            index = musicList.size - 1
            mediaPlayer =
                MediaPlayer.create(context, musicList[index].song)
        }
        mediaPlayer.start()
    }

    fun pause() {
        if (MusicSource.mediaPlayer.isPlaying) {
            mediaPlayer.pause()
        } else {
            mediaPlayer.start()
        }
    }
}
