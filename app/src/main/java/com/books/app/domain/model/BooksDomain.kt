package com.books.app.domain.model

data class BooksDomain(
    val books: List<BookDomain>,
    val topBannerSlides: List<TopBannerSlideDomain>,
    val youWillLikeSection: List<Int>,
)

data class BookDomain(
    val author: String,
    val coverUrl: String,
    val genre: String,
    val id: Int,
    val likes: String,
    val name: String,
    val quotes: String,
    val summary: String,
    val views: String,
)

data class TopBannerSlideDomain(
    val bookId: Int,
    val cover: String,
    val id: Int,
)