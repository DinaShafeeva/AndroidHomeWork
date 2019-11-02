package com.example.androidhomework

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_second.*

class SecondActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)
        tv_name_second_activity.text =
            "Name: " + intent?.extras?.getString(KEY_FILM_NAME) ?: "DEFAULT NAME"
        tv_producer_second_activity.text =
            "Producer: " + intent?.extras?.getString(KEY_FILM_PRODUCER) ?: "DEFAULT NAME"
        tv_description_second_activity.text =
            "Description: " + intent?.extras?.getString(KEY_FILM_DESCRIPTION) ?: "DEFAULT NAME"
        iv_second_activity.setImageResource(intent?.extras?.getInt(KEY_FILM_IMAGE) ?: 111)
    }

    companion object {
        private const val KEY_FILM_NAME = "name"
        private const val KEY_FILM_PRODUCER = "producer"
        private const val KEY_FILM_DESCRIPTION = "description"
        private const val KEY_FILM_IMAGE = "image"

        fun createIntent(
            activity: Activity,
            name: String,
            producer: String,
            description: String,
            image: Int
        ) =
            Intent(activity, SecondActivity::class.java).apply {
                putExtra(KEY_FILM_NAME, name)
                putExtra(KEY_FILM_PRODUCER, producer)
                putExtra(KEY_FILM_DESCRIPTION, description)
                putExtra(KEY_FILM_IMAGE, image)
            }
    }
}
