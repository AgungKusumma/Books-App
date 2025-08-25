package com.agungkusuma.core.domain.repository

import com.agungkusuma.core.data.local.BookEntity
import kotlinx.coroutines.flow.Flow

interface BookRepository {
    fun observeBooks(): Flow<List<BookEntity>>
    fun searchBooks(query: String): Flow<List<BookEntity>>
    suspend fun refreshBooks()
}