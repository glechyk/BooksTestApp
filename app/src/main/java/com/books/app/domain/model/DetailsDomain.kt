package com.books.app.domain.model

data class DetailsDomain(
    val books: List<DetailDomain>,
)

data class DetailDomain(
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