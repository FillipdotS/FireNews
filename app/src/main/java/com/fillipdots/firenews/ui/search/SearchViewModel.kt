package com.fillipdots.firenews.ui.search

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.fillipdots.firenews.data.repository.NewsRepository
import com.fillipdots.firenews.domain.model.Article
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val newsRepository: NewsRepository,
) : ViewModel() {
    var uiState = mutableStateOf(SearchUiState())
        private set

    fun onSearchTermChange(term: String) {
        uiState.value = uiState.value.copy(searchTerm = term)
    }

    fun onSearchClick() {
        uiState.value = uiState.value.copy(articles = emptyList(), isLoading = true)

        viewModelScope.launch {
            val articles = newsRepository.getArticlesViaSearch(uiState.value.searchTerm)

            uiState.value = uiState.value.copy(articles = articles, isLoading = false)
        }
    }
}

data class SearchUiState(
    val searchTerm: String = "",
    val articles: List<Article> = listOf(),
    val isLoading: Boolean = false,
)