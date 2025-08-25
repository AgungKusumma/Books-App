package com.agungkusuma.features.books

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.agungkusuma.common.state.UiState
import com.agungkusuma.core.data.local.BookEntity
import com.agungkusuma.core.domain.usecase.GetBooksUseCase
import com.agungkusuma.core.domain.usecase.RefreshBooksUseCase
import com.agungkusuma.core.domain.usecase.SearchBooksUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class BookListViewModel @Inject constructor(
    private val getBooksUseCase: GetBooksUseCase,
    private val searchBooksUseCase: SearchBooksUseCase,
    private val refreshBooksUseCase: RefreshBooksUseCase
) : ViewModel() {
    private val query = MutableStateFlow("")

    private val _bookState = MutableStateFlow<UiState<List<BookEntity>>>(UiState.Idle)
    val bookState: StateFlow<UiState<List<BookEntity>>> = _bookState

    init {
        observeBooks()
    }

    private fun observeBooks() {
        viewModelScope.launch {
            try {
                query.flatMapLatest { q ->
                    if (q.isBlank()) getBooksUseCase() else searchBooksUseCase(q)
                }
                    .onStart { _bookState.value = UiState.Loading }
                    .collect { list ->
                        _bookState.value = UiState.Success(list)

                        if (query.value.isBlank() && list.isEmpty()) {
                            refresh()
                        }
                    }
            } catch (e: Exception) {
                _bookState.value = UiState.Error(e)
            }
        }
    }

    fun searchBooks(text: String) {
        viewModelScope.launch { query.emit(text) }
    }

    fun refresh() {
        viewModelScope.launch {
            try {
                _bookState.value = UiState.Loading
                refreshBooksUseCase()
                _bookState.value = UiState.Idle
            } catch (e: Exception) {
                _bookState.value = UiState.Error(e)
            }
        }
    }
}