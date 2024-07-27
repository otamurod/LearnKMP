package com.kodeco.learn.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.platform.Font
import androidx.compose.ui.unit.sp

private val fontSizeBig = 16.sp
private val fontSizeMedium = 15.sp
private val fontSizeSmall = 14.sp
private val fontSizeTiny = 12.sp

private val OpenSansFontFamily = FontFamily(
  Font("font/opensans_bold.ttf", FontWeight.Bold),
  Font("font/opensans_extrabold.ttf", FontWeight.ExtraBold),
  Font("font/opensans_light.ttf", FontWeight.Light),
  Font("font/opensans_regular.ttf", FontWeight.Normal),
  Font("font/opensans_semibold.ttf", FontWeight.SemiBold),
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