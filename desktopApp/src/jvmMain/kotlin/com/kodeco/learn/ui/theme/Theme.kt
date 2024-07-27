package com.kodeco.learn.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable

private val DarkColorScheme = darkColorScheme(
  primary = darkTextPrimary,
  surface = darkBackground,
  onSurfaceVariant = darkTextPrimary,
  background = darkBackground,
  secondaryContainer = darkSecondaryContainer,
  onSecondaryContainer = darkBackground
)

private val LightColorScheme = lightColorScheme(
  primary = lightTextPrimary,
  surface = lightBackground,
  onSurfaceVariant = lightTextPrimary,
  background = lightBackground,
  secondaryContainer = lightSecondaryContainer,
  onSecondaryContainer = lightBackground
)

@Composable
fun KodecoTheme(
  darkTheme: Boolean = isSystemInDarkTheme(),
  content: @Composable () -> Unit
) {
  val colorScheme = when {
    darkTheme -> DarkColorScheme
    else -> LightColorScheme
  }

  MaterialTheme(
    colorScheme = colorScheme,
    typography = Typography,
    content = content
  )
}