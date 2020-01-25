package com.example.androidhomework.dao

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.example.androidhomework.recycler.Note

class DataBaseHandler(
    context: Context?,
    name: String?,
    factory: SQLiteDatabase.CursorFactory?,
    version: Int
) : SQLiteOpenHelper(context, name, factory, version), IDatabaseHandler {

    private val TABLE_NOTES = "note"
    private val KEY_ID = "id"
    private val KEY_TITLE = "title"
    private val KEY_DESCRIPTION = "description"
    private val KEY_DATE = "date"
    private val KEY_LONGITUDE = "longitude"
    private val KEY_LATITUDE = "latitude"

    override fun onCreate(db: SQLiteDatabase?) {
        val CREATE_NOTES_TABLE = ("CREATE TABLE " + TABLE_NOTES + "("
                + KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," + KEY_TITLE + " TEXT,"
                + KEY_DESCRIPTION + " TEXT," + KEY_DATE + " DATE," + KEY_LONGITUDE + " DATE,"
                + KEY_LATITUDE + " INTEGER" + ")")
        db?.execSQL(CREATE_NOTES_TABLE)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db?.execSQL("DROP TABLE IF EXISTS " + TABLE_NOTES);
        onCreate(db);
    }

    override fun addNote(note: Note) {
        val db = this.writableDatabase
        val values = ContentValues()
        values.put(KEY_TITLE, note.title)
        values.put(KEY_DESCRIPTION, note.description)
        values.put(KEY_DATE, note.date)
        values.put(KEY_LONGITUDE, note.longitude)
        values.put(KEY_LATITUDE, note.latitude)

        db.insert(TABLE_NOTES, null, values)
        db.close()
    }

    override fun getNote(id: Int): Note? {
        val db = this.readableDatabase

        val cursor: Cursor? = db.query(
            TABLE_NOTES,
            arrayOf(
                KEY_ID, KEY_TITLE, KEY_DESCRIPTION, KEY_DATE, KEY_LONGITUDE, KEY_LATITUDE
            ),
            "$KEY_ID=?",
            arrayOf(java.lang.String.valueOf(id)),
            null,
            null,
            null,
            null
        )

        if (cursor != null) {
            cursor.moveToFirst()
        }
        if (cursor != null) {
            return Note(
                cursor.getInt(0),
                cursor.getString(1), cursor.getString(2),
                cursor.getString(3), cursor.getInt(4), cursor.getInt(5)
            )
        } else return null
    }

    override fun getAllNotes(): ArrayList<Note> {
        val noteList: ArrayList<Note> = ArrayList<Note>()
        val selectQuery = "SELECT  * FROM $TABLE_NOTES"
        val db = this.writableDatabase
        val cursor = db.rawQuery(selectQuery, null)

        if (cursor.moveToFirst()) {
            do {
                val note = Note(
                    cursor.getInt(0),
                    cursor.getString(1), cursor.getString(2),
                    cursor.getString(3), cursor.getInt(4), cursor.getInt(5)
                )
                noteList.add(note)
            } while (cursor.moveToNext())
        }
        return noteList
    }

    override fun updateNote(note: Note?): Int {
        val db = this.writableDatabase

        val values = ContentValues()
        values.put(KEY_TITLE, note?.title)
        values.put(KEY_DESCRIPTION, note?.description)
        values.put(KEY_DATE, note?.date)
        values.put(KEY_LONGITUDE, note?.longitude)
        values.put(KEY_LATITUDE, note?.latitude)

        return db.update(
            TABLE_NOTES,
            values,
            "$KEY_ID = ?",
            arrayOf(java.lang.String.valueOf(note?.id))
        )
    }

    override fun deleteNote(note: Note?) {
        val db = this.writableDatabase
        db.delete(
            TABLE_NOTES,
            "$KEY_ID = ?",
            arrayOf(java.lang.String.valueOf(note?.id))
        )
        db.close()
    }

    override fun deleteAll() {
        val db = this.writableDatabase
        db.delete(TABLE_NOTES, null, null)
        db.close()
    }
}
