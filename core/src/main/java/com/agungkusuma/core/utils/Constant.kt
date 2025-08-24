package com.agungkusuma.core.utils

import com.agungkusuma.core.BuildConfig

object Constants {
    const val DEFAULT_BOOK_QUERY = "subject:chess"

    object ApiComponents {
        const val BASE_URL = BuildConfig.BASE_URL
    }

    object ApiEndpoint {
        const val GET_BOOK = "volumes"
        const val GET_BOOK_DETAIL = "volumes/{id}"
    }

    object KeyParam {
        const val KEY_BOOK = "key_book"
    }
}