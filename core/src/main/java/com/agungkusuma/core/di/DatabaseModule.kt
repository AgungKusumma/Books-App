package com.agungkusuma.core.di

import android.content.Context
import androidx.room.Room
import com.agungkusuma.core.data.local.BookDao
import com.agungkusuma.core.data.local.BookDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context): BookDatabase = Room.databaseBuilder(
        context, BookDatabase::class.java, "books_db"
    ).build()

    @Provides
    fun provideBookDao(db: BookDatabase): BookDao = db.bookDao()
}