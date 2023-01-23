package com.fillipdots.firenews.ui.article

import android.content.Intent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.OpenInBrowser
import androidx.compose.material.icons.filled.Share
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.content.ContextCompat.startActivity
import androidx.hilt.navigation.compose.hiltViewModel
import com.fillipdots.firenews.R
import com.fillipdots.firenews.util.UtilGlideImage

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ArticleDetail(
    articleViewModel: ArticleViewModel = hiltViewModel()
) {
    val articleUiState by articleViewModel.uiState
    val article = articleUiState.chosenArticle

    val uriHandler = LocalUriHandler.current

    val context = LocalContext.current

    if (article != null) {
        Scaffold(
            floatingActionButton = {
                FloatingActionButton(onClick = {
                    uriHandler.openUri(article.url)
                }) {
                    Icon(
                        Icons.Filled.OpenInBrowser,
                        contentDescription = stringResource(R.string.add_new_topic)
                    )
                }
            }
        ) { innerPadding ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding)
                    .verticalScroll(rememberScrollState())
            ) {
                UtilGlideImage(
                    modifier = Modifier.fillMaxWidth(),
                    source = article.urlToImage,
                    contentDescription = article.title,
                    contentScale = ContentScale.FillWidth
                )
                Row(
                    Modifier
                        .padding(horizontal = 8.dp)
                        .padding(top = 8.dp)
                ) {
                    Text(article.source, style = MaterialTheme.typography.labelMedium)
                    Spacer(modifier = Modifier.weight(1f))
                    Text(
                        article.publishedAt.toString(),
                        style = MaterialTheme.typography.labelSmall
                    )
                }
                Column(modifier = Modifier.padding(8.dp)) {
                    Text(article.title, style = MaterialTheme.typography.headlineSmall)

                    Spacer(modifier = Modifier.size(16.dp))

                    Text(article.content ?: stringResource(R.string.no_text_content), style = MaterialTheme.typography.bodyMedium)

                    Button(
                        modifier = Modifier.padding(top = 8.dp),
                        contentPadding = PaddingValues(start = 16.dp, end = 24.dp),
                        onClick = {
                            val sendIntent = Intent().apply {
                                action = Intent.ACTION_SEND
                                putExtra(Intent.EXTRA_TEXT, article.url)
                                type = "text/plain"
                            }

                            val shareIntent = Intent.createChooser(sendIntent, null)
                            startActivity(context, shareIntent, null)
                        }
                    ) {
                        Icon(
                            Icons.Filled.Share,
                            contentDescription = stringResource(R.string.share),
                            modifier = Modifier
                                .size(18.dp)
                                .padding(start = 0.dp)
                        )
                        Spacer(modifier = Modifier.size(8.dp))
                        Text(stringResource(R.string.share))
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ArticleDetailPreview() {
//    val previewArticle = Article(
//        title = "Swansea cat gets stuck in a tree",
//        source = "BBC News",
//        content = "When The Bear appeared on screens in the US this year, it arrived with few expectations. A small, indie-feeling drama about an ailing Chicago sandwich shop, it came with a semi-recognisable cast and … [+9946 chars]",
//        publishedAt = Date(),
//        urlToImage = "",
//        url = "google.com"
//    )

    ArticleDetail()
}