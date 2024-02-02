package com.muslim.simplevknewsclient.presentation.news

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.MoreVert
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.muslim.simplevknewsclient.R
import com.muslim.simplevknewsclient.domain.FeedPost
import com.muslim.simplevknewsclient.domain.StatisticsItem
import com.muslim.simplevknewsclient.domain.StatisticsType

@Composable
fun PostCard(
    modifier: Modifier = Modifier,
    feedPost: FeedPost,
    onLikesClickListener: (StatisticsItem) -> Unit,
    onShareClickListener: (StatisticsItem) -> Unit,
    onCommentClickListener: (StatisticsItem) -> Unit,
    onViewsClickListener: (StatisticsItem) -> Unit,
) {
    Card(modifier = modifier) {
        Column(
            modifier = Modifier
                .padding(8.dp)
        ) {
            PostHeader(feedPost)

            Spacer(modifier = Modifier.height(8.dp))

            Text(text = feedPost.contentText)

            Spacer(modifier = Modifier.height(8.dp))

            AsyncImage(
                model = feedPost.contentImageUrl,
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight(),
                contentDescription = null,
                contentScale = ContentScale.FillWidth
            )

            Spacer(modifier = Modifier.height(8.dp))

            Statistics(
                statistics = feedPost.statistics,
                onLikesClickListener = onLikesClickListener,
                onShareClickListener = onShareClickListener,
                onCommentClickListener = onCommentClickListener,
                onViewsClickListener = onViewsClickListener
            )
        }
    }
}

@Composable
private fun PostHeader(
    feedPost: FeedPost
) {
    Row(
        modifier = Modifier
            .fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        AsyncImage(
            model = feedPost.communityImageUrl,
            modifier = Modifier
                .size(50.dp)
                .clip(CircleShape),
            contentDescription = null
        )

        Spacer(modifier = Modifier.width(8.dp))

        Column(
            modifier = Modifier
                .weight(1f)
        ) {
            Text(
                text = feedPost.communityName,
                color = MaterialTheme.colorScheme.onPrimary
            )
            Spacer(modifier = Modifier.width(4.dp))
            Text(
                text = feedPost.publicationDate,
                color = MaterialTheme.colorScheme.onSecondary
            )
        }

        Icon(
            imageVector = Icons.Rounded.MoreVert,
            contentDescription = null,
            tint = MaterialTheme.colorScheme.onSecondary
        )
    }
}

@Composable
private fun Statistics(
    statistics: List<StatisticsItem>,
    onLikesClickListener: (StatisticsItem) -> Unit,
    onShareClickListener: (StatisticsItem) -> Unit,
    onCommentClickListener: (StatisticsItem) -> Unit,
    onViewsClickListener: (StatisticsItem) -> Unit,
) {

    Row {
        Row(
            modifier = Modifier
                .weight(1f)
        ) {
            val viewsItem = statistics.getItemById(StatisticsType.VIEWS)
            IconWithText(
                iconResId = R.drawable.ic_views_count,
                text = viewsItem.count.toString(),
                onItemClickListener = { onViewsClickListener(viewsItem) }
            )
        }
        Row(
            modifier = Modifier
                .weight(1f),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            val shareItem = statistics.getItemById(StatisticsType.SHARES)
            IconWithText(
                iconResId = R.drawable.ic_share,
                text = shareItem.count.toString(),
                onItemClickListener = { onShareClickListener(shareItem) }
            )
            val commentItem = statistics.getItemById(StatisticsType.COMMENTS)
            IconWithText(
                iconResId = R.drawable.ic_comment,
                text = commentItem.count.toString(),
                onItemClickListener = { onCommentClickListener(commentItem) }
            )
            val likesItem = statistics.getItemById(StatisticsType.LIKES)
            IconWithText(
                iconResId = R.drawable.ic_like,
                text = likesItem.count.toString(),
                onItemClickListener = { onLikesClickListener(likesItem) }
            )
        }
    }

}

private fun List<StatisticsItem>.getItemById(type: StatisticsType): StatisticsItem {
    return this.find { it.type == type } ?: throw IllegalStateException()
}

@Composable
private fun IconWithText(
    iconResId: Int,
    text: String,
    onItemClickListener: () -> Unit,
) {
    Row(
        modifier = Modifier.clickable { onItemClickListener() },
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            painter = painterResource(id = iconResId),
            contentDescription = null,
            tint = MaterialTheme.colorScheme.onSecondary
        )

        Spacer(modifier = Modifier.width(4.dp))

        Text(
            text = text,
            color = MaterialTheme.colorScheme.onSecondary
        )
    }
}

//@Preview
//@Composable
//private fun PreviewLight() {
//
//    SimpleVkNewsClientTheme(darkTheme = false) {
//        PostCard()
//    }
//}
//
//@Preview
//@Composable
//private fun PreviewDark() {
//
//    SimpleVkNewsClientTheme(darkTheme = true) {
//        PostCard()
//    }
//}