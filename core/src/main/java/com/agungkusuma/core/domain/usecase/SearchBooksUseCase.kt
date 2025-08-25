package com.agungkusuma.core.domain.usecase

import com.agungkusuma.core.data.local.BookEntity
import com.agungkusuma.core.domain.repository.BookRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class SearchBooksUseCase @Inject constructor(
    private val repository: BookRepository
) {
    operator fun invoke(query: String): Flow<List<BookEntity>> = repository.searchBooks(query)
}