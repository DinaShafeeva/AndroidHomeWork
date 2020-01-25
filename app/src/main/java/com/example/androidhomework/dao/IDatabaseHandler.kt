package com.example.androidhomework.dao

import com.example.androidhomework.recycler.Note

interface IDatabaseHandler {
    fun addNote(note: Note)
    fun getNote(id: Int): Note?
    fun getAllNotes(): List<Note>
    fun updateNote(note: Note?): Int
    fun deleteNote(note: Note?)
    fun deleteAll()
}
