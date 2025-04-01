package com.abhiram.databeatassessment.feature_home.presentation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.abhiram.databeatassessment.feature_home.presentation.components.NewsListItem
import org.koin.compose.viewmodel.koinViewModel
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.text.input.ImeAction
import com.abhiram.databeatassessment.core.util.UiText
import com.abhiram.databeatassessment.feature_home.presentation.state_and_actions.HomeUiAction
import com.abhiram.databeatassessment.feature_home.presentation.state_and_actions.HomeUiState
import databeatassessment.composeapp.generated.resources.Res
import databeatassessment.composeapp.generated.resources.search_for_articles
import databeatassessment.composeapp.generated.resources.ic_search
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource

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
        uiState = uiState,
        onUiAction = viewModel::onUiAction,
        onClickArticle = {}
    )

}

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    uiState: HomeUiState,
    onUiAction: (HomeUiAction) -> Unit,
    onClickArticle: (articleUrl: String) -> Unit
) {
    Column(
        modifier = modifier.fillMaxSize()
            .padding(horizontal = 15.dp)
    ) {

        OutlinedTextField(
            value = uiState.searchQuery,
            onValueChange = { onUiAction(HomeUiAction.OnQueryChanged(it)) },
            label = {
                Text(
                    text = stringResource(Res.string.search_for_articles),
                    style = MaterialTheme.typography.body1,
                )
            },
            trailingIcon = {
                Icon(
                    painter = painterResource(Res.drawable.ic_search),
                    contentDescription = null,
                    tint = MaterialTheme.colors.onSurface
                )
            },
            keyboardOptions = KeyboardOptions(imeAction = ImeAction.Search),
            singleLine = true,
            shape = CircleShape,
        )

        LazyVerticalGrid(
            columns = GridCells.Adaptive(200.dp),
            contentPadding = PaddingValues(vertical = 10.dp),
            horizontalArrangement = Arrangement.SpaceEvenly,
            verticalArrangement = Arrangement.spacedBy(4.dp),
            modifier = Modifier.fillMaxWidth(),
        ) {
            items(
                items = uiState.newsItems,
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

            if (uiState.isLoading) {
                item(
                    span = { GridItemSpan(maxLineSpan) }
                ) {
                    Box(
                        modifier = Modifier.fillMaxWidth(),
                        contentAlignment = Alignment.Center
                    ) {
                        CircularProgressIndicator(
                            color = MaterialTheme.colors.onSurface
                        )
                    }
                }
            }
        }
    }
}