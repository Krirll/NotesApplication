package ru.krirll.notes.data.repository

import kotlinx.coroutines.flow.Flow
import ru.krirll.notes.data.notes_db.NotesDao
import ru.krirll.notes.domain.model.Note
import ru.krirll.notes.domain.repository.NotesRepository

class NotesRepositoryImpl(
    private val dao: NotesDao
): NotesRepository {

    override fun getNotes(): Flow<List<Note>> {
        return dao.getNotes()
    }

    override suspend fun getNoteById(id: Int): Note? {
        return dao.getNoteById(id)
    }

    override suspend fun insertOrUpdateNote(note: Note) {
        dao.insertOrUpdateNote(note)
    }

    override suspend fun deleteNote(note: Note) {
        dao.deleteNote(note)
    }
}