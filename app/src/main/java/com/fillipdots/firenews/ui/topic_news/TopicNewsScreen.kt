package com.fillipdots.firenews.ui.topic_news

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.fillipdots.firenews.R
import com.fillipdots.firenews.domain.model.Article
import com.fillipdots.firenews.ui.common.ArticleListItem

@Composable
fun TopicNewsScreen(
    navController: NavController,
    topicNewsViewModel: TopicNewsViewModel = hiltViewModel(),
) {
    val topicNewsUiState by topicNewsViewModel.uiState
    val topicNews = topicNewsUiState.topicNews

    Log.i("TopicNewsScreen", topicNews?.hasBeenViewed.toString())

    if (topicNews != null) {
        val topicSections = topicNews.articleMap.keys.toList().map { topicName ->
            TopicSection(topicName, topicNews.articleMap[topicName]!!)
        }

        LaunchedEffect(Unit) {
            topicNewsViewModel.setTopicNewsToRead()
        }

        LazyColumn {
            topicSections.forEach { section ->
                item {
                    Text(
                        modifier = Modifier
                            .padding(8.dp)
                            .padding(top = 8.dp),
                        text = section.topicName,
                        style = MaterialTheme.typography.headlineMedium
                    )
                }
                items(items = section.articles) { article ->
                    ArticleListItem(
                        article = article,
                        onClick = { navController.navigate("article/${article.uuid}") }
                    )
                }
            }
        }
    } else {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                stringResource(R.string.no_topic_news),
                style = MaterialTheme.typography.labelLarge,
                textAlign = TextAlign.Center
            )
        }
    }
}

data class TopicSection(
    val topicName: String,
    val articles: List<Article>
)