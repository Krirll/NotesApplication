package ru.krirll.notes.data.repository

import kotlinx.coroutines.flow.Flow
import ru.krirll.notes.data.notes_db.NotesDbHelper
import ru.krirll.notes.domain.model.Note
import ru.krirll.notes.domain.repository.NotesRepository

class NotesRepositoryImpl(
    private val dbHelper: NotesDbHelper
) : NotesRepository {

    override fun getNotes(): Flow<List<Note>> {
        return dbHelper.getNotes()
    }

    override suspend fun getNoteById(id: Int): Note {
        return dbHelper.getNoteById(id)
    }

    override suspend fun insertOrUpdateNote(note: Note) {
        dbHelper.insertOrUpdateNote(note)
    }

    override suspend fun deleteNote(note: Note) {
        dbHelper.deleteNote(note)
    }
}