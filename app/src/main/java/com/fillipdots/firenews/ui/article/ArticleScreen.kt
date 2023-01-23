package com.fillipdots.firenews.ui.article

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.fillipdots.firenews.ui.common.ArticleList

@Composable
fun ArticleScreen(
    articleViewModel: ArticleViewModel = hiltViewModel(),
    navController: NavHostController,
) {
    val articleUiState by articleViewModel.uiState

    if (articleUiState.isLoading) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            CircularProgressIndicator(modifier = Modifier.size(64.dp))
        }
    } else {
        ArticleList(
            articles = articleUiState.articles!!,
            onArticleClick = { uuid -> navController.navigate("article/${uuid}") },
            refreshClick = { articleViewModel.refreshPosts(true) },
            shouldShowRefresh = true
        )
    }
}