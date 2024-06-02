package com.books.app.presentation.feature.details

import android.app.Activity
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel

@Composable
fun DetailsRoute(
    viewModel: DetailsViewModel = hiltViewModel(),
    bookId: Int,
    onNavigateBack: () -> Unit,
) {

    val activity = LocalContext.current as Activity
    val detailsState = viewModel.detailsState.collectAsState()
    val recommendationsState = viewModel.recommendationsState.collectAsState()

    LaunchedEffect(key1 = Unit) {
        viewModel.getDetailsAndRecommendations(activity)
    }

    DetailsScreen(
        bookId = bookId,
        details = detailsState.value,
        recommendations = recommendationsState.value,
        onNavigateBack = onNavigateBack,
    )
}