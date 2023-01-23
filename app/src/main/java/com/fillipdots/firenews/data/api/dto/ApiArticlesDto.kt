package com.fillipdots.firenews.data.api.dto

data class ApiArticlesDto(
    val articles: List<ArticleDto>,
    val status: String,
    val totalResults: Int
)