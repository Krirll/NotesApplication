package ru.krirll.notes.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

private val DarkColorPalette = darkColorScheme(
    primary = Color.White,
    onPrimary = Color.Black,
    background = DarkGray,
    onBackground = Color.White,
    surface = LightBlue,
    onSurface = DarkGray
)

@Composable
fun NotesTheme(content: @Composable () -> Unit) {
    MaterialTheme(
        colorScheme = DarkColorPalette,
        typography = Typography,
        content = content
    )
}