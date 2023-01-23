package com.fillipdots.firenews.ui.common

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ListItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.fillipdots.firenews.domain.model.Article
import com.fillipdots.firenews.util.UtilGlideImage

@OptIn(ExperimentalMaterial3Api::class, ExperimentalGlideComposeApi::class)
@Composable
fun ArticleListItem(
    modifier: Modifier = Modifier,
    article: Article,
    onClick: () -> Unit
) {
    val imageModifier = Modifier.width(128.dp)

    ListItem(
        modifier = Modifier
            .clickable(onClick = onClick)
            .padding(0.dp),
        leadingContent = {
            // GlideImage doesn't work in inspection mode
            UtilGlideImage(
                modifier = imageModifier,
                source = article.urlToImage,
                contentDescription = article.title
            )

        },
        headlineText = { Text(article.title) },
        overlineText = { Text(article.source) },
    )
}

@Preview(showBackground = true)
@Composable
private fun ArticleListItemPreview() {
    ArticleListItem(
        article = Article(
            title = "Swansea cat gets stuck in a tree",
            source = "BBC News",
            url = "google.com",
            urlToImage = ""
        ),
        onClick = {}
    )
}