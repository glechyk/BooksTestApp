package com.books.app.domain.usecase

import android.app.Activity
import com.books.app.domain.model.DetailsDomain
import com.books.app.domain.repository.BooksRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

interface GetDetailsUseCase {

    suspend operator fun invoke(activity: Activity): Flow<DetailsDomain>
}

class GetDetailsUseCaseImpl @Inject constructor(
    private val booksRepository: BooksRepository,
) : GetDetailsUseCase {

    override suspend fun invoke(activity: Activity): Flow<DetailsDomain> =
        booksRepository.getDetails(activity)
}