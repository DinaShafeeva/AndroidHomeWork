package com.example.androidhomework

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.androidhomework.dao.DataBaseHandler
import com.example.androidhomework.recycler.Note
import kotlinx.android.synthetic.main.activity_second.*

class SecondActivity : AppCompatActivity() {
    val db = DataBaseHandler(this, DATABASE_NAME, null, DATABASE_VERSION)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)
        if (intent?.extras?.getInt(KEY_NOTE) != -1) {
            et_title.hint = intent?.extras?.getInt(KEY_NOTE)?.let { db.getNote(it)?.title }
            et_description.hint =
                intent?.extras?.getInt(KEY_NOTE)?.let { db.getNote(it)?.description }
            et_date.hint = intent?.extras?.getInt(KEY_NOTE)?.let { db.getNote(it)?.date.toString() }
            et_longitude.hint =
                intent?.extras?.getInt(KEY_NOTE)?.let { db.getNote(it)?.longitude.toString() }
            et_latitude.hint =
                intent?.extras?.getInt(KEY_NOTE)?.let { db.getNote(it)?.latitude.toString() }


        }
        btn_save.setOnClickListener { onClick() }

    }

    private fun onClick() {

        val note = Note(
            1, et_title.text.toString(), et_description.text.toString(),
            et_date.text.toString().toInt(), et_longitude.text.toString().toInt(),
            et_latitude.text.toString().toInt()
        )
        db.addNote(note)
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)

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
