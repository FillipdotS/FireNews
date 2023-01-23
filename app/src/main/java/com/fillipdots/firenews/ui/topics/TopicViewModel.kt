package com.fillipdots.firenews.ui.topics

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.fillipdots.firenews.data.repository.TopicNewsRepository
import com.fillipdots.firenews.data.repository.TopicRepository
import com.fillipdots.firenews.domain.model.Topic
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TopicViewModel @Inject constructor(
    private val topicRepository: TopicRepository,
    private val topicNewsRepository: TopicNewsRepository
) : ViewModel() {
    var uiState = mutableStateOf(TopicUiState(topics = topicRepository.topics))
        private set

    fun onSaveClick() {
        viewModelScope.launch {
            val newTopic = Topic(id = uiState.value.editingId, name = uiState.value.name, notificationsOn = true)
            if (newTopic.id != null) {
                topicRepository.update(newTopic)
            } else {
                topicRepository.save(newTopic)
            }
        }
    }

    fun onDeleteClick(topic: Topic) {
        viewModelScope.launch {
            topicRepository.delete(topic)
        }
    }

    fun onNotificationToggleClick(topic: Topic) {
        viewModelScope.launch {
            topicRepository.update(topic.copy(notificationsOn = !topic.notificationsOn))
        }
    }

    fun setEditingTopic(topic: Topic) {
        uiState.value = uiState.value.copy(name = topic.name, editingId = topic.id)
    }

    fun onNameChange(name: String) {
        uiState.value = uiState.value.copy(name = name)
    }
}

data class TopicUiState(
    val topics: Flow<List<Topic>>,
    val editingId: String? = null,
    val name: String = "",
)