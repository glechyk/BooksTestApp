package com.books.app.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.books.app.R

val Georgia = FontFamily(
    Font(R.font.georgia_bold_italic, FontWeight.W700)
)

val NunitoSans = FontFamily(
    Font(R.font.nunito_sans_regular, FontWeight.W600),
    Font(R.font.nunito_sans_bold, FontWeight.W700),
    Font(R.font.nunito_sans_extra_bold, FontWeight.W800),
)

val Typography = Typography(
    bodyLarge = TextStyle(
        fontFamily = NunitoSans,
        fontWeight = FontWeight.W600,
        fontSize = 16.sp,
        lineHeight = 17.6.sp,
    ),
    bodyMedium = TextStyle(
        fontFamily = NunitoSans,
        fontWeight = FontWeight.W600,
        fontSize = 14.sp,
        lineHeight = 16.8.sp,
    ),
    bodySmall = TextStyle(
        fontFamily = NunitoSans,
        fontWeight = FontWeight.W600,
        fontSize = 12.sp,
        lineHeight = 13.2.sp,
    ),
    titleLarge = TextStyle(
        fontFamily = NunitoSans,
        fontWeight = FontWeight.W700,
        fontSize = 20.sp,
        lineHeight = 22.sp,
    ),
    titleMedium = TextStyle(
        fontFamily = NunitoSans,
        fontWeight = FontWeight.W700,
        fontSize = 18.sp,
        lineHeight = 22.sp,
    ),
    titleSmall = TextStyle(
        fontFamily = NunitoSans,
        fontWeight = FontWeight.W700,
        fontSize = 14.sp,
        lineHeight = 15.4.sp,
    ),
    labelLarge = TextStyle(
        fontFamily = NunitoSans,
        fontWeight = FontWeight.W800,
        fontSize = 16.sp,
        lineHeight = 16.sp,
    ),
    headlineLarge = TextStyle(
        fontFamily = Georgia,
        fontWeight = FontWeight.W700,
        fontSize = 52.sp,
        lineHeight = 52.sp,
    ),
    headlineMedium = TextStyle(
        fontFamily = NunitoSans,
        fontWeight = FontWeight.W700,
        fontSize = 24.sp,
        lineHeight = 26.4.sp,
    )
)