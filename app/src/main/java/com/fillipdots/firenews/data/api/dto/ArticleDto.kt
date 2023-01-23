package com.fillipdots.firenews.data.api.dto

import com.fillipdots.firenews.domain.model.Article
import java.util.Date

data class ArticleDto(
    val author: String,
    val content: String,
    val description: String,
    val publishedAt: String,
    val source: Source,
    val title: String,
    val url: String,
    val urlToImage: String
)

fun ArticleDto.toArticle(): Article {
    return Article(
        title = title,
        source =source.name,
        url =url,
        urlToImage = urlToImage,
        author = author,
        content = content,
        publishedAt = publishedAt
    )
}