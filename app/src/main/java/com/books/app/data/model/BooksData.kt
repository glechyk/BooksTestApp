package com.books.app.data.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class BooksData(
    val books: List<BookData?>?,
    @SerialName("top_banner_slides")
    val topBannerSlides: List<TopBannerSlideData?>?,
    @SerialName("you_will_like_section")
    val youWillLikeSection: List<Int?>?
)

@Serializable
data class BookData(
    val author: String?,
    @SerialName("cover_url")
    val coverUrl: String?,
    val genre: String?,
    val id: Int?,
    val likes: String?,
    val name: String?,
    val quotes: String?,
    val summary: String?,
    val views: String?
)

@Serializable
data class TopBannerSlideData(
    @SerialName("book_id")
    val bookId: Int?,
    val cover: String?,
    val id: Int?
)