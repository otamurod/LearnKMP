package com.kodeco.learn.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.kodeco.learn.R

private val fontSizeBig = 16.sp
private val fontSizeMedium = 15.sp
private val fontSizeSmall = 14.sp
private val fontSizeTiny = 12.sp

private val OpenSansFontFamily = FontFamily(
  Font(R.font.opensans_bold, FontWeight.Bold),
  Font(R.font.opensans_extrabold, FontWeight.ExtraBold),
  Font(R.font.opensans_light, FontWeight.Light),
  Font(R.font.opensans_regular, FontWeight.Normal),
  Font(R.font.opensans_semibold, FontWeight.SemiBold),
)

// Set of Material typography styles to start with
val Typography = Typography(
  headlineLarge = TextStyle(
    fontFamily = OpenSansFontFamily,
    fontWeight = FontWeight.Bold,
    fontSize = fontSizeMedium
  ),

  headlineMedium = TextStyle(
    fontFamily = OpenSansFontFamily,
    fontWeight = FontWeight.Normal,
    fontSize = fontSizeSmall
  ),

  headlineSmall = TextStyle(
    fontFamily = OpenSansFontFamily,
    fontWeight = FontWeight.Bold,
    fontSize = fontSizeTiny
  ),

  bodyLarge = TextStyle(
    fontFamily = OpenSansFontFamily,
    fontWeight = FontWeight.Normal,
    fontSize = fontSizeBig
  ),

  bodyMedium = TextStyle(
    fontFamily = OpenSansFontFamily,
    fontWeight = FontWeight.Bold,
    fontSize = fontSizeSmall
  ),

  bodySmall = TextStyle(
    fontFamily = OpenSansFontFamily,
    fontSize = fontSizeSmall
  )
)