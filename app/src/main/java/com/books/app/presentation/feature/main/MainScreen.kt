package com.books.app.presentation.feature.main

import androidx.compose.animation.core.FastOutLinearInEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.books.app.R
import com.books.app.domain.model.BookDomain
import com.books.app.domain.model.TopBannerSlideDomain
import com.books.app.presentation.component.BooksList
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@Composable
fun MainScreen(
    topBannerSlides: List<TopBannerSlideDomain>,
    books: List<List<BookDomain>>,
    onBookClick: (Int) -> Unit,
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFF101010))
    ) {

        Spacer(modifier = Modifier.height(38.dp))

        Text(
            text = stringResource(R.string.main_title),
            modifier = Modifier.padding(start = 16.dp),
            color = Color(0xFFD0006E),
            style = MaterialTheme.typography.titleLarge
        )

        Spacer(modifier = Modifier.height(20.dp))

        BooksContent(topBannerSlides = topBannerSlides, books = books, onBookClick = onBookClick)
    }
}

@Composable
private fun BooksContent(
    topBannerSlides: List<TopBannerSlideDomain>,
    books: List<List<BookDomain>>,
    onBookClick: (Int) -> Unit,
) {
    LazyColumn(
        contentPadding = PaddingValues(bottom = 45.dp),
        verticalArrangement = Arrangement.spacedBy(24.dp)
    ) {
        if (topBannerSlides.isNotEmpty()) item {
            TopBannerSlides(topBannerSlides = topBannerSlides, onBookClick = onBookClick)

            Spacer(modifier = Modifier.height(16.dp))
        }
        if (books.isNotEmpty()) items(books) {
            GenreItem(books = it, onBookClick = onBookClick)
        }
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
private fun TopBannerSlides(
    topBannerSlides: List<TopBannerSlideDomain>,
    onBookClick: (Int) -> Unit,
) {
    val pagerState = rememberPagerState(pageCount = { topBannerSlides.size })

    LaunchedEffect(key1 = pagerState.currentPage) {
        launch {
            delay(3000)
            with(pagerState) {
                val target = if (currentPage < topBannerSlides.size - 1) currentPage + 1 else 0

                animateScrollToPage(
                    page = target, animationSpec = tween(
                        durationMillis = 0, easing = FastOutLinearInEasing
                    )
                )
            }
        }
    }

    Box(modifier = Modifier.fillMaxWidth()) {
        HorizontalPager(
            state = pagerState,
            modifier = Modifier.fillMaxWidth(),
            beyondBoundsPageCount = 3,
        ) { page ->
            AsyncImage(
                model = topBannerSlides[page].cover,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(160.dp)
                    .padding(horizontal = 16.dp)
                    .clip(RoundedCornerShape(16.dp))
                    .background(Color(0xFFC4C4C4))
                    .clickable(onClick = { onBookClick(topBannerSlides[page].bookId) }),
                contentDescription = null,
                contentScale = ContentScale.Crop,
            )
        }
        Row(
            Modifier
                .wrapContentHeight()
                .fillMaxWidth()
                .align(Alignment.BottomCenter)
                .padding(bottom = 8.dp),
            horizontalArrangement = Arrangement.Center
        ) {

            repeat(pagerState.pageCount) { iteration ->
                val color = if (pagerState.currentPage == iteration) {
                    Color(0xFFD0006E)
                } else {
                    Color(0xFFC1C2CA)
                }
                Box(
                    modifier = Modifier
                        .padding(horizontal = 5.dp, vertical = 8.dp)
                        .clip(CircleShape)
                        .background(color)
                        .size(7.dp)
                )
            }
        }
    }
}

@Composable
private fun GenreItem(books: List<BookDomain>, onBookClick: (Int) -> Unit) {
    Column(modifier = Modifier.fillMaxWidth()) {
        Text(
            text = books[0].genre,
            modifier = Modifier.padding(start = 16.dp),
            color = Color.White,
            style = MaterialTheme.typography.titleLarge
        )

        Spacer(modifier = Modifier.height(16.dp))

        BooksList(
            books = books,
            descriptionColor = Color(0xB3FFFFFF),
            onBookClick = onBookClick
        )
    }
}
