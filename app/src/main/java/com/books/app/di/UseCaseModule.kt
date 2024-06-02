package com.books.app.di

import com.books.app.domain.usecase.GetBooksUseCase
import com.books.app.domain.usecase.GetBooksUseCaseImpl
import com.books.app.domain.usecase.GetDetailsUseCase
import com.books.app.domain.usecase.GetDetailsUseCaseImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface UseCaseModule {

    @Binds
    @Singleton
    fun bindGetBooksUseCaseRepository(
        getBooksUseCaseImpl: GetBooksUseCaseImpl,
    ): GetBooksUseCase

    @Binds
    @Singleton
    fun bindGetDetailsUseCaseRepository(
        getDetailsUseCaseImpl: GetDetailsUseCaseImpl,
    ): GetDetailsUseCase
}