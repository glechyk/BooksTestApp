package com.books.app.di

import com.books.app.data.repository.BooksRepositoryImpl
import com.books.app.domain.repository.BooksRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface RepositoryModule {

    @Binds
    @Singleton
    fun bindBooksRepository(
        booksRepositoryImpl: BooksRepositoryImpl,
    ): BooksRepository
}