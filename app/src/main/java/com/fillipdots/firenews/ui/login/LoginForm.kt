package com.fillipdots.firenews.ui.login

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.OffsetMapping
import androidx.compose.ui.text.input.TransformedText
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.fillipdots.firenews.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LoginForm(
    email: String,
    emailOnChange: (String) -> Unit,
    password: String,
    passwordOnChange: (String) -> Unit,
    isLoading: Boolean,
    onSubmit: () -> Unit,
    error: String?,
) {
    val focusManager = LocalFocusManager.current

    Column(
        modifier = Modifier
            .fillMaxHeight()
            .fillMaxWidth()
            .padding(8.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(8.dp, Alignment.CenterVertically)
    ) {
        if (!error.isNullOrEmpty()) {
            Text(error, style = MaterialTheme.typography.labelMedium, color = Color.Red)
        }

        // Email
        OutlinedTextField(
            value = email,
            onValueChange = { emailOnChange(it) },
            label = { Text(stringResource(R.string.email)) },
            maxLines = 1,
            enabled = !isLoading,
            keyboardOptions = KeyboardOptions(imeAction = ImeAction.Next),
            keyboardActions = KeyboardActions(
                onDone = {
                    focusManager.moveFocus(FocusDirection.Down)
                }
            )
        )

        // Password
        OutlinedTextField(
            value = password,
            onValueChange = { passwordOnChange(it) },
            label = { Text(stringResource(R.string.password)) },
            maxLines = 1,
            enabled = !isLoading,
            keyboardOptions = KeyboardOptions(imeAction = ImeAction.Done),
            keyboardActions = KeyboardActions(
                onDone = {
                    onSubmit()
                }
            ),
            visualTransformation = {
                TransformedText(
                    AnnotatedString("*".repeat(it.length)),
                    OffsetMapping.Identity
                )
            }
        )

        Column(modifier = Modifier.padding(8.dp)) {
            Button(
                modifier = Modifier.fillMaxWidth(),
                onClick = { onSubmit() },
                enabled = !isLoading,
            ) {
                Text(stringResource(R.string.login))
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun LoginFormPreview() {
    // LoginForm()
}