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
//
//@Composable
//fun NewsListItem(
//    modifier: Modifier = Modifier,
//    sourceName: String,
//    imageUrl: String?,
//    title: String,
//    description: String?,
//    publishedAt: LocalDateTime?,
//) {
//
//    Column(
//        modifier = modifier
//            .clip(RoundedCornerShape(5))
//            .background(MaterialTheme.colors.surface)
//            .padding(horizontal = 10.dp, vertical = 8.dp)
//            .clickable { onClick() },
//    ) {
//        if (imageUrl != null) {
//            SubcomposeAsyncImage(
//                model = imageUrl,
//                contentDescription = null,
//                contentScale = ContentScale.Crop,
//                loading = {
//                    CircularProgressIndicator(
//                        color = MaterialTheme.colors.onSurface,
//                        modifier = Modifier.size(64.dp)
//                    )
//                },
//                modifier = Modifier.height(192.dp)
//                    .fillMaxWidth()
//                    .clip(RoundedCornerShape(5))
//            )
//        } else {
//            Icon(
//                painter = painterResource(Res.drawable.ic_block),
//                contentDescription = null,
//                modifier = Modifier.height(128.dp)
//                    .fillMaxWidth()
//                    .clip(RoundedCornerShape(5))
//            )
//        }
//
//        Spacer(modifier = Modifier.height(4.dp))
//
//        Row(
//            modifier = Modifier.fillMaxWidth(),
//            horizontalArrangement = Arrangement.SpaceBetween,
//            verticalAlignment = Alignment.CenterVertically
//        ) {
//            Text(
//                text = sourceName,
//                style = MaterialTheme.typography.body1
//            )
//            Text(
//                text = publishedAt?.toFormattedString() ?: "",
//                style = MaterialTheme.typography.body1
//            )
//        }
//        Spacer(modifier = Modifier.height(4.dp))
//        Text(
//            text = title,
//            style = MaterialTheme.typography.h6,
//            fontWeight = FontWeight.SemiBold
//        )
//        Spacer(modifier = Modifier.height(6.dp))
//        Text(
//            text = description ?: stringResource(Res.string.no_description),
//            style = MaterialTheme.typography.body1
//        )
//    }
//}