package com.agungkusuma.core.data.repository

import com.agungkusuma.core.data.local.BookDao
import com.agungkusuma.core.data.local.BookEntity
import com.agungkusuma.core.data.mapper.toEntity
import com.agungkusuma.core.data.remote.network.ApiService
import com.agungkusuma.core.domain.repository.BookRepository
import com.agungkusuma.core.utils.network.NetworkUtils
import com.agungkusuma.core.utils.network.safeApiCall
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class BookRepositoryImpl @Inject constructor(
    private val apiService: ApiService,
    private val bookDao: BookDao,
    private val networkUtils: NetworkUtils
) : BookRepository {
    override fun observeBooks(): Flow<List<BookEntity>> = bookDao.getAllBooks()

    override fun searchBooks(query: String): Flow<List<BookEntity>> = bookDao.searchBooks(query)

    override suspend fun refreshBooks() {
        val response = safeApiCall(networkUtils) {
            apiService.getBooks()
        }

        val entities = response.items.map { it.toEntity() }

        if (entities.isNotEmpty()) {
            bookDao.insertBooks(entities)
        }
    }
}