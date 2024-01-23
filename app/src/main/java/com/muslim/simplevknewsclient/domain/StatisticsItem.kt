package com.muslim.simplevknewsclient.domain

data class StatisticsItem(
    val type: StatisticsType,
    val count: Int = 0
)

enum class StatisticsType {
    VIEWS, COMMENTS, SHARES, LIKES
}
