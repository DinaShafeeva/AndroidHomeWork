package com.example.androidhomework

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

class SecondActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)

    }

    companion object {
        private const val KEY_NOTE = "note"

        fun createIntent(
            activity: Activity,
            id: Int
        ) =
            Intent(activity, SecondActivity::class.java).apply {
                putExtra(KEY_NOTE, id)
            }
    }
}
