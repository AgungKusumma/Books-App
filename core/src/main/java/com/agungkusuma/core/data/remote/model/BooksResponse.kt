package com.agungkusuma.core.data.remote.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

data class BooksResponse(
    @SerializedName("items")
    val items: List<BookItem>
)

@Parcelize
data class BookItem(
    @SerializedName("id")
    val id: String,

    @SerializedName("volumeInfo")
    val volumeInfo: VolumeInfo
) : Parcelable

@Parcelize
data class VolumeInfo(
    @SerializedName("title")
    val title: String,

    @SerializedName("authors")
    val authors: List<String>?,

    @SerializedName("publishedDate")
    val publishedDate: String?,

    @SerializedName("description")
    val description: String?,

    @SerializedName("imageLinks")
    val imageLinks: ImageLinks?
) : Parcelable

@Parcelize
data class ImageLinks(
    @SerializedName("thumbnail")
    val thumbnail: String?
) : Parcelable