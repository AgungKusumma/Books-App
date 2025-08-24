package com.agungkusuma.core.domain.repository

import com.agungkusuma.core.domain.model.Book

interface BookRepository {
    suspend fun getBooks(): List<Book>
}