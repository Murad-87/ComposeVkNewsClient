package com.muslim.simplevknewsclient.domain

import com.muslim.simplevknewsclient.R

data class FeedPost(
    val id: Int = 0,
    val communityName: String = "/dev/null",
    val publicationDate: String = "14:00",
    val avatarResId: Int = R.drawable.post_comunity_thumbnail,
    val contentText: String = "Lorem ipsum dolor sit amet, consectetur adipiscing elit.",
    val contentImageResId: Int = R.drawable.post_content_image,
    val statistics: List<StatisticsItem> = listOf(
        StatisticsItem(type = StatisticsType.VIEWS, 966),
        StatisticsItem(type = StatisticsType.SHARES, 7),
        StatisticsItem(type = StatisticsType.COMMENTS, 8),
        StatisticsItem(type = StatisticsType.LIKES, 27)
    )
)
