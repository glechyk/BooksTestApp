package com.books.app.domain.repository

import android.app.Activity
import com.books.app.domain.model.BooksDomain
import com.books.app.domain.model.DetailsDomain
import kotlinx.coroutines.flow.Flow

interface BooksRepository {

    suspend fun getBooks(activity: Activity): Flow<BooksDomain>

    suspend fun getDetails(activity: Activity): Flow<DetailsDomain>
}