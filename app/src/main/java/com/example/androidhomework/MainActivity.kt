package com.example.androidhomework

import android.content.Intent
import android.os.Bundle
import android.provider.Settings.Global.getString
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        button_reset.setOnClickListener(object : View.OnClickListener {
            override fun onClick(view: View): Unit {
                val intent = Intent(this@MainActivity, ResetPasswordActivity::class.java);
                startActivity(intent); }
        })

        button_login.setOnClickListener({ x -> onClick() });

        et_sign_in_pass.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                ti_sign_in_pass.error = null
                ti_sign_in_pass.isErrorEnabled = false
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            }

        })
    }

    fun onClick() {
        if (et_sign_in_pass.getText().toString() == PasswordRepository.password) {
            val intent = Intent(this, FinalActivity::class.java)
            startActivity(intent)
        }else setPasswordError()
    }

    fun setPasswordError() {
        ti_sign_in_pass.error = getString(R.string.validate_password)
    }
}
object PasswordRepository {
    var password: String = "111"
}




