package com.books.app.presentation.feature.details

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PageSize
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.util.lerp
import coil.compose.AsyncImage
import com.books.app.R
import com.books.app.domain.model.BookDomain
import com.books.app.domain.model.DetailDomain
import com.books.app.presentation.component.BooksList
import com.books.app.utils.PROJECT_REPOSITORY_URL
import kotlinx.coroutines.launch
import kotlin.math.absoluteValue

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun DetailsScreen(
    bookId: Int,
    details: List<DetailDomain>,
    recommendations: List<BookDomain>,
    onNavigateBack: () -> Unit,
) {
    val pagerState = rememberPagerState(initialPage = bookId, pageCount = { details.size })
    val scrollState = rememberScrollState()
    val coroutineScope = rememberCoroutineScope()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFF532454))
    ) {
        Spacer(modifier = Modifier.height(40.dp))

        IconButton(
            onClick = onNavigateBack,
            modifier = Modifier
                .padding(horizontal = 16.dp)
                .size(24.dp)
        ) {
            Icon(
                painter = painterResource(id = R.drawable.back_arrow_ic),
                contentDescription = null,
                tint = Color.White
            )
        }

        Spacer(modifier = Modifier.height(8.dp))

        if (details.isNotEmpty() && recommendations.isNotEmpty()) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .verticalScroll(scrollState)
            ) {
                DetailsCarousel(pagerState = pagerState, details = details)

                Spacer(modifier = Modifier.height(18.dp))

                DetailsDescription(
                    detail = details[pagerState.currentPage],
                    recommendations = recommendations,
                    onBookClick = {
                        coroutineScope.launch {
                            pagerState.animateScrollToPage(it)
                        }
                    }
                )
            }
        }
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun DetailsCarousel(
    pagerState: PagerState,
    details: List<DetailDomain>,
) {
    val pageSize = 200.dp

    Column {
        HorizontalPager(
            state = pagerState,
            modifier = Modifier.fillMaxWidth(),
            pageSize = PageSize.Fixed(pageSize),
            beyondBoundsPageCount = 5,
            contentPadding = PaddingValues(
                horizontal = (LocalConfiguration.current.screenWidthDp.dp - pageSize) / 2
            )
        ) { page ->

            AsyncImage(
                model = details[page].coverUrl,
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .graphicsLayer {
                        val offset = (
                                (pagerState.currentPage - page) + pagerState
                                    .currentPageOffsetFraction
                                ).absoluteValue
                        alpha = lerp(
                            start = 0.5f,
                            stop = 1f,
                            fraction = 1f - offset.coerceIn(0f, 1f)
                        )
                        scaleX = 1f - (offset.absoluteValue * 0.3f)
                        scaleY = 1f - (offset.absoluteValue * 0.3f)
                    }
                    .size(width = 200.dp, height = 250.dp)
                    .clip(RoundedCornerShape(16.dp))
                    .background(Color(0xFFC4C4C4))
                    .align(Alignment.CenterHorizontally),
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(4.dp),
        ) {
            Text(
                text = details[pagerState.currentPage].name,
                color = Color.White,
                style = MaterialTheme.typography.titleLarge,
            )

            Text(
                text = details[pagerState.currentPage].author,
                color = Color(0xCCFFFFFF),
                style = MaterialTheme.typography.titleSmall,
            )
        }
    }
}

@Composable
fun DetailsDescription(
    detail: DetailDomain,
    recommendations: List<BookDomain>,
    onBookClick: (Int) -> Unit,
) {
    val uriHandler = LocalUriHandler.current

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight(1f)
            .clip(RoundedCornerShape(topStart = 20.dp, topEnd = 20.dp))
            .background(Color.White),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        StatisticSection(detail = detail)

        SummarySection(text = detail.summary)

        HorizontalDivider(
            modifier = Modifier.padding(horizontal = 16.dp),
            color = Color(0xFFD9D5D6)
        )

        RecommendationsSection(recommendations = recommendations, onBookClick = onBookClick)

        Button(
            onClick = { uriHandler.openUri(PROJECT_REPOSITORY_URL) },
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 50.dp)
                .clip(RoundedCornerShape(30.dp)),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color(0xFFDD48A1)
            ),
            contentPadding = PaddingValues(16.dp)
        ) {
            Text(
                text = stringResource(R.string.details_read_now),
                color = Color.White,
                style = MaterialTheme.typography.labelLarge
            )
        }

        Spacer(modifier = Modifier.height(60.dp))
    }
}

@Composable
private fun StatisticSection(detail: DetailDomain) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp)
    ) {
        Spacer(modifier = Modifier.height(20.dp))

        Row(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceAround
        ) {
            StatisticItem(
                title = detail.views,
                description = stringResource(R.string.details_readers)
            )

            StatisticItem(
                title = detail.likes,
                description = stringResource(R.string.details_likes)
            )

            StatisticItem(
                title = detail.quotes,
                description = stringResource(R.string.details_quotes)
            )

            StatisticItem(
                title = detail.genre,
                description = stringResource(R.string.details_genre)
            )
        }

        Spacer(modifier = Modifier.height(10.dp))

        HorizontalDivider(color = Color(0xFFD9D5D6))
    }
}

@Composable
private fun StatisticItem(
    title: String,
    description: String,
) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Text(
            text = title,
            color = Color(0xFF0B080F),
            style = MaterialTheme.typography.titleMedium,
        )

        Text(
            text = description,
            color = Color(0xFFD9D5D6),
            style = MaterialTheme.typography.bodySmall,
        )
    }
}

@Composable
private fun SummarySection(text: String) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp),
    ) {
        Text(
            text = stringResource(R.string.details_summary),
            color = Color(0xFF0B080F),
            style = MaterialTheme.typography.titleLarge,
        )

        Text(
            text = text,
            color = Color(0xFF393637),
            style = MaterialTheme.typography.bodyMedium,
        )
    }
}

@Composable
fun RecommendationsSection(
    recommendations: List<BookDomain>,
    onBookClick: (Int) -> Unit,
) {
    Column(
        modifier = Modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(16.dp),
    ) {
        Text(
            text = stringResource(R.string.details_recommendations),
            modifier = Modifier.padding(start = 16.dp),
            color = Color(0xFF0B080F),
            style = MaterialTheme.typography.titleLarge,
        )

        BooksList(
            books = recommendations,
            descriptionColor = Color(0xFF393637),
            onBookClick = onBookClick,
        )
    }

    Spacer(modifier = Modifier.height(16.dp))
}
