package com.agungkusuma.core.data.mapper

import com.agungkusuma.core.data.local.BookEntity
import com.agungkusuma.core.data.remote.model.BookItem
import com.agungkusuma.core.data.remote.model.ImageLinks
import com.agungkusuma.core.data.remote.model.VolumeInfo

fun BookItem.toEntity(): BookEntity {
    return BookEntity(
        id = id,
        title = volumeInfo.title,
        authors = volumeInfo.authors?.joinToString(", "),
        publishedDate = volumeInfo.publishedDate.orEmpty(),
        description = volumeInfo.description.orEmpty(),
        thumbnail = volumeInfo.imageLinks?.thumbnail
    )
}

fun BookEntity.toBookItem(): BookItem = BookItem(
    id = id,
    volumeInfo = VolumeInfo(
        title = title,
        authors = authors?.split(", ")?.filter { it.isNotBlank() },
        publishedDate = publishedDate,
        description = description,
        imageLinks = ImageLinks(thumbnail)
    )
)