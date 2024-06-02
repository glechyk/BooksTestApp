package com.books.app.data.mapper

import com.books.app.data.model.BookData
import com.books.app.data.model.BooksData
import com.books.app.data.model.TopBannerSlideData
import com.books.app.domain.model.BookDomain
import com.books.app.domain.model.BooksDomain
import com.books.app.domain.model.TopBannerSlideDomain

fun BooksData.mapToDomain() = BooksDomain(
    books = books?.mapNotNull { it?.mapToDomain() } ?: emptyList(),
    topBannerSlides = topBannerSlides?.mapNotNull { it?.mapToDomain() } ?: emptyList(),
    youWillLikeSection = youWillLikeSection?.map { it ?: -1 } ?: emptyList()
)

fun BookData.mapToDomain() = BookDomain(
    author = author.orEmpty(),
    coverUrl = coverUrl.orEmpty(),
    genre = genre.orEmpty(),
    id = id ?: -1,
    likes = likes.orEmpty(),
    name = name.orEmpty(),
    quotes = quotes.orEmpty(),
    summary = summary.orEmpty(),
    views = views.orEmpty(),
)

fun TopBannerSlideData.mapToDomain() = TopBannerSlideDomain(
    bookId = bookId ?: -1,
    cover = cover.orEmpty(),
    id = id ?: -1,
)