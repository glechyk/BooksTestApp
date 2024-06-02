package com.books.app.presentation.feature.splash

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import kotlinx.coroutines.delay

@Composable
fun SplashRoute(
    onNavigateToMainScreen: () -> Unit,
) {
    LaunchedEffect(key1 = Unit) {
        delay(2000L)
        onNavigateToMainScreen()
    }

    SplashScreen()
}