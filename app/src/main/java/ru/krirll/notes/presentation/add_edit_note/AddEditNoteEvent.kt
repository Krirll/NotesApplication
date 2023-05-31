package ru.krirll.notes.presentation.add_edit_note

import androidx.compose.ui.focus.FocusState

sealed class AddEditNoteEvent {
    data class TitleTextChanged(val value: String): AddEditNoteEvent()
    data class TitleFocusChanged(val focus: FocusState): AddEditNoteEvent()
    data class ContentTextChanged(val value: String): AddEditNoteEvent()
    data class ContentFocusChanged(val focus: FocusState): AddEditNoteEvent()
    data class ColorChanged(val color: Int): AddEditNoteEvent()
    object SaveNote: AddEditNoteEvent()
}
