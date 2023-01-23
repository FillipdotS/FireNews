package com.fillipdots.firenews.ui.topics

import androidx.compose.material3.AlertDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.fillipdots.firenews.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopicEditDialog(
    open: Boolean,
    onDismiss: () -> Unit,
    onSave: () -> Unit,
    isCreatingNew: Boolean,
    topicName: String,
    onTopicNameChange: (String) -> Unit,
) {
    if (open) {
        AlertDialog(
            title = {
                val title = if (isCreatingNew) R.string.add_new_topic else R.string.edit_topic
                Text(stringResource(title))
            },
            onDismissRequest = { onDismiss() },
            confirmButton = {
                TextButton(
                    onClick = { onSave() }
                ) {
                    Text(stringResource(R.string.save))
                }
            },
            dismissButton = {
                TextButton(
                    onClick = { onDismiss() }
                ) {
                    Text(stringResource(R.string.cancel))
                }
            },
            text = {
                OutlinedTextField(
                    value = topicName,
                    onValueChange = { onTopicNameChange(it) },
                    label = { Text(stringResource(R.string.topic)) },
                )
            },
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun TopicEditDialogPreview() {
    TopicEditDialog(
        open = true,
        onDismiss = { },
        onSave = { },
        isCreatingNew = true,
        topicName = "My topic",
        onTopicNameChange = { }
    )
}