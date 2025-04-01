package com.abhiram.databeatassessment.feature_home.presentation

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.abhiram.databeatassessment.feature_home.domain.model.NewsItem
import com.abhiram.databeatassessment.feature_home.presentation.components.NewsListItem
import org.koin.compose.viewmodel.koinViewModel
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import com.abhiram.databeatassessment.core.util.UiText

@Composable
fun HomeRoot(
    viewModel: HomeViewModel = koinViewModel(),
    onShowSnackBar: suspend (message: UiText, actionLabel: UiText?) -> Boolean,
) {

    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    LaunchedEffect(uiState.snackBarMessage) {
        if (uiState.snackBarMessage != null) {
            onShowSnackBar(uiState.snackBarMessage!!, null)
            viewModel.clearSnackBar()
        }
    }

    HomeScreen(
        newsItems = uiState.newsItems,
        isLoading = uiState.isLoading,
        onClickArticle = {}
    )

}

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    newsItems: List<NewsItem>,
    isLoading: Boolean,
    onClickArticle: (articleUrl: String) -> Unit
) {
    Column(modifier = modifier.fillMaxSize()) {
        LazyVerticalGrid(
            columns = GridCells.Adaptive(200.dp),
            contentPadding = PaddingValues(all = 4.dp),
            modifier = Modifier.fillMaxWidth(),
        ) {
            items(
                items = newsItems,
            ) {
                NewsListItem(
                    sourceName = it.sourceName,
                    imageUrl = it.imageUrl,
                    title = it.title,
                    description = it.description,
                    publishedAt = it.publishedAt,
                    onClick = { onClickArticle(it.articleUrl) }
                )
            }

            if (isLoading) {
                item {
                    Box(modifier = Modifier.fillMaxWidth(), contentAlignment = Alignment.Center) {
                        CircularProgressIndicator(
                            color = MaterialTheme.colors.onSurface
                        )
                    }
                }
            }
        }
    }
}