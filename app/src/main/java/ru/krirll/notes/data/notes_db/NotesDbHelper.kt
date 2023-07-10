package ru.krirll.notes.data.notes_db

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.provider.BaseColumns
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import ru.krirll.notes.domain.model.Note
import javax.inject.Inject

class NotesDbHelper @Inject constructor(context: Context) : SQLiteOpenHelper(
    context,
    DATABASE_NAME,
    null,
    DATABASE_VERSION
) {

    override fun onCreate(db: SQLiteDatabase?) {
        db?.execSQL(SQL_CREATE_NOTE_ENTRY)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db?.execSQL(SQL_DELETE_NOTE_ENTRY)
        onCreate(db)
    }

    fun getNotes(): Flow<List<Note>> {
        return flow {
            val projection = arrayOf(
                BaseColumns._ID,
                NotesReaderContract.NoteEntry.COLUMN_NAME_TITLE,
                NotesReaderContract.NoteEntry.COLUMN_NAME_CONTENT,
                NotesReaderContract.NoteEntry.COLUMN_NAME_TIMESTAMP,
                NotesReaderContract.NoteEntry.COLUMN_NAME_COLOR
            )
            val cursor = readableDatabase.query(
                NotesReaderContract.NoteEntry.TABLE_NAME,
                projection,
                null,
                null,
                null,
                null,
                null
            )
            val notes = mutableListOf<Note>()
            with(cursor) {
                while (moveToNext()) {
                    notes.add(
                        Note(
                            id = getInt(getColumnIndexOrThrow(BaseColumns._ID)),
                            title = getString(getColumnIndexOrThrow(NotesReaderContract.NoteEntry.COLUMN_NAME_TITLE)),
                            content = getString(getColumnIndexOrThrow(NotesReaderContract.NoteEntry.COLUMN_NAME_CONTENT)),
                            timestamp = getLong(getColumnIndexOrThrow(NotesReaderContract.NoteEntry.COLUMN_NAME_TIMESTAMP)),
                            color = getInt(getColumnIndexOrThrow(NotesReaderContract.NoteEntry.COLUMN_NAME_COLOR))
                        )
                    )
                }
                emit(notes)
            }
        }
    }

    fun getNoteById(id: Int): Note {
        val projection = arrayOf(
            BaseColumns._ID,
            NotesReaderContract.NoteEntry.COLUMN_NAME_TITLE,
            NotesReaderContract.NoteEntry.COLUMN_NAME_CONTENT,
            NotesReaderContract.NoteEntry.COLUMN_NAME_TIMESTAMP,
            NotesReaderContract.NoteEntry.COLUMN_NAME_COLOR
        )
        val selection = "CAST(${BaseColumns._ID} as TEXT) = ?"
        val selectionArgs = arrayOf(id.toString())
        val cursor = readableDatabase.query(
            NotesReaderContract.NoteEntry.TABLE_NAME,
            projection,
            selection,
            selectionArgs,
            null,
            null,
            null
        )
        val note = with(cursor) {
            moveToNext()
                Note(
                    id = getInt(getColumnIndexOrThrow(BaseColumns._ID)),
                    title = getString(getColumnIndexOrThrow(NotesReaderContract.NoteEntry.COLUMN_NAME_TITLE)),
                    content = getString(getColumnIndexOrThrow(NotesReaderContract.NoteEntry.COLUMN_NAME_CONTENT)),
                    timestamp = getLong(getColumnIndexOrThrow(NotesReaderContract.NoteEntry.COLUMN_NAME_TIMESTAMP)),
                    color = getInt(getColumnIndexOrThrow(NotesReaderContract.NoteEntry.COLUMN_NAME_COLOR))
                )
        }
        cursor.close()
        return note
    }

    fun insertOrUpdateNote(note: Note): Long {
        val values = ContentValues().apply {
            put(BaseColumns._ID, note.id)
            put(NotesReaderContract.NoteEntry.COLUMN_NAME_TITLE, note.title)
            put(NotesReaderContract.NoteEntry.COLUMN_NAME_CONTENT, note.content)
            put(NotesReaderContract.NoteEntry.COLUMN_NAME_TIMESTAMP, note.timestamp)
            put(NotesReaderContract.NoteEntry.COLUMN_NAME_COLOR, note.color)
        }

        return writableDatabase.insertWithOnConflict(
            NotesReaderContract.NoteEntry.TABLE_NAME,
            null,
            values,
            SQLiteDatabase.CONFLICT_REPLACE
        )
    }

    fun deleteNote(note: Note): Int =
        writableDatabase.delete(
            NotesReaderContract.NoteEntry.TABLE_NAME,
            "${BaseColumns._ID} = ?",
            arrayOf(note.id?.toString())
        )

    companion object {

        private const val DATABASE_NAME = "NoteEntry.db"
        private const val DATABASE_VERSION = 1

        private const val SQL_CREATE_NOTE_ENTRY =
            "CREATE TABLE ${NotesReaderContract.NoteEntry.TABLE_NAME} (" +
                    "${BaseColumns._ID} INTEGER PRIMARY KEY," +
                    "${NotesReaderContract.NoteEntry.COLUMN_NAME_TITLE} TEXT," +
                    "${NotesReaderContract.NoteEntry.COLUMN_NAME_CONTENT} TEXT," +
                    "${NotesReaderContract.NoteEntry.COLUMN_NAME_TIMESTAMP} INTEGER," +
                    "${NotesReaderContract.NoteEntry.COLUMN_NAME_COLOR} INTEGER)"

        private const val SQL_DELETE_NOTE_ENTRY =
            "DROP TABLE IF EXISTS ${NotesReaderContract.NoteEntry.TABLE_NAME}"
    }
}