package ru.krirll.notes.domain.use_case

import ru.krirll.notes.domain.model.InvalidNoteException
import ru.krirll.notes.domain.model.Note
import ru.krirll.notes.domain.repository.NotesRepository

class InsertOrUpdateNoteUseCase(
    private val repository: NotesRepository
) {

    @Throws(InvalidNoteException::class)
    suspend operator fun invoke(note: Note) {
        if (note.title.isBlank()) {
            if (note.content.isBlank())
                throw  InvalidNoteException("The content of the note can't be empty")
            else
                throw InvalidNoteException("The title of the note can't be empty")
        }
        repository.insertOrUpdateNote(note)
    }
}