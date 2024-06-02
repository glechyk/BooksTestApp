package com.books.app.presentation.feature.details

import android.app.Activity
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.books.app.domain.model.BookDomain
import com.books.app.domain.model.DetailDomain
import com.books.app.domain.usecase.GetBooksUseCase
import com.books.app.domain.usecase.GetDetailsUseCase
import com.books.app.utils.subscribe
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailsViewModel @Inject constructor(
    private val getDetailsUseCase: GetDetailsUseCase,
    private val getBooksUseCase: GetBooksUseCase,
) : ViewModel() {

    private val _detailsState = MutableStateFlow<List<DetailDomain>>(emptyList())
    val detailsState = _detailsState.asStateFlow()

    private val _recommendationsState = MutableStateFlow<List<BookDomain>>(emptyList())
    val recommendationsState = _recommendationsState.asStateFlow()

    fun getDetailsAndRecommendations(activity: Activity) {
        viewModelScope.launch {
            getDetailsUseCase(activity).subscribe(
                scope = viewModelScope,
                success = { details ->
                    _detailsState.update { details.books }
                },
                error = {
                    it.printStackTrace()
                },
            )

            getBooksUseCase(activity).subscribe(
                scope = viewModelScope,
                success = { books ->
                    _recommendationsState.update {
                        books.books.filter { it.id in books.youWillLikeSection }
                    }
                },
                error = {
                    it.printStackTrace()
                },
            )
        }
    }
}