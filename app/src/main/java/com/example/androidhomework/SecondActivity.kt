package com.example.androidhomework

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.androidhomework.dao.DataBaseHandler
import com.example.androidhomework.recycler.Note
import kotlinx.android.synthetic.main.activity_second.*
import java.util.*

class SecondActivity : AppCompatActivity() {
    val db = DataBaseHandler(this, DATABASE_NAME, null, DATABASE_VERSION)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)
        if (intent?.extras?.getInt(KEY_NOTE) != -1) {
            et_title.setText(
                intent?.extras?.getInt(KEY_NOTE)?.let { db.getNote(it)?.title },
                TextView.BufferType.EDITABLE
            )
            et_description.setText(intent?.extras?.getInt(KEY_NOTE)?.let {
                db.getNote(it)?.description
            }, TextView.BufferType.EDITABLE)
            et_date.setText(intent?.extras?.getInt(KEY_NOTE)?.let {
                db.getNote(it)?.date.toString()
            }, TextView.BufferType.EDITABLE)
            et_longitude.setText(intent?.extras?.getInt(KEY_NOTE)?.let {
                db.getNote(it)?.longitude.toString()
            }, TextView.BufferType.EDITABLE)
            et_latitude.setText(intent?.extras?.getInt(KEY_NOTE)?.let {
                db.getNote(it)?.latitude.toString()
            }, TextView.BufferType.EDITABLE)

        } else et_date.setText(Calendar.DAY_OF_YEAR.toString(), TextView.BufferType.EDITABLE)

        btn_save.setOnClickListener { onClick() }

    }

    private fun onClick() {
        val note = Note(
            1, et_title.text.toString(), et_description.text.toString(),
            et_date.text.toString().toInt(), et_longitude.text.toString().toInt(),
            et_latitude.text.toString().toInt()
        )
        if (intent?.extras?.getInt(KEY_NOTE) == -1) {
            db.addNote(note)
        } else {
            db.updateNote(note)
        }
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
