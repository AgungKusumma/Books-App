package com.agungkusuma.features.books

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.agungkusuma.core.domain.model.Book
import com.agungkusuma.core.domain.usecase.GetBooksUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
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

    init {
        loadBooks()
    }

    private fun loadBooks() {
        viewModelScope.launch {
            _bookState.value = getBooksUseCase()
        }
    }
}