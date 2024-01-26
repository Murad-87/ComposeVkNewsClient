package com.muslim.simplevknewsclient

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.muslim.simplevknewsclient.domain.FeedPost
import com.muslim.simplevknewsclient.domain.PostComment
import com.muslim.simplevknewsclient.ui.theme.SimpleVkNewsClientTheme

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CommentsScreen(
    feedPost: FeedPost,
    comments: List<PostComment>,
    onBackPressed: () -> Unit,
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = "Comments for FeedPost Id: ${feedPost.id}") },
                navigationIcon = {
                    IconButton(onClick = { onBackPressed() }) {
                        Icon(
                            imageVector = Icons.Filled.ArrowBack,
                            contentDescription = null
                        )
                    }
                }
            )
        }
    ) {

        LazyColumn(
            contentPadding = PaddingValues(
                top = 16.dp,
                start = 8.dp,
                end = 8.dp,
                bottom = 72.dp
            )
        ) {
            items(items = comments, key = { it.id }) {comment ->
                CommentItem(comment = comment)
            }
        }
    }
}

@Composable
private fun CommentItem(
    comment: PostComment
) {
    Row(modifier = Modifier
        .fillMaxWidth()
        .padding(horizontal = 16.dp, vertical = 4.dp)
    ) {
        Image(
            modifier = Modifier.size(24.dp),
            painter = painterResource(id = comment.authorAvatarId),
            contentDescription = null
        )

        Spacer(modifier = Modifier.width(8.dp))

        Column {
            Text(
                text = "${comment.authorName} CommentId: ${comment.id}",
                color = MaterialTheme.colorScheme.onPrimary,
                fontSize = 12.sp
            )

            Spacer(modifier = Modifier.height(4.dp))

            Text(
                text = comment.commentText,
                color = MaterialTheme.colorScheme.onPrimary,
                fontSize = 14.sp
            )

            Spacer(modifier = Modifier.height(4.dp))

            Text(
                text = comment.publicationDate,
                color = MaterialTheme.colorScheme.onSecondary,
                fontSize = 12.sp
            )
        }
    }
}

@Preview
@Composable
private fun PreviewComment() {

    SimpleVkNewsClientTheme {
        CommentItem(comment = PostComment(id = 0))
    }
}