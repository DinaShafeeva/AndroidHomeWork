package com.example.androidhomework

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_second.*

class SecondActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)
        val intent: Intent = intent
        tv_open_second_activity.text = intent.extras?.get(Intent.EXTRA_TEXT).toString()
    }
}
