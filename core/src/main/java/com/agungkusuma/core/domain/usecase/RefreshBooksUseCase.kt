package com.agungkusuma.core.domain.usecase

import com.agungkusuma.core.domain.repository.BookRepository
import javax.inject.Inject

class RefreshBooksUseCase @Inject constructor(
    private val repository: BookRepository
) {
    suspend operator fun invoke() = repository.refreshBooks()
}