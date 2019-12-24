package com.example.androidhomework

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Build
import androidx.core.app.NotificationCompat
import androidx.core.app.TaskStackBuilder
import com.example.androidhomework.MusicSource.Companion.mediaPlayer

class Notification {
    private var notificationId: Int = 1

    fun createPendingIntent(context: Context, music: Music): PendingIntent {
        val intent = Intent(context, SecondActivity::class.java)
        intent.putExtra("TITLE", music.title)
        intent.putExtra("SINGER", music.singer)
        intent.putExtra("GENRE", music.genre)
        intent.putExtra("POSTER", music.poster)
        intent.putExtra("SONG", music.song)

        TaskStackBuilder.create(context).apply {
            addParentStack(MainActivity::class.java)
            addNextIntent(intent)
        }
        return PendingIntent.getActivity(
            context,
            0,
            intent,
            0
        )
    }

    fun showNotification(notificationManager: NotificationManager, context: Context, music: Music) {
        createNotificationChannel(notificationManager)
        val contentPendingIntent = createPendingIntent(context, music)
        val pausePendingIntent = createPausePendingIntent(context)
        val prevPendingIntent = createPreviousSongPendingIntent(context)
        val nextPendingIntent = createNextSongPendingIntent(context)
        val builder = NotificationCompat.Builder(context, CHANNEL_ID)
            .setStyle(androidx.media.app.NotificationCompat.MediaStyle())
            .setSmallIcon(R.drawable.ic_notifications_black_24dp)
            .setContentTitle(music.title)
            .setPriority(NotificationCompat.PRIORITY_MAX)
            .setContentIntent(contentPendingIntent)
            .addAction(R.drawable.ic_prev, "Previous", prevPendingIntent)

        if (mediaPlayer.isPlaying) {
            builder.addAction(R.drawable.ic_pause, "Pause", pausePendingIntent)
        } else {
            builder.addAction(R.drawable.ic_play, "Pause", pausePendingIntent)
        }
        builder.addAction(R.drawable.ic_next, "Next", nextPendingIntent)
        val notification = builder.build()
        notificationManager.notify(notificationId, notification)
    }

    fun createNotificationChannel(notificationManager: NotificationManager) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val name = R.string.channel_name.toString()
            val descriptionText = R.string.channel_description.toString()
            val importance = NotificationManager.IMPORTANCE_DEFAULT
            val channel = notificationManager.getNotificationChannel(CHANNEL_ID)
                ?: NotificationChannel(CHANNEL_ID, name, importance).apply {
                    description = descriptionText
                }
            notificationManager.createNotificationChannel(channel)
        }
    }

    fun createPausePendingIntent(context: Context): PendingIntent {
        val intent = Intent(context, MyService::class.java)
        intent.action = Constans.PAUSE_ACTION
        return PendingIntent.getService(context, 0, intent, 0)
    }

    fun createNextSongPendingIntent(context: Context): PendingIntent {
        val intent = Intent(context, MyService::class.java)
        intent.action = Constans.NEXT_SONG_ACTION
        return PendingIntent.getService(context, 0, intent, 0)
    }

    fun createPreviousSongPendingIntent(context: Context): PendingIntent {
        val intent = Intent(context, MyService::class.java)
        intent.action = Constans.PREV_ACTION
        return PendingIntent.getService(context, 0, intent, 0)
    }

    companion object {
        private const val CHANNEL_ID = "1"
    }
}
