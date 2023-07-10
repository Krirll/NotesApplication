package ru.krirll.notes.data.notes_db

import android.provider.BaseColumns

object NotesReaderContract {

    object NoteEntry : BaseColumns {
        const val TABLE_NAME = "notes"
        const val COLUMN_NAME_TITLE = "title"
        const val COLUMN_NAME_CONTENT = "content"
        const val COLUMN_NAME_TIMESTAMP = "timestamp"
        const val COLUMN_NAME_COLOR = "color"
    }
}