package ru.krirll.notes.domain.use_case

data class NotesUseCases(
    val getNotes: GetNotesUseCase,
    val deleteNote: DeleteNoteUseCase,
    val insertOrUpdateNoteUseCase: InsertOrUpdateNoteUseCase,
    val getNoteById: GetNoteByIdUseCase
)