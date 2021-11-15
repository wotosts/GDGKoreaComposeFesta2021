package com.codelab.theming.ui.start.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

private val LightColors = lightColors(
    primary = BlueP,
    primaryVariant = BluePDark,
    onPrimary = Color.Black,
    secondary = BlueS,
    secondaryVariant = BlueSDark,
    onSecondary = Color.White,
    error = Error
)

private val DarkColors = darkColors(
    primary = BluePLight,
    primaryVariant = BlueP,
    onPrimary = Color.White,
    secondary = BlueSLight,
    secondaryVariant = BlueS,
    onSecondary = Color.Black,
    error = Error
)

@Composable
fun JetnewsTheme(darkTheme: Boolean = isSystemInDarkTheme(), content: @Composable () -> Unit) {
    MaterialTheme(
        content = content,
        colors = if (darkTheme) DarkColors else LightColors,
        typography = JetsTypography,
        shapes = JetnewsShape
    )
}