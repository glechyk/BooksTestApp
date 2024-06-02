package com.books.app.domain.usecase

import android.app.Activity
import com.books.app.domain.model.BooksDomain
import com.books.app.domain.repository.BooksRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

interface GetBooksUseCase {

    suspend operator fun invoke(activity: Activity): Flow<BooksDomain>
}

class GetBooksUseCaseImpl @Inject constructor(
    private val booksRepository: BooksRepository,
) : GetBooksUseCase {

    override suspend fun invoke(activity: Activity): Flow<BooksDomain> =
        booksRepository.getBooks(activity)
}