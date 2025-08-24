package com.agungkusuma.core.data.repository

import com.agungkusuma.core.domain.model.Book
import com.agungkusuma.core.domain.repository.BookRepository
import javax.inject.Inject

class BookRepositoryImpl @Inject constructor() : BookRepository {
    override suspend fun getBooks(): List<Book> {
        return listOf(
            Book(
                id = "1",
                title = "The Oxford Companion to Chess",
                authors = "David Hooper, Kenneth Whyld, Ken Whyld",
                publishedDate = "1996",
                thumbnail = "http://books.google.com/books/content?id=edEZAQAAIAAJ&printsec=frontcover&img=1&zoom=1&source=gbs_api"
            ),
            Book(
                id = "2",
                title = "Chess Fundamentals",
                authors = "José Raúl Capablanca",
                publishedDate = "1921",
                thumbnail = "http://books.google.com/books/content?id=G_hyEAAAQBAJ&printsec=frontcover&img=1&zoom=1&edge=curl&source=gbs_api"
            ),
            Book(
                id = "3",
                title = "Bobby Fischer Teaches Chess",
                authors = "Bobby Fischer",
                publishedDate = "1966",
                thumbnail = "http://books.google.com/books/content?id=v6G1AAAAIAAJ&printsec=frontcover&img=1&zoom=1&source=gbs_api"
            ),
            Book(
                id = "4",
                title = "Chess Book for Beginners",
                authors = "A. Gopalaratnam",
                publishedDate = "2006",
                thumbnail = "http://books.google.com/books/content?id=-jC_5eaHpb8C&printsec=frontcover&img=1&zoom=1&edge=curl&source=gbs_api"
            )
        )
    }
}