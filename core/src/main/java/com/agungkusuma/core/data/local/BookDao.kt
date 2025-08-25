package com.agungkusuma.core.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface BookDao {
    @Query("SELECT * FROM books ORDER BY title ASC")
    fun getAllBooks(): Flow<List<BookEntity>>


    @Query("SELECT * FROM books WHERE title LIKE '%' || :query || '%' OR authors LIKE '%' || :query || '%' ORDER BY title ASC")
    fun searchBooks(query: String): Flow<List<BookEntity>>


    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertBooks(books: List<BookEntity>)
}