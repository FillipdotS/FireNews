package com.fillipdots.firenews.ui.search

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Cancel
import androidx.compose.material.icons.filled.Login
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.fillipdots.firenews.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchBar(
    modifier: Modifier = Modifier,
    searchTerm: String,
    onSearchTermChange: (String) -> Unit,
    onSearchClick: () -> Unit
) {
    TextField(
        modifier = modifier,
        value = searchTerm,
        onValueChange = { text -> onSearchTermChange(text) },
        leadingIcon = {
            Icon(
                Icons.Filled.Search,
                contentDescription = stringResource(R.string.search),
                modifier = Modifier
                    .size(18.dp)
                    .clickable {
                        onSearchClick()
                    }
            )
        },
        trailingIcon = {
            if (searchTerm.isNotEmpty()) {
                Icon(
                    imageVector = Icons.Filled.Cancel,
                    contentDescription = stringResource(R.string.delete_search),
                    modifier = Modifier
                        .size(18.dp)
                        .clickable {
                            onSearchTermChange("")
                        }
                )
            }
        },
        keyboardOptions = KeyboardOptions(imeAction = ImeAction.Search),
        keyboardActions = KeyboardActions(
            onSearch = {
                onSearchClick()
            }
        ),
    )
}

@Preview(showBackground = true)
@Composable
private fun SearchBarPreview() {
    SearchBar(
        searchTerm = "university",
        onSearchTermChange = { },
        onSearchClick = { }
    )
}