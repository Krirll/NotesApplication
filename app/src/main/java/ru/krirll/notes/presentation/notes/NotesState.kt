package ru.krirll.notes.presentation.notes

import ru.krirll.notes.domain.model.Note
import ru.krirll.notes.domain.util.NotesOrder
import ru.krirll.notes.domain.util.OrderType

data class NotesState(
    val notes: List<Note> = emptyList(),
    val notesOrder: NotesOrder = NotesOrder.Date(OrderType.Descending),
    val isOrderSectionVisible: Boolean = false
)