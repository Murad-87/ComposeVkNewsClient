package com.muslim.simplevknewsclient.domain

data class FeedPost(
    val id: String,
    val communityName: String,
    val publicationDate: String,
    val communityImageUrl: String,
    val contentText: String,
    val contentImageUrl: String?,
    val statistics: List<StatisticsItem>,
    val isFavourite: Boolean,
)
