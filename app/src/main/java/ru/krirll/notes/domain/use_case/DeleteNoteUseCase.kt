package ru.krirll.notes.domain.use_case

import ru.krirll.notes.domain.model.Note
import ru.krirll.notes.domain.repository.NotesRepository

class DeleteNoteUseCase(
    private val repository: NotesRepository
) {

    suspend operator fun invoke(note: Note) {
        repository.deleteNote(note)
    }
}