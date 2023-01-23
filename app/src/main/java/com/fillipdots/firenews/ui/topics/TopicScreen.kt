package com.fillipdots.firenews.ui.topics

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.fillipdots.firenews.R
import com.fillipdots.firenews.domain.model.Topic
import com.fillipdots.firenews.ui.Route

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopicScreen(
    navController: NavController,
    topicViewModel: TopicViewModel = hiltViewModel(),
) {
    val topicUiState by topicViewModel.uiState
    val topics = topicUiState.topics.collectAsState(emptyList())

    val editDialogOpen = remember { mutableStateOf(false) }
    val editDialogNew = remember { mutableStateOf(false) }

    Scaffold(
        floatingActionButton = {
            FloatingActionButton(onClick = {
                topicViewModel.setEditingTopic(Topic(name = "", notificationsOn = true))
                editDialogNew.value = true
                editDialogOpen.value = true
            }) {
                Icon(Icons.Filled.Add, contentDescription = stringResource(R.string.add_new_topic))
            }
        }
    ) { innerPadding ->
        Box(modifier = Modifier.padding(innerPadding)) {
            TopicsList(
                topics = topics.value,
                onTopicClick = { topic ->
                    topicViewModel.setEditingTopic(topic)
                    editDialogNew.value = false
                    editDialogOpen.value = true
                },
                onTopicDeleteClick = { topic -> topicViewModel.onDeleteClick(topic) },
                onTopicNewsButtonClick = { navController.navigate(Route.TopicNews.routeString) },
                onTopicNotificationToggle = { topic -> topicViewModel.onNotificationToggleClick(topic) }
            )

            TopicEditDialog(
                open = editDialogOpen.value,
                onDismiss = { editDialogOpen.value = false },
                onSave = {
                    topicViewModel.onSaveClick()
                    editDialogOpen.value = false
                },
                isCreatingNew = editDialogNew.value,
                topicName = topicUiState.name,
                onTopicNameChange = { topicViewModel.onNameChange(it) }
            )

        }
    }
}