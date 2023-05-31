package ru.krirll.notes.presentation.add_edit_note

sealed class UiEvent {
    data class ShowSnackBar(val message: String): UiEvent()
    object SaveNote: UiEvent()
}
