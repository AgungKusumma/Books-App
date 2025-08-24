package com.agungkusuma.core.domain.repository

import com.agungkusuma.core.data.remote.model.BooksResponse

interface BookRepository {
    suspend fun getBooks(): BooksResponse
}