package com.example.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.runtime.Composable

private val GodscarDarkScheme = darkColorScheme(
    primary = GodscarColors.Accent,
    onPrimary = GodscarColors.Page,
    secondary = GodscarColors.Highlight,
    onSecondary = GodscarColors.Page,
    background = GodscarColors.Page,
    onBackground = GodscarColors.TextIvory,
    surface = GodscarColors.Surface,
    onSurface = GodscarColors.TextIvory,
    surfaceVariant = GodscarColors.SurfaceRaised,
    onSurfaceVariant = GodscarColors.TextMuted,
    outline = GodscarColors.Line,
    outlineVariant = GodscarColors.LineSoft,
    error = GodscarColors.Danger,
    onError = GodscarColors.Page
)

@Composable
fun GodscarTheme(content: @Composable () -> Unit) {
    MaterialTheme(
        colorScheme = GodscarDarkScheme,
        typography = GodscarTypography,
        content = content
    )
}
