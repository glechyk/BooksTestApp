package com.books.app.presentation.feature.main

import android.app.Activity
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel

@Composable
fun MainRoute(
    viewModel: MainViewModel = hiltViewModel(),
    onNavigateToDetails: (Int) -> Unit,
) {
    val activity = LocalContext.current as Activity
    val topBannerState = viewModel.topBannerState.collectAsState()
    val booksState = viewModel.booksState.collectAsState()

    LaunchedEffect(key1 = Unit) {
        viewModel.getBooks(activity)
    }

    MainScreen(
        topBannerSlides = topBannerState.value,
        books = booksState.value,
        onBookClick = onNavigateToDetails,
    )
}