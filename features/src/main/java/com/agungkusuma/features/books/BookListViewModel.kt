package com.agungkusuma.features.books

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.agungkusuma.core.domain.model.Book
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

    private val _bookState = MutableStateFlow<List<Book>>(emptyList())
    val bookState: StateFlow<List<Book>> = _bookState

    private var originalList: List<Book> = emptyList()
    private var searchJob: Job? = null

    init {
        loadBooks()
    }

    private fun loadBooks() {
        viewModelScope.launch {
            val books = getBooksUseCase()
            originalList = books
            _bookState.value = books
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
                    it.title.contains(query, ignoreCase = true) ||
                            it.authors.contains(query, ignoreCase = true)
                }
            }
            _bookState.value = filtered
        }
    }
}