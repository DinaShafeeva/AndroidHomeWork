package com.example.androidhomework

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.Window
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import com.example.androidhomework.dao.DataBaseHandler
import com.example.androidhomework.recycler.GridItemDecoration
import com.example.androidhomework.recycler.Note
import com.example.androidhomework.recycler.NoteAdapter
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.note_item.*


val DATABASE_VERSION = 1
val DATABASE_NAME = "noteBD"

class MainActivity : AppCompatActivity() {

    var adapter: NoteAdapter? = null
    val db = DataBaseHandler(this, DATABASE_NAME, null, DATABASE_VERSION)

    override fun onCreate(savedInstanceState: Bundle?) {
        requestWindowFeature(Window.FEATURE_ACTION_BAR);
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        rv_note.layoutManager = GridLayoutManager(this, 3)

        val notes: ArrayList<Note> = db.getAllNotes()

        rv_note.addItemDecoration(
            GridItemDecoration(
                10,
                2
            )
        )

        adapter =
            NoteAdapter(notes) { note ->
                navigateToSecondActivity(note.id)
                btn_delete.setOnClickListener { delete(note) }
            }
        rv_note.adapter = adapter

        btn_add_note.setOnClickListener { navigateToSecondActivity(-1) }


    }

    private fun delete(note: Note) {
        db.deleteNote(note)
        adapter?.updateList(db.getAllNotes())
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.getItemId() == R.id.btn_delete_all) {
            db.deleteAll()
            adapter?.updateList(db.getAllNotes())
            return true
        }
        return super.onOptionsItemSelected(item)
    }

    private fun onClick() {
        startActivity(SecondActivity.createIntent(this, -1))
    }

    private fun navigateToSecondActivity(id: Int) {
        startActivity(SecondActivity.createIntent(this, id))
    }
}
