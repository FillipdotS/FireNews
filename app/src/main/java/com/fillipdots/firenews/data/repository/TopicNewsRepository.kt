package com.fillipdots.firenews.data.repository

import com.fillipdots.firenews.data.api.dto.UserDocumentDto
import com.fillipdots.firenews.domain.model.Article
import com.fillipdots.firenews.domain.model.TopicNews
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class TopicNewsRepository @Inject constructor(
    private val accountRepository: AccountRepository
) {
    var latestTopicNewsArticleMap: Map<String, List<Article>> = mapOf()

    suspend fun getLatestTopicNews(): TopicNews? {
        val doc = accountRepository.currentUserDocument().get().await()

        // Log.i("TopicNewsViewModel", doc.data?.get("topicNews").toString())

        val topicNews = doc.toObject(UserDocumentDto::class.java)?.topicNews

        if (topicNews != null) {
            latestTopicNewsArticleMap = topicNews.articleMap
        }

        return topicNews
    }

    suspend fun update(topicNews: TopicNews) {
        accountRepository.updateUserDocument(mapOf("topicNews" to topicNews))
    }
}