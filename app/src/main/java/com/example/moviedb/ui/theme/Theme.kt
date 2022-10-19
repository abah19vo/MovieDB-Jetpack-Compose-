package com.example.moviedb.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

val LightColorPalette = lightColors(
    primary = Color.Black,
    surface = white800,
    onPrimary = white800,
    onSecondary = white800,
    onBackground =  Color.Black,
    onSurface = gray900.copy(alpha = 0.8f)
)

private val DarkColorPalette = darkColors(
    primary = Color.White,
    background = Color.Black,
    surface = white150,
    onPrimary = gray900,
    onSecondary = white150,
    onBackground = white150,
    onSurface = Color.White.copy(alpha = .8f)
)

@Composable
fun MovieDBTheme(darkTheme: Boolean = isSystemInDarkTheme(), content: @Composable () -> Unit) {
    val colors = if (darkTheme) {
        DarkColorPalette
    } else {
        LightColorPalette
    }

    MaterialTheme(
        colors = colors,
        typography = typography,
        shapes = Shapes,
        content = content
    )
}