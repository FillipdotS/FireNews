package com.fillipdots.firenews.domain.model

data class TopicNews(
    val articleMap: Map<String, List<Article>> = mapOf(),
    val hasBeenViewed: Boolean = true,
)