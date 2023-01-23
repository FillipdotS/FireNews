package com.fillipdots.firenews.data.repository

import android.content.res.Resources.NotFoundException
import com.fillipdots.firenews.data.api.NewsApi
import com.fillipdots.firenews.data.api.dto.ArticleDto
import com.fillipdots.firenews.data.api.dto.toArticle
import com.fillipdots.firenews.domain.model.Article
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock
import javax.inject.Inject

class NewsRepository(
    private val newsApi: NewsApi,
    private val apiKey: String,
    private val topicNewsRepository: TopicNewsRepository,
) {
    private val latestNewsMutex = Mutex()
    private var latestNews: List<Article> = emptyList()
    private var latestTopNews: List<Article> = emptyList()
    private var latestSearchNews: List<Article> = emptyList()

    suspend fun fetchLatestNews(refresh: Boolean = false): List<Article> {
        if (refresh || latestNews.isEmpty()) {
            val networkResult = newsApi.getTopHeadlines(
                apiKey,
                mapOf("country" to "gb", "pageSize" to "50")
            )

            val processedArticles =
                networkResult.articles.map { item: ArticleDto -> item.toArticle() }

            this.latestNews = processedArticles
        }

        return latestNewsMutex.withLock { this.latestNews }
    }

    suspend fun getTopArticles(pageSize: Int): List<Article> {
        val networkResult = newsApi.getTopHeadlines(
            apiKey,
            mapOf(
                "country" to "gb",
                "pageSize" to "$pageSize"
            )
        )

        val processedArticles = networkResult.articles.map { articleDto -> articleDto.toArticle() }
        latestTopNews = processedArticles

        return processedArticles
    }

    suspend fun getArticlesViaSearch(query: String): List<Article> {
        val networkResult = newsApi.getEverything(
            apiKey,
            mapOf(
                "language" to "en",
                "q" to query
            )
        )

        val processedArticles = networkResult.articles.map { articleDto -> articleDto.toArticle() }
        latestSearchNews = processedArticles

        return processedArticles
    }

    fun getArticleByUuid(uuid: String): Article {
        val latestNewsArticle = latestNews.find { it.uuid == uuid }

        if (latestNewsArticle != null) {
            return latestNewsArticle
        } else if (latestSearchNews.find { it.uuid == uuid } != null) {
            return latestSearchNews.find { it.uuid == uuid }!!
        } else {
            topicNewsRepository.latestTopicNewsArticleMap.values.forEach { articleList ->
                val potential = articleList.find { it.uuid == uuid }
                if (potential != null) {
                    return potential
                }
            }
        }

        throw NotFoundException("Couldn't find article with uuid $uuid")
    }
}