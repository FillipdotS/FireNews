package com.fillipdots.firenews.ui.search

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.fillipdots.firenews.R
import com.fillipdots.firenews.ui.common.ArticleList

@Composable
fun SearchScreen(
    navController: NavController,
    searchViewModel: SearchViewModel = hiltViewModel()
) {
    val searchUiState by searchViewModel.uiState

    ArticleList(
        articles = searchUiState.articles,
        onArticleClick = { uuid -> navController.navigate("article/${uuid}") },
        shouldShowRefresh = false,
        itemOnTop = {
            SearchBar(
                modifier = Modifier.fillMaxWidth(),
                searchTerm = searchUiState.searchTerm,
                onSearchTermChange = { term -> searchViewModel.onSearchTermChange(term) },
                onSearchClick = { searchViewModel.onSearchClick() }
            )
        }
    )

    if (searchUiState.isLoading) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            CircularProgressIndicator(modifier = Modifier.size(64.dp))
        }
    } else if (searchUiState.articles.isEmpty()) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                stringResource(R.string.search_for_results),
                style = MaterialTheme.typography.labelLarge,
                textAlign = TextAlign.Center
            )
        }
    } else {

    }
}