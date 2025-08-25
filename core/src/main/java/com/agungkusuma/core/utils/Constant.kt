package com.agungkusuma.core.utils

import com.agungkusuma.core.BuildConfig

object Constants {
    const val DEFAULT_BOOK_QUERY = "subject:chess"
    const val DEFAULT_MAX_RESULTS = 30

    object ApiComponents {
        const val BASE_URL = BuildConfig.BASE_URL
    }

    object ApiEndpoint {
        const val GET_BOOK = "volumes"
    }

    object KeyParam {
        const val KEY_BOOK = "key_book"
    }
}