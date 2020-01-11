package com.example.androidhomework.dao

import com.example.androidhomework.recycler.Note
import java.util.*

class NoteSource {
    companion object {
        var noteList =
            getDataSource()

        fun getDataSource(): List<Note> = arrayListOf(
            Note(
                1,
                "name1",
                "desc",
                Calendar.DATE,
                1,
                2
            ),
            Note(
                2,
                "name2",
                "desc",
                Calendar.DATE,
                1,
                2
            ),
            Note(
                3,
                "name3",
                "desc",
                Calendar.DATE,
                1,
                2
            ),
            Note(
                4,
                "name4",
                "desc",
                Calendar.DATE,
                1,
                2
            ),
            Note(
                5,
                "name5",
                "desc",
                Calendar.DATE,
                1,
                2
            ),
            Note(
                6,
                "name6",
                "desc",
                Calendar.DATE,
                1,
                2
            ),
            Note(
                7,
                "name7",
                "desc",
                Calendar.DATE,
                1,
                2
            ),
            Note(
                8,
                "name8",
                "desc",
                Calendar.DATE,
                1,
                2
            ),
            Note(
                9,
                "name9",
                "desc",
                Calendar.DATE,
                1,
                2
            ),
            Note(
                10,
                "name10",
                "desc",
                Calendar.DATE,
                1,
                2
            )
        )
    }
}
