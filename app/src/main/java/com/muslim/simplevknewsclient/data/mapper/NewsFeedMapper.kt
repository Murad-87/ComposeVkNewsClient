package com.muslim.simplevknewsclient.data.mapper

import com.muslim.simplevknewsclient.data.model.NewsFeedResponseDto
import com.muslim.simplevknewsclient.domain.FeedPost
import com.muslim.simplevknewsclient.domain.StatisticsItem
import com.muslim.simplevknewsclient.domain.StatisticsType
import java.sql.Timestamp
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
                communityName = group.name,
                publicationDate = mapTimestampDate(post.date * 1000),
                communityImageUrl = group.imageUrl,
                contentText = post.text,
                contentImageUrl = post.attachments?.firstOrNull()?.photo?.photoUrls?.lastOrNull()?.url,
                statistics = listOf(
                    StatisticsItem(type = StatisticsType.LIKES, post.likes.count),
                    StatisticsItem(type = StatisticsType.VIEWS, post.views.count),
                    StatisticsItem(type = StatisticsType.SHARES, post.reposts.count),
                    StatisticsItem(type = StatisticsType.COMMENTS, post.comments.count),
                ),
                isFavourite = post.isFavourite
            )
            result.add(feedPost)
        }
        return result
    }

    private fun mapTimestampDate(timestamp: Long): String {
        val date = Date(timestamp)
        return SimpleDateFormat("d MMMM yyyy, hh:mm", Locale.getDefault()).format(date)
    }
}