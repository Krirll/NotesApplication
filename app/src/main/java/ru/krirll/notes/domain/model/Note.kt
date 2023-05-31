package ru.krirll.notes.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import ru.krirll.notes.ui.theme.BabyBlue
import ru.krirll.notes.ui.theme.LightGreen
import ru.krirll.notes.ui.theme.RedOrange
import ru.krirll.notes.ui.theme.RedPink
import ru.krirll.notes.ui.theme.Violet

@Entity
data class Note(
    val title: String,
    val content: String,
    val timestamp: Long,
    val color: Int,

    @PrimaryKey(autoGenerate = true)
    var id: Int? = null,

) {

    companion object {
        val noteColors = listOf(RedOrange, LightGreen, Violet, BabyBlue, RedPink)
    }
}

class InvalidNoteException(message: String): Exception(message)