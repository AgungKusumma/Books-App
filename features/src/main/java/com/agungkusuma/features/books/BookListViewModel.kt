package com.agungkusuma.features.books

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.agungkusuma.common.state.UiState
import com.agungkusuma.core.data.remote.model.BookItem
import com.agungkusuma.core.data.remote.model.BooksResponse
import com.agungkusuma.core.domain.usecase.GetBooksUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class BookListViewModel @Inject constructor(
    private val getBooksUseCase: GetBooksUseCase
) : ViewModel() {

    private val _bookState = MutableStateFlow<UiState<BooksResponse>>(UiState.Idle)
    val bookState: StateFlow<UiState<BooksResponse>> = _bookState

    private var originalList: List<BookItem> = emptyList()
    private var searchJob: Job? = null

    init {
        loadBooks()
    }

    private fun loadBooks() {
        viewModelScope.launch {
            _bookState.value = UiState.Loading
            try {
                val response = getBooksUseCase()
                originalList = response.items
                _bookState.value = UiState.Success(response)
            } catch (e: Exception) {
                _bookState.value = UiState.Error(e)
            }
        }
    }

    // local search
    fun searchBooks(query: String) {
        searchJob?.cancel()
        searchJob = viewModelScope.launch {
            delay(300)
            val filtered = if (query.isBlank()) {
                originalList
            } else {
                originalList.filter {
                    it.volumeInfo.title.contains(query, ignoreCase = true) ||
                            (it.volumeInfo.authors?.any { author ->
                                author.contains(query, ignoreCase = true)
                            } == true)
                }
            }
            _bookState.value = UiState.Success(BooksResponse(filtered))
        }
    }
}