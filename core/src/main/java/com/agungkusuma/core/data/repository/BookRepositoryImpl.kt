package com.agungkusuma.core.data.repository

import com.agungkusuma.core.data.remote.model.BooksResponse
import com.agungkusuma.core.data.remote.network.ApiService
import com.agungkusuma.core.domain.repository.BookRepository
import com.agungkusuma.core.utils.network.NetworkUtils
import com.agungkusuma.core.utils.network.safeApiCall
import javax.inject.Inject

class BookRepositoryImpl @Inject constructor(
    private val apiService: ApiService,
    private val networkUtils: NetworkUtils
) : BookRepository {
    override suspend fun getBooks(): BooksResponse =
        safeApiCall(networkUtils) {
            apiService.getBooks()
        }
}