package com.books.app.data.mapper

import com.books.app.data.model.DetailData
import com.books.app.data.model.DetailsData
import com.books.app.domain.model.DetailDomain
import com.books.app.domain.model.DetailsDomain

fun DetailsData.mapToDomain() = DetailsDomain(
    books = books?.mapNotNull { it?.mapToDomain() } ?: emptyList(),
)

fun DetailData.mapToDomain() = DetailDomain(
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