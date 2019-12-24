package com.example.androidhomework

import android.app.Activity
import android.app.NotificationManager
import android.app.TaskStackBuilder
import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.ServiceConnection
import android.os.Bundle
import android.os.IBinder
import androidx.appcompat.app.AppCompatActivity
import com.example.androidhomework.Constans.Companion.KEY_GENRE
import com.example.androidhomework.Constans.Companion.KEY_MUSIC
import com.example.androidhomework.Constans.Companion.KEY_POSITION
import com.example.androidhomework.Constans.Companion.KEY_POSTER
import com.example.androidhomework.Constans.Companion.KEY_SINGER
import com.example.androidhomework.Constans.Companion.KEY_SONG
import com.example.androidhomework.Constans.Companion.KEY_TITLE
import com.example.androidhomework.MusicSource.Companion.index
import com.example.androidhomework.MusicSource.Companion.mediaPlayer
import com.example.androidhomework.MusicSource.Companion.musicList
import kotlinx.android.synthetic.main.activity_second.*


class SecondActivity : AppCompatActivity() {
    private var myService: MyService? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)

        val title = intent?.extras?.getString(KEY_TITLE) ?: "DEFAULT NAME"
        val singer = "Singer: " + intent?.extras?.getString(KEY_SINGER)
        val genre = "Genre: " + intent?.extras?.getString(KEY_GENRE)
        val poster = intent?.extras?.getInt(KEY_POSTER) ?: 111
        val song = intent?.extras?.getInt(KEY_SONG) ?: 111
        val notification = Notification()
        val notificationManager =
            getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        val music = Music(title, singer, genre, poster, song)

        tv_title_second_activity.text = title
        tv_singer_second_activity.text = singer
        tv_genre_second_activity.text = genre

        iv_poster_second_activity.setImageResource(intent?.extras?.getInt(KEY_POSTER) ?: 111)

        btn_play_stop.setOnClickListener {
            if (!mediaPlayer.isPlaying) {
                btn_play_stop.text = "stop"
            } else {
                btn_play_stop.text = "start"
            }
            myService?.pause()
            notification.showNotification(notificationManager, this, music)
        }

        btn_next.setOnClickListener {
            myService?.nextSong(this)
            startActivity(createIntent(this, this, musicList[index], index))
            notification.showNotification(notificationManager, this, musicList[index])
        }

        btn_prev.setOnClickListener {
            myService?.previousSong(this)
            startActivity(createIntent(this, this, musicList[index], index))
            notification.showNotification(notificationManager, this, musicList[index])
        }
    }

    var mBound = false
    private val mConnection: ServiceConnection = object : ServiceConnection {
        override fun onServiceConnected(className: ComponentName, service: IBinder) {
            var binder = service as MyService.LocalBinder
            myService = binder.getService()
            mBound = true
        }

        override fun onServiceDisconnected(arg0: ComponentName) {
            mBound = false
        }
    }

    override fun onStart() {
        super.onStart()
        val intent = Intent(this, MyService::class.java)
        bindService(intent, mConnection, Context.BIND_AUTO_CREATE)
    }

    override fun onStop() {
        super.onStop()
        if (mBound) {
            unbindService(mConnection)
            mBound = false
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        if (mBound) {
            unbindService(mConnection)
        }
    }

    companion object {
        fun createIntent(activity: Activity, context: Context, music: Music, index: Int): Intent {
            val intent = Intent(activity, SecondActivity::class.java).apply {
                putExtra(KEY_TITLE, music.title)
                putExtra(KEY_SINGER, music.singer)
                putExtra(KEY_GENRE, music.genre)
                putExtra(KEY_MUSIC, music.song)
                putExtra(KEY_POSTER, music.poster)
                putExtra(KEY_POSITION, index)
            }
            TaskStackBuilder.create(context).apply {
                addParentStack(MainActivity::class.java)
                addNextIntent(intent)
            }
            return intent
        }
    }
}
