package com.books.app.data.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class DetailsData(
    val books: List<DetailData?>?,
)

@Serializable
data class DetailData(
    val author: String?,
    @SerialName("cover_url")
    val coverUrl: String?,
    val genre: String?,
    val id: Int?,
    val likes: String?,
    val name: String?,
    val quotes: String?,
    val summary: String?,
    val views: String?,
)