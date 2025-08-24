package com.agungkusuma.core.domain.usecase

import com.agungkusuma.core.domain.model.Book
import com.agungkusuma.core.domain.repository.BookRepository
import javax.inject.Inject

class GetBooksUseCase @Inject constructor(
    private val repository: BookRepository
) {
    suspend operator fun invoke(): List<Book> {
        return repository.getBooks()
    }
}