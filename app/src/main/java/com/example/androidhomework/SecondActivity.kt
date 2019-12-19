package com.example.androidhomework

import android.annotation.SuppressLint
import android.app.Activity
import android.app.NotificationManager
import android.app.TaskStackBuilder
import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.ServiceConnection
import android.os.Bundle
import android.os.Handler
import android.os.IBinder
import android.view.MotionEvent
import android.view.View
import android.widget.Button
import android.widget.SeekBar
import androidx.appcompat.app.AppCompatActivity
import com.example.androidhomework.Constans.Companion.EXTRA_GENRE
import com.example.androidhomework.Constans.Companion.EXTRA_MUSIC
import com.example.androidhomework.Constans.Companion.EXTRA_POSITION
import com.example.androidhomework.Constans.Companion.EXTRA_POSTER
import com.example.androidhomework.Constans.Companion.EXTRA_SINGER
import com.example.androidhomework.Constans.Companion.EXTRA_SONG
import com.example.androidhomework.Constans.Companion.EXTRA_TITLE
import com.example.androidhomework.MusicSource.Companion.index
import com.example.androidhomework.MusicSource.Companion.mediaPlayer
import com.example.androidhomework.MusicSource.Companion.musicList
import kotlinx.android.synthetic.main.activity_second.*


class SecondActivity : AppCompatActivity() {

    private var buttonPlayStop: Button? = null

    private var seekBar: SeekBar? = null
    private var handler = Handler()
    private var myService: MyService? = null




    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)

        val title = intent?.extras?.getString(EXTRA_TITLE) ?: "DEFAULT NAME"
        val singer = "Singer: " + intent?.extras?.getString(EXTRA_SINGER) ?: "DEFAULT NAME"
        val genre = "Genre: " + intent?.extras?.getString(EXTRA_GENRE) ?: "DEFAULT NAME"
        val poster = intent?.extras?.getInt(EXTRA_POSTER) ?: 111
        val song = intent?.extras?.getInt(EXTRA_SONG) ?: 111
        val notification = Notification()
        val notificationManager =
            getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        val music = Music(title, singer, genre, poster, song)

        tv_title_second_activity.text = title

        tv_singer_second_activity.text = singer
        tv_genre_second_activity.text = genre

        iv_poster_second_activity.setImageResource(intent?.extras?.getInt(EXTRA_POSTER) ?: 111)

        // initViews()

        val stop = "stop"
        val start = "start"

        btn_play_stop.setOnClickListener {
            if (!mediaPlayer.isPlaying) {
                buttonPlayStop?.text = stop
                initViews()
                startPlayProgressUpdater()
            } else {
                buttonPlayStop?.text = start
            }
            myService?.pause()

            notification.showNotification(notificationManager, this, music)
        }
//        btn_play_stop.setOnClickListener {
//            if (buttonPlayStop?.getText() == "play") {
//                buttonPlayStop?.setText("stop")
//                try {
//
//                } catch (e: IllegalStateException) {
//                    mediaPlayer?.pause()
//                }
//            } else {
//                buttonPlayStop?.setText("play")
//                mediaPlayer?.pause()
//
//            }
//        }

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


    @SuppressLint("ClickableViewAccessibility")
    private fun initViews() {
        buttonPlayStop = btn_play_stop
        seekBar = sb_seekBar
        mediaPlayer.duration.let { seekBar?.setMax(it) }

        seekBar?.setOnTouchListener(object : View.OnTouchListener {
            override fun onTouch(v: View, event: MotionEvent): Boolean {
                seekChange(v)
                return false
            }
        });
    }

    private fun seekChange(v: View) {
        if (mediaPlayer.isPlaying) {
                val sb = v as SeekBar
            mediaPlayer.seekTo(sb.progress)
        }
    }

    //    @SuppressLint("SetTextI18n")
//    fun playAndStop(v: View) {
//        if (buttonPlayStop?.getText() == "play") {    /*(buttonPlayStop?.background == R.drawable.ic_play.toDrawable()){*/
//            buttonPlayStop?.setText("stop")
//            //  buttonPlayStop?.background = R.drawable.ic_pause.toDrawable()
//            try {
//                startService(Intent(this, MyService::class.java))
//                //  mediaPlayer?.start()
//                //  startPlayProgressUpdater()
//            } catch (e: IllegalStateException) {
//                stopService(Intent(this, MyService::class.java))
//                //     mediaPlayer?.pause()
//            }
//        } else {
//            buttonPlayStop?.setText("play")
//            //  buttonPlayStop?.background = R.drawable.ic_play.toDrawable()
//            //  mediaPlayer?.pause()
//            stopService(Intent(this, MyService::class.java))
//        }
//    }
//
    @SuppressLint("SetTextI18n")
    fun startPlayProgressUpdater() {
        mediaPlayer.getCurrentPosition().let { seekBar?.setProgress(it) }
        if (mediaPlayer.isPlaying) {
                val notification = Runnable { startPlayProgressUpdater() }
                handler.postDelayed(notification, 1000)
            } else {
            myService?.pause();
                buttonPlayStop?.setText("play");
                seekBar?.setProgress(0);
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
                putExtra(EXTRA_TITLE, music.title)
                putExtra(EXTRA_SINGER, music.singer)
                putExtra(EXTRA_GENRE, music.genre)
                putExtra(EXTRA_MUSIC, music.song)
                putExtra(EXTRA_POSTER, music.poster)
                putExtra(EXTRA_POSITION, index)
            }
            TaskStackBuilder.create(context).apply {
                addParentStack(MainActivity::class.java)
                addNextIntent(intent)
            }
            return intent
        }
    }
}