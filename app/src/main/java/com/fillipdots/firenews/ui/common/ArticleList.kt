package com.fillipdots.firenews.ui.common

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material3.Button
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.fillipdots.firenews.R
import com.fillipdots.firenews.domain.model.Article
import java.util.Date

@Composable
fun ArticleList(
    articles: List<Article>,
    onArticleClick: (uuid: String) -> Unit,
    refreshClick: () -> Unit = { },
    shouldShowRefresh: Boolean,
    itemOnTop: @Composable () -> Unit = {},
) {
    LazyColumn {
        if (shouldShowRefresh) {
            item {
                Button(
                    modifier = Modifier.padding(8.dp).fillMaxWidth(),
                    onClick = { refreshClick() }
                ) {
                    Icon(Icons.Filled.Refresh, contentDescription = stringResource(R.string.refresh))
                    Text(stringResource(R.string.refresh))
                }
            }
        }

        item {
            itemOnTop()
        }

        items(items = articles, key = { article -> article.uuid}) {
            ArticleListItem(
                article = it,
                onClick = {
                    onArticleClick(it.uuid)
                }
            )
            Divider()
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun ArticleListPreview() {
    val articleList = List(100) {
        Article(
            title = "Swansea cat gets stuck in a tree",
            source = "BBC News",
            url = "google.com",
            content = "Lorem ipsum",
            publishedAt = Date().toString(),
            author = "Fillip Serov",
            urlToImage = ""
        )
    }

    ArticleList(
        articles = articleList,
        onArticleClick = { },
        refreshClick = { },
        shouldShowRefresh = true,
        itemOnTop = {},
    )
}