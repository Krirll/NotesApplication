package ru.krirll.notes.presentation.notes.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import ru.krirll.notes.domain.util.NotesOrder
import ru.krirll.notes.domain.util.OrderType

@Composable
fun OrderSection(
    modifier: Modifier = Modifier,
    notesOrder: NotesOrder = NotesOrder.Date(OrderType.Descending),
    onOrderChanged: (NotesOrder) -> Unit
) {
    Column(
        modifier = modifier
    ) {
        Row(
            modifier = Modifier.fillMaxWidth()
        ) {
            DefaultRadioButton(
                text = "Title",
                selected = notesOrder is NotesOrder.Title,
                onSelected = { onOrderChanged(NotesOrder.Title(notesOrder.orderType)) }
            )
            Spacer(modifier = Modifier.width(8.dp))
            DefaultRadioButton(
                text = "Date",
                selected = notesOrder is NotesOrder.Date,
                onSelected = { onOrderChanged(NotesOrder.Date(notesOrder.orderType)) }
            )
            Spacer(modifier = Modifier.width(8.dp))
            DefaultRadioButton(
                text = "Color",
                selected = notesOrder is NotesOrder.Color,
                onSelected = { onOrderChanged(NotesOrder.Color(notesOrder.orderType)) }
            )
            Spacer(modifier = Modifier.width(8.dp))
        }
        Spacer(modifier = Modifier.height(16.dp))
        Row(
            modifier = Modifier.fillMaxWidth()
        ) {
            DefaultRadioButton(
                text = "Ascending",
                selected = notesOrder.orderType is OrderType.Ascending,
                onSelected = {
                    onOrderChanged(notesOrder.copy(OrderType.Ascending))
                }
            )
            Spacer(modifier = Modifier.width(8.dp))
            DefaultRadioButton(
                text = "Descending",
                selected = notesOrder.orderType is OrderType.Descending,
                onSelected = {
                    onOrderChanged(notesOrder.copy(OrderType.Descending))
                }
            )
        }
    }
}