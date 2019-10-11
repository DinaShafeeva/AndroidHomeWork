package com.example.androidhomework

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_reset_password.*

class ResetPasswordActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_reset_password)
        button_reset_2.setOnClickListener{ onClick() };
    }
    fun onClick() {
        PasswordRepository.password = et_pass.getText().toString();
        onBackPressed()
        }
    }

