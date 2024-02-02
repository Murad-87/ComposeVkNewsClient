package com.muslim.simplevknewsclient.data.mapper

import com.muslim.simplevknewsclient.data.model.NewsFeedResponseDto
import com.muslim.simplevknewsclient.domain.FeedPost
import com.muslim.simplevknewsclient.domain.StatisticsItem
import com.muslim.simplevknewsclient.domain.StatisticsType
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
                publicationDate = post.date.toString(),
                communityImageUrl = group.imageUrl,
                contentText = post.text,
                contentImageUrl = post.attachments?.firstOrNull()?.photo?.photoUrls?.lastOrNull()?.url,
                statistics = listOf(
                    StatisticsItem(type = StatisticsType.LIKES, post.likes.count),
                    StatisticsItem(type = StatisticsType.VIEWS, post.views.count),
                    StatisticsItem(type = StatisticsType.SHARES, post.reposts.count),
                    StatisticsItem(type = StatisticsType.COMMENTS, post.comments.count),
                )
            )
            result.add(feedPost)
        }
        return result
    }
}