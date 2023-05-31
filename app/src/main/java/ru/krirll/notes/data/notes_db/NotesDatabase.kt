package ru.krirll.notes.data.notes_db

import androidx.room.Database
import androidx.room.RoomDatabase
import ru.krirll.notes.domain.model.Note

@Database(
    entities = [Note::class],
    version = 1,
    exportSchema = false
)
abstract class NotesDatabase: RoomDatabase() {

    abstract val notesDao: NotesDao

    companion object {
        const val DATABASE_NAME = "notes_db"
    }
}