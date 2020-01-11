package com.example.androidhomework

import android.content.Context
import android.os.Bundle
import android.view.Window
import android.widget.RelativeLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.androidhomework.dao.NoteSource
import com.example.androidhomework.recycler.GridItemDecoration
import com.example.androidhomework.recycler.NoteAdapter
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {

    var adapter: NoteAdapter? = null
    var context: Context? = null
    var relativeLayout: RelativeLayout? = null
    var recyclerView: RecyclerView? = null //= rv_note
    var layoutManager: RecyclerView.LayoutManager? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        requestWindowFeature(Window.FEATURE_ACTION_BAR);
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        rv_note.layoutManager = GridLayoutManager(this, 2)

        rv_note.addItemDecoration(
            GridItemDecoration(
                10,
                2
            )
        )

        adapter =
            NoteAdapter(NoteSource.getDataSource()) { note ->
                navigateToSecondActivity(note.id)
            }
        rv_note.adapter = adapter


    }

    private fun navigateToSecondActivity(id: Int) {
        startActivity(SecondActivity.createIntent(this, id))
    }
}
