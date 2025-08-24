package com.agungkusuma.core.domain.usecase

import com.agungkusuma.core.data.remote.model.BooksResponse
import com.agungkusuma.core.domain.repository.BookRepository
import javax.inject.Inject

class GetBooksUseCase @Inject constructor(
    private val repository: BookRepository
) {
    suspend operator fun invoke(): BooksResponse {
        return repository.getBooks()
    }
}