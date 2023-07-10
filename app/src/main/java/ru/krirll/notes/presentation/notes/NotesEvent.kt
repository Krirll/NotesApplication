package ru.krirll.notes.presentation.notes

import ru.krirll.notes.domain.model.Note
import ru.krirll.notes.domain.util.NotesOrder

sealed class NotesEvent {
    data class Order(val notesOrder: NotesOrder): NotesEvent()
    data class DeleteNote(val note: Note): NotesEvent()
    object RestoreNote: NotesEvent()
    object UpdateNotes: NotesEvent()
    object ToggleOrderSection: NotesEvent()
}
