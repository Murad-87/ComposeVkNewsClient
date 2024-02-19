package com.muslim.simplevknewsclient.domain.entity

data class FeedPost(
    val id: Long,
    val communityId: Long,
    val communityName: String,
    val publicationDate: String,
    val communityImageUrl: String,
    val contentText: String,
    val contentImageUrl: String?,
    val statistics: List<StatisticsItem>,
    val isLiked: Boolean,
)