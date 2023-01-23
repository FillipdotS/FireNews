package com.fillipdots.firenews.ui.topics

import android.os.Build
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.app.NotificationManagerCompat
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkManager
import com.fillipdots.firenews.R
import com.fillipdots.firenews.domain.model.Topic
import com.fillipdots.firenews.worker.TopicNotificationWorker
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.PermissionStatus
import com.google.accompanist.permissions.rememberPermissionState
import kotlin.random.Random

@OptIn(ExperimentalFoundationApi::class, ExperimentalPermissionsApi::class)
@Composable
fun TopicsList(
    topics: List<Topic>,
    onTopicClick: (Topic) -> Unit,
    onTopicDeleteClick: (Topic) -> Unit,
    onTopicNotificationToggle: (Topic) -> Unit,
    onTopicNewsButtonClick: () -> Unit,
) {
    // Only used for API 33=< level
    val notificationPermission =
        rememberPermissionState(permission = android.Manifest.permission.POST_NOTIFICATIONS)

    // Boolean is correct on <33 API level
    val notificationEnabled = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
        notificationPermission.status == PermissionStatus.Granted
    } else {
        NotificationManagerCompat.from(LocalContext.current).areNotificationsEnabled()
    }

    // For debugging
    val wm = WorkManager.getInstance(LocalContext.current)
    val work = OneTimeWorkRequestBuilder<TopicNotificationWorker>().build()
    val onDebugButtonClick = {
        wm.enqueue(work)
    }

    LazyColumn(
        contentPadding = PaddingValues(bottom = 88.dp)
    ) {
        item {
            Button(
                modifier = Modifier
                    .padding(8.dp)
                    .fillMaxWidth(),
                onClick = { onTopicNewsButtonClick() }
            ) {
                Text(stringResource(R.string.go_to_topic_news))
            }
        }

        item {
            if (!notificationEnabled) {
                ElevatedCard(
                    modifier = Modifier.padding(16.dp),
                    colors = CardDefaults.cardColors(
                        containerColor = MaterialTheme.colorScheme.errorContainer
                    ),
                ) {
                    Column(
                        modifier = Modifier.padding(16.dp),
                        verticalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        Text(stringResource(R.string.notifications_disabled))

                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.End
                            ) {
                                Button(
                                    onClick = { notificationPermission.launchPermissionRequest() }
                                ) {
                                    Text(stringResource(R.string.give_permission))
                                }
                            }
                        }
                    }
                }
            }
        }

        items(
            items = topics,
            key = { topic -> topic.id!! }
        ) {
            Box(Modifier.animateItemPlacement()) {
                TopicListItem(
                    it,
                    onClick = { topic -> onTopicClick(topic) },
                    onDeleteClick = { topic -> onTopicDeleteClick(topic) },
                    onNotificationClick = { topic -> onTopicNotificationToggle(topic)}
                )
                Divider()
            }
        }

        item {
            OutlinedButton(
                modifier = Modifier.padding(8.dp),
                onClick = { onDebugButtonClick() }
            ) {
                Text(stringResource(R.string.trigger_topic_update))
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun TopicsListPreview() {
    val fakeTopics: List<Topic> =
        List(100) {
            Topic(
                id = "$it",
                name = "$it",
                notificationsOn = Random.nextBoolean()
            )
        }

    TopicsList(topics = fakeTopics, {}, {}, {}, {})
}