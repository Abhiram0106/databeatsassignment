package com.abhiram.databeatassessment.feature_home.presentation.components

import android.content.Intent
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.core.uri.Uri
import coil3.compose.SubcomposeAsyncImage
import com.abhiram.databeatassessment.feature_home.util.toFormattedString
import databeatassessment.composeapp.generated.resources.Res
import databeatassessment.composeapp.generated.resources.ic_block
import databeatassessment.composeapp.generated.resources.no_description
import kotlinx.datetime.LocalDateTime
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource
import androidx.core.net.toUri

@Composable
actual fun NewsListItem(
    modifier: Modifier,
    sourceName: String,
    imageUrl: String?,
    title: String,
    description: String?,
    publishedAt: LocalDateTime?,
    articleUrl: String,
    activityNotFound: () -> Unit
) {

    val context = LocalContext.current

    Column(
        modifier = modifier
            .clip(RoundedCornerShape(5))
            .background(MaterialTheme.colors.surface)
            .padding(horizontal = 10.dp, vertical = 8.dp)
            .clickable {
                val webIntent: Intent = Intent(Intent.ACTION_VIEW, articleUrl.toUri())
                try {
                    val packageManager = context.packageManager
                    if (webIntent.resolveActivity(packageManager) != null) {
                        context.startActivity(webIntent)
                    } else {
                        activityNotFound()
                    }
                } catch (e: Exception) {
                    e.printStackTrace()
                    activityNotFound()
                }
            },
    ) {
        if (imageUrl != null) {
            SubcomposeAsyncImage(
                model = imageUrl,
                contentDescription = null,
                contentScale = ContentScale.Crop,
                loading = {
                    CircularProgressIndicator(
                        color = MaterialTheme.colors.onSurface,
                        modifier = Modifier.size(64.dp)
                    )
                },
                modifier = Modifier.height(192.dp)
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(5))
            )
        } else {
            Icon(
                painter = painterResource(Res.drawable.ic_block),
                contentDescription = null,
                modifier = Modifier.height(128.dp)
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(5))
            )
        }

        Spacer(modifier = Modifier.height(4.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = sourceName,
                style = MaterialTheme.typography.body1
            )
            Text(
                text = publishedAt?.toFormattedString() ?: "",
                style = MaterialTheme.typography.body1
            )
        }
        Spacer(modifier = Modifier.height(4.dp))
        Text(
            text = title,
            style = MaterialTheme.typography.h6,
            fontWeight = FontWeight.SemiBold
        )
        Spacer(modifier = Modifier.height(6.dp))
        Text(
            text = description ?: stringResource(Res.string.no_description),
            style = MaterialTheme.typography.body1
        )
    }
}