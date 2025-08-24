package com.agungkusuma.core.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Book(
    val id: String,
    val title: String,
    val authors: String,
    val publishedDate: String,
    val thumbnail: String
): Parcelable