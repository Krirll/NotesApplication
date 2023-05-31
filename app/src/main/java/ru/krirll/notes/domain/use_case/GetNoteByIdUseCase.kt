package ru.krirll.notes.domain.use_case

import ru.krirll.notes.domain.model.Note
import ru.krirll.notes.domain.repository.NotesRepository

class GetNoteByIdUseCase(
    private val repository: NotesRepository
) {

    suspend operator fun invoke(id: Int): Note? = repository.getNoteById(id)
}