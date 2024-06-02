package com.books.app.presentation.component

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.books.app.domain.model.BookDomain

@Composable
fun BooksList(
    modifier: Modifier = Modifier,
    books: List<BookDomain>,
    descriptionColor: Color,
    onBookClick: (Int) -> Unit,
) {
    val scrollState = rememberScrollState()

    Row(
        modifier = modifier
            .fillMaxWidth()
            .horizontalScroll(scrollState),
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Spacer(modifier = Modifier.width(8.dp))

        books.forEach {
            BookItem(
                book = it,
                descriptionColor = descriptionColor,
                onBookClick = { onBookClick(it.id) },
            )
        }

        Spacer(modifier = Modifier.width(8.dp))
    }
}

@Composable
private fun BookItem(
    modifier: Modifier = Modifier,
    book: BookDomain,
    descriptionColor: Color,
    onBookClick: () -> Unit,
) {
    Column(modifier = modifier.clickable(onClick = onBookClick)) {
        AsyncImage(
            model = book.coverUrl,
            contentDescription = null,
            modifier = Modifier
                .size(width = 120.dp, height = 150.dp)
                .clip(RoundedCornerShape(16.dp))
                .background(Color(0xFFC4C4C4)),
            contentScale = ContentScale.Crop,
        )

        Spacer(modifier = Modifier.height(4.dp))

        Text(
            text = book.name,
            modifier = Modifier.width(120.dp),
            color = descriptionColor,
            maxLines = 2,
            overflow = TextOverflow.Ellipsis,
            style = MaterialTheme.typography.bodyLarge,
        )
    }
}