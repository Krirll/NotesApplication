package ru.krirll.notes.data.notes_db

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Query
import androidx.room.Upsert
import kotlinx.coroutines.flow.Flow
import ru.krirll.notes.domain.model.Note

@Dao
interface NotesDao {

    @Query("SELECT * FROM note")
    fun getNotes(): Flow<List<Note>>

    @Query("SELECT * FROM note WHERE id = :id")
    suspend fun getNoteById(id: Int): Note?

    @Upsert
    suspend fun insertOrUpdateNote(note: Note)

    @Delete
    suspend fun deleteNote(note: Note)
}