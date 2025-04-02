package com.abhiram.databeatassessment.feature_home.presentation.components

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import kotlinx.datetime.LocalDateTime

@Composable
expect fun NewsListItem(
    modifier: Modifier,
    sourceName: String,
    imageUrl: String?,
    title: String,
    description: String?,
    publishedAt: LocalDateTime?,
    articleUrl: String,
    activityNotFound: () -> Unit
)