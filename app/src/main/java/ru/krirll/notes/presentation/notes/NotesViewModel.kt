package ru.krirll.notes.presentation.notes

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import ru.krirll.notes.domain.model.Note
import ru.krirll.notes.domain.use_case.NotesUseCases
import ru.krirll.notes.domain.util.NotesOrder
import ru.krirll.notes.domain.util.OrderType
import javax.inject.Inject

@HiltViewModel
class NotesViewModel @Inject constructor(
    private val notesUseCase: NotesUseCases
) : ViewModel() {

    private val _state = mutableStateOf(NotesState())
    val state: State<NotesState> = _state

    private var recentlyDeletedNote: Note? = null

    private var getNotesJob: Job? = null

    init {
        getNotes(NotesOrder.Date(OrderType.Descending))
    }

    fun onEvent(event: NotesEvent) {
        when (event) {
            is NotesEvent.DeleteNote -> {
                viewModelScope.launch {
                    notesUseCase.deleteNote(event.note)
                    recentlyDeletedNote = event.note
                }
                getNotes(_state.value.notesOrder)
            }

            is NotesEvent.Order -> {
                if (state.value.notesOrder::class == event.notesOrder::class &&
                    state.value.notesOrder.orderType == event.notesOrder.orderType
                ) {
                    return
                }
                getNotes(event.notesOrder)
            }

            NotesEvent.RestoreNote -> {
                viewModelScope.launch {
                    notesUseCase.insertOrUpdateNoteUseCase(recentlyDeletedNote ?: return@launch)
                    recentlyDeletedNote = null
                }
                getNotes(_state.value.notesOrder)
            }
            NotesEvent.UpdateNotes -> {
                getNotes(_state.value.notesOrder)
            }
            NotesEvent.ToggleOrderSection -> {
                _state.value = state.value.copy(
                    isOrderSectionVisible = !state.value.isOrderSectionVisible
                )
            }

        }
    }

    private fun getNotes(notesOrder: NotesOrder) {
        getNotesJob?.cancel()
        getNotesJob = notesUseCase.getNotes(notesOrder)
            .onEach { notes ->
                _state.value = state.value.copy(
                    notes = notes,
                    notesOrder = notesOrder
                )
                Log.d("LIST", "getNotes")
            }
            .launchIn(viewModelScope)
    }
}