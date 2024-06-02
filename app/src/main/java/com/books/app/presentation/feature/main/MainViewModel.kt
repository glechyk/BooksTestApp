package com.books.app.presentation.feature.main

import android.app.Activity
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.books.app.domain.model.BookDomain
import com.books.app.domain.model.TopBannerSlideDomain
import com.books.app.domain.usecase.GetBooksUseCase
import com.books.app.utils.subscribe
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val getBooksUseCase: GetBooksUseCase,
) : ViewModel() {

    private val _topBannerState = MutableStateFlow<List<TopBannerSlideDomain>>(emptyList())
    val topBannerState = _topBannerState.asStateFlow()

    private val _booksState = MutableStateFlow<List<List<BookDomain>>>(emptyList())
    val booksState = _booksState.asStateFlow()

    fun getBooks(activity: Activity) {
        viewModelScope.launch {
            getBooksUseCase(activity).subscribe(
                scope = viewModelScope,
                success = { books ->
                    _topBannerState.update { books.topBannerSlides }
                    _booksState.update { books.books.groupBy { it.genre }.values.toList() }
                },
                error = {
                    it.printStackTrace()
                },
            )
        }
    }
}