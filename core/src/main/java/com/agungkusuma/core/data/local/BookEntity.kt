package com.agungkusuma.core.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "books")
data class BookEntity(
    @PrimaryKey val id: String,
    val title: String,
    val authors: String?,
    val publishedDate: String?,
    val description: String?,
    val thumbnail: String?
)