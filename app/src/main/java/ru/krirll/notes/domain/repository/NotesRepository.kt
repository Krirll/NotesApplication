package ru.krirll.notes.domain.repository

import kotlinx.coroutines.flow.Flow
import ru.krirll.notes.domain.model.Note

interface NotesRepository {

    fun getNotes(): Flow<List<Note>>

    suspend fun getNoteById(id : Int): Note?

    suspend fun insertOrUpdateNote(note: Note)

    suspend fun deleteNote(note: Note)
}