package com.example.androidhomework.recycler

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.androidhomework.R
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.note_item.*

class NoteHolder(
    override val containerView: View,
    private val clickLambda: (Note) -> Unit
) : RecyclerView.ViewHolder(containerView), LayoutContainer {

    fun bind(note: Note) {
        tv_title.text = note.title ?: "Unknown"

        itemView.setOnClickListener {
            clickLambda(note)
        }
    }

    companion object {
        fun create(
            parent: ViewGroup,
            clickLambda: (Note) -> Unit
        ) =
            NoteHolder(
                LayoutInflater.from(parent.context).inflate(
                    R.layout.note_item,
                    parent,
                    false
                ),
                clickLambda
            )
    }
}