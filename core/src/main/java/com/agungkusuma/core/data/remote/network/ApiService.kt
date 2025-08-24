package com.agungkusuma.core.data.remote.network

import com.agungkusuma.core.data.remote.model.BooksResponse
import com.agungkusuma.core.utils.Constants.ApiEndpoint.GET_BOOK
import com.agungkusuma.core.utils.Constants.DEFAULT_BOOK_QUERY
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET(GET_BOOK)
    suspend fun getBooks(
        @Query("q") query: String = DEFAULT_BOOK_QUERY,
    ): BooksResponse
}