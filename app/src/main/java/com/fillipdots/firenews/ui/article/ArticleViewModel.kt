package com.fillipdots.firenews.ui.article

import android.content.res.Resources
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.fillipdots.firenews.data.repository.NewsRepository
import com.fillipdots.firenews.domain.model.Article
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import java.util.UUID
import javax.inject.Inject

@HiltViewModel
class ArticleViewModel @Inject constructor(
    private val newsRepository: NewsRepository,
    savedStateHandle: SavedStateHandle
) : ViewModel() {
    var uiState = mutableStateOf(ArticleUiState())
        private set

    init {
        refreshPosts(refresh = false)

        savedStateHandle.get<String>("uuid")?.let { uuid ->
            chooseArticle(uuid)
        }
    }

    fun refreshPosts(refresh: Boolean) {
        uiState.value = uiState.value.copy(isLoading = true, articles = emptyList())

        viewModelScope.launch {
            val articles = newsRepository.fetchLatestNews(refresh = refresh)
            uiState.value = uiState.value.copy(articles = articles, isLoading = false)
        }
    }

    private fun chooseArticle(uuid: String) {
        val article = newsRepository.getArticleByUuid(uuid)

        uiState.value = uiState.value.copy(chosenArticle = article)
    }
}

data class ArticleUiState(
    val articles: List<Article>? = null,
    val isLoading: Boolean = false,
    val chosenArticle: Article? = null,
)