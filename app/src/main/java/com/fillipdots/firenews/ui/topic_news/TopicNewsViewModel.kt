package com.fillipdots.firenews.ui.topic_news

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.fillipdots.firenews.data.repository.TopicNewsRepository
import com.fillipdots.firenews.domain.model.TopicNews
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TopicNewsViewModel @Inject constructor(
    private val topicNewsRepository: TopicNewsRepository
) : ViewModel() {
    var uiState = mutableStateOf(TopicNewsUiState())
        private set

    init {
        setupTopicNews()
    }

    fun setTopicNewsToRead() {
        viewModelScope.launch {
            val tn = uiState.value.topicNews
            if (tn != null && !tn.hasBeenViewed) {
                topicNewsRepository.update(tn.copy(hasBeenViewed = true))
            }
        }
    }

    private fun setupTopicNews() {
        viewModelScope.launch {
            val topicNews = topicNewsRepository.getLatestTopicNews()
            Log.i("TopicNewsViewModel", (topicNews != null).toString())
            uiState.value = uiState.value.copy(topicNews = topicNews)
        }
    }
}

data class TopicNewsUiState(
    val topicNews: TopicNews? = null
)