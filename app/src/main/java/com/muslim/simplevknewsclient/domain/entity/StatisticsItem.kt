package com.muslim.simplevknewsclient.domain.entity

data class StatisticsItem(
    val type: StatisticsType,
    val count: Int = 0
)

enum class StatisticsType {
    VIEWS, COMMENTS, SHARES, LIKES
}
