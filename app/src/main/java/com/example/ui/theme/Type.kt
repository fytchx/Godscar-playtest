package com.example.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.example.R

val LoraFontFamily = FontFamily(
    Font(R.font.lora, FontWeight.Normal),
    Font(R.font.lora, FontWeight.Bold)
)

val InterFontFamily = FontFamily(
    Font(R.font.inter, FontWeight.Normal),
    Font(R.font.inter, FontWeight.Medium),
    Font(R.font.inter, FontWeight.SemiBold),
    Font(R.font.inter, FontWeight.Bold)
)

val GodscarTypography = Typography(
    headlineMedium = TextStyle(
        fontFamily = LoraFontFamily,
        fontWeight = FontWeight.Bold,
        fontSize = 22.sp,
        lineHeight = 26.sp,
        color = GodscarColors.TextIvory
    ),
    headlineSmall = TextStyle(
        fontFamily = LoraFontFamily,
        fontWeight = FontWeight.Bold,
        fontSize = 18.sp,
        lineHeight = 22.sp,
        color = GodscarColors.TextIvory
    ),
    titleLarge = TextStyle(
        fontFamily = LoraFontFamily,
        fontWeight = FontWeight.Bold,
        fontSize = 17.sp,
        lineHeight = 20.sp,
        letterSpacing = 0.3.sp,
        color = GodscarColors.TextIvory
    ),
    titleMedium = TextStyle(
        fontFamily = LoraFontFamily,
        fontWeight = FontWeight.Bold,
        fontSize = 15.sp,
        lineHeight = 18.sp,
        color = GodscarColors.TextIvory
    ),
    titleSmall = TextStyle(
        fontFamily = LoraFontFamily,
        fontWeight = FontWeight.Bold,
        fontSize = 13.sp,
        lineHeight = 16.sp,
        color = GodscarColors.TextIvory
    ),
    bodyMedium = TextStyle(
        fontFamily = InterFontFamily,
        fontWeight = FontWeight.Normal,
        fontSize = 13.sp,
        lineHeight = 18.sp,
        color = GodscarColors.TextWarm
    ),
    bodySmall = TextStyle(
        fontFamily = InterFontFamily,
        fontWeight = FontWeight.Normal,
        fontSize = 11.sp,
        lineHeight = 15.sp,
        color = GodscarColors.TextMuted
    ),
    labelLarge = TextStyle(
        fontFamily = InterFontFamily,
        fontWeight = FontWeight.Bold,
        fontSize = 11.sp,
        lineHeight = 14.sp,
        letterSpacing = 0.6.sp,
        color = GodscarColors.TextIvory
    ),
    labelSmall = TextStyle(
        fontFamily = InterFontFamily,
        fontWeight = FontWeight.Bold,
        fontSize = 9.sp,
        lineHeight = 11.sp,
        letterSpacing = 1.1.sp,
        color = GodscarColors.TextMuted
    )
)
