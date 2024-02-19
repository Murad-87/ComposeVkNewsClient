package com.muslim.simplevknewsclient.data.mapper

import com.muslim.simplevknewsclient.data.model.CommentsResponseDto
import com.muslim.simplevknewsclient.data.model.NewsFeedResponseDto
import com.muslim.simplevknewsclient.domain.entity.FeedPost
import com.muslim.simplevknewsclient.domain.entity.PostComment
import com.muslim.simplevknewsclient.domain.entity.StatisticsItem
import com.muslim.simplevknewsclient.domain.entity.StatisticsType
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
import kotlin.math.absoluteValue

class NewsFeedMapper {

    fun mapResponseToPosts(responseDto: NewsFeedResponseDto): List<FeedPost> {
        val result = mutableListOf<FeedPost>()

        val posts = responseDto.newsFeedContent.posts
        val groups = responseDto.newsFeedContent.groups

        for (post in posts) {
            val group = groups.find { it.id == post.communityId.absoluteValue } ?: break
            val feedPost = FeedPost(
                id = post.id,
                communityId = post.communityId,
                communityName = group.name,
                publicationDate = mapTimestampDate(post.date),
                communityImageUrl = group.imageUrl,
                contentText = post.text,
                contentImageUrl = post.attachments?.firstOrNull()?.photo?.photoUrls?.lastOrNull()?.url,
                statistics = listOf(
                    StatisticsItem(type = StatisticsType.LIKES, post.likes.count),
                    StatisticsItem(type = StatisticsType.VIEWS, post.views.count),
                    StatisticsItem(type = StatisticsType.SHARES, post.reposts.count),
                    StatisticsItem(type = StatisticsType.COMMENTS, post.comments.count),
                ),
                isLiked = post.likes.userLikes > 0
            )
            result.add(feedPost)
        }
        return result
    }

    fun mapResponseToComments(response: CommentsResponseDto): List<PostComment> {
        val result = mutableListOf<PostComment>()
        val comments = response.content.comments
        val profiles = response.content.profiles

        for (comment in comments) {
            if (comment.text.isBlank()) continue
            val author = profiles.firstOrNull { it.id == comment.authorId } ?: continue
            val postComment = PostComment(
                id = comment.id,
                authorName = "${author.firstName} ${author.lastName}",
                authorAvatarUrl = author.avatarUrl,
                commentText = comment.text,
                publicationDate = mapTimestampDate(comment.date)
            )
            result.add(postComment)
        }
        return result
    }

    private fun mapTimestampDate(timestamp: Long): String {
        val date = Date(timestamp * 1000)
        return SimpleDateFormat("d MMMM yyyy, hh:mm", Locale.getDefault()).format(date)
    }
}