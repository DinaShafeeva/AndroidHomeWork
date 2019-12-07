package com.example.androidhomework

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Intent
import android.media.MediaPlayer
import android.os.Bundle
import android.os.Handler
import android.view.MotionEvent
import android.view.View
import android.widget.Button
import android.widget.SeekBar
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_second.*


class SecondActivity : AppCompatActivity() {

    private var buttonPlayStop: Button? = null
    private var mediaPlayer: MediaPlayer? = null
    private var seekBar: SeekBar? = null
    private var handler = Handler()
    private var service = MyService()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)

        tv_title_second_activity.text =
            intent?.extras?.getString(KEY_FILM_NAME) ?: "DEFAULT NAME"
        tv_singer_second_activity.text =
            "Singer: " + intent?.extras?.getString(KEY_FILM_SINGER) ?: "DEFAULT NAME"
        tv_genre_second_activity.text =
            "Genre: " + intent?.extras?.getString(KEY_FILM_GENRE) ?: "DEFAULT NAME"
        iv_poster_second_activity.setImageResource(intent?.extras?.getInt(KEY_FILM_POSTER) ?: 111)

        initViews()
    }

    @SuppressLint("ClickableViewAccessibility")
    private fun initViews() {
        buttonPlayStop = btn_play_stop
        mediaPlayer = MediaPlayer.create(this, intent?.extras?.getInt(KEY_FILM_SONG) ?: 111)
        seekBar = sb_seekBar
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

    override fun onDestroy() {
        super.onDestroy()
        stopService(Intent(this, MyService::class.java))
    }

    @SuppressLint("SetTextI18n")
    fun playAndStop(v: View) {
        if (buttonPlayStop?.getText() == "play") {    /*(buttonPlayStop?.background == R.drawable.ic_play.toDrawable()){*/
            buttonPlayStop?.setText("stop")
            //  buttonPlayStop?.background = R.drawable.ic_pause.toDrawable()
            try {
                startService(Intent(this, MyService::class.java))
                //  mediaPlayer?.start()
                //  startPlayProgressUpdater()
            } catch (e: IllegalStateException) {
                stopService(Intent(this, MyService::class.java))
                //     mediaPlayer?.pause()
            }
        } else {
            buttonPlayStop?.setText("play")
            //  buttonPlayStop?.background = R.drawable.ic_play.toDrawable()
            //  mediaPlayer?.pause()
            stopService(Intent(this, MyService::class.java))
        }
    }

    @SuppressLint("SetTextI18n")
    fun startPlayProgressUpdater() {
        mediaPlayer?.getCurrentPosition()?.let { seekBar?.setProgress(it) }
        val mp = mediaPlayer
        if (mp != null) {
            if (mp.isPlaying) {
                val notification = Runnable { startPlayProgressUpdater() }
                handler.postDelayed(notification, 1000)
            } else {
                mediaPlayer?.pause();
                buttonPlayStop?.setText("play");
                seekBar?.setProgress(0);
            }
        }
    }

    fun next(v: View) {
        if (index != null) {
            var i: Int = index as Int + 1
            startActivity(SecondActivity.createIntent(this, getDataSource().get(i)))
            if (i < getDataSource().size) {
                index = i++
            } else index = 0
        }
    }

    fun prev(v: View) {
        if (index != null) {
            var i: Int = index as Int - 1
            startActivity(SecondActivity.createIntent(this, getDataSource().get(i)))
            if (i > 0) {
                index = i--
            } else index = getDataSource().size
        }
    }

    companion object {
        private const val KEY_FILM_NAME = "name"
        private const val KEY_FILM_SINGER = "producer"
        private const val KEY_FILM_GENRE = "description"
        private const val KEY_FILM_POSTER = "image"
        private const val KEY_FILM_SONG = "song"

        fun createIntent(
            activity: Activity,
            music: Music
        ) =
            Intent(activity, SecondActivity::class.java).apply {
                putExtra(KEY_FILM_NAME, music.title)
                putExtra(KEY_FILM_SINGER, music.singer)
                putExtra(KEY_FILM_GENRE, music.genre)
                putExtra(KEY_FILM_POSTER, music.poster)
                putExtra(KEY_FILM_SONG, music.song)
            }
    }
}