package com.example.androidhomework.recycler

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

class NoteAdapter(
    private var noteList: ArrayList<Note>,
    private val clickLambda: (Note) -> Unit
) : RecyclerView.Adapter<NoteHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteHolder =
        NoteHolder.create(
            parent,
            clickLambda
        );

    override fun getItemCount(): Int = noteList.size;

    override fun onBindViewHolder(holder: NoteHolder, position: Int) =
        holder.bind(noteList[position])

    fun updateList(newList: ArrayList<Note>) {
        androidx.recyclerview.widget.DiffUtil.calculateDiff(
            DiffUtil(this.noteList, newList),
            true
        )
            .dispatchUpdatesTo(this)
        this.noteList.clear()
        this.noteList.addAll(newList)
    }
}
