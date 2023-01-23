package com.fillipdots.firenews.ui.topics

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.outlined.Notifications
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.fillipdots.firenews.R
import com.fillipdots.firenews.domain.model.Topic

@Composable
fun TopicListItem(
    topic: Topic,
    onClick: (Topic) -> Unit,
    onDeleteClick: (Topic) -> Unit,
    onNotificationClick: (Topic) -> Unit,
) {
    Row(
        modifier = Modifier
            .clickable { onClick(topic) }
            .padding(8.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Text(topic.name)
        Row(
            Modifier.weight(1f),
            horizontalArrangement = Arrangement.End
        ) {
            IconButton(onClick = { onNotificationClick(topic) }) {
                Icon(
                    imageVector = if (topic.notificationsOn) Icons.Filled.Notifications else Icons.Outlined.Notifications,
                    contentDescription = stringResource(R.string.toggle_notifications)
                )
            }
            IconButton(onClick = { onDeleteClick(topic) }) {
                Icon(
                    Icons.Filled.Delete,
                    contentDescription = stringResource(R.string.delete)
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun TopicListItemPreview() {
    TopicListItem(Topic(id = "123", name = "University", notificationsOn = true), {}, {}, {})
}

@Preview(showBackground = true)
@Composable
private fun TopicListItemPreviewNotificationsOff() {
    TopicListItem(Topic(id = "123", name = "University", notificationsOn = false), {}, {}, {})
}