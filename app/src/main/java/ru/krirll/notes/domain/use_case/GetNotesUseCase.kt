package ru.krirll.notes.domain.use_case

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import ru.krirll.notes.domain.model.Note
import ru.krirll.notes.domain.repository.NotesRepository
import ru.krirll.notes.domain.util.NotesOrder
import ru.krirll.notes.domain.util.OrderType

class GetNotesUseCase(
    private val repository: NotesRepository
) {

    operator fun invoke(
        notesOrder: NotesOrder = NotesOrder.Date(OrderType.Descending)
    ): Flow<List<Note>> {
        return repository.getNotes().map {notes ->
            when(notesOrder.orderType) {
                is OrderType.Ascending -> {
                    when(notesOrder) {
                        is NotesOrder.Title -> notes.sortedBy { it.title.lowercase() }
                        is NotesOrder.Color -> notes.sortedBy { it.color }
                        is NotesOrder.Date -> notes.sortedBy { it.timestamp }
                    }
                }
                is OrderType.Descending -> {
                    when(notesOrder) {
                        is NotesOrder.Title -> notes.sortedByDescending { it.title.lowercase() }
                        is NotesOrder.Color -> notes.sortedByDescending { it.color }
                        is NotesOrder.Date -> notes.sortedByDescending { it.timestamp }
                    }
                }
            }
        }
    }
}