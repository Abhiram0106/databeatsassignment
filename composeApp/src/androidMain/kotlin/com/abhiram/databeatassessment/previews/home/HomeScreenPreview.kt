package com.abhiram.databeatassessment.previews.home

import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.abhiram.databeatassessment.core.presentation.theme.AppTheme
import com.abhiram.databeatassessment.feature_home.presentation.HomeScreen
import com.abhiram.databeatassessment.feature_home.presentation.components.NewsListItem
import kotlinx.datetime.LocalDateTime

@Preview(showBackground = true)
@Composable
private fun HomeScreenPreview() {
    AppTheme(false) {
        HomeScreen()
    }
}

@Preview(showBackground = true)
@Composable
private fun NewsListItemPreview() {
    AppTheme(false) {
        NewsListItem(
            sourceName = "BBC News",
            imageUrl = "",
            title = "Bitcoin in the bush - the crypto mine in remote Zambia",
            description = "Bitcoin miners will go to remote locations to take advantage of cheap electricity.",
            publishedAt = LocalDateTime.parse("2025-03-07T17:31:00"),
            onClick = {}
        )
    }
}