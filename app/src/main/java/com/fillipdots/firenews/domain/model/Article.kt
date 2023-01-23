package com.fillipdots.firenews.domain.model

import java.util.Date
import java.util.UUID

data class Article(
    val uuid: String = UUID.randomUUID().toString(),
    val title: String = "",
    val source: String = "",
    val url: String = "",
    val urlToImage: String? = null,
    val author: String? = null,
    val content: String? = null,
    val publishedAt: String? = null,
)