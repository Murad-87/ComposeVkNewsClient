package com.muslim.simplevknewsclient

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.muslim.simplevknewsclient.domain.FeedPost
import com.muslim.simplevknewsclient.domain.StatisticsItem
import java.lang.IllegalStateException

class MainViewModel: ViewModel() {

    private val _feedPost = MutableLiveData(FeedPost())
    val feedPost: LiveData<FeedPost> = _feedPost

    fun updateCount(item: StatisticsItem) {
        val oldStatistics = feedPost.value?.statistics ?: throw IllegalStateException()
        val newStatistics = oldStatistics.toMutableList().apply {
            replaceAll { oldItem ->
                if (oldItem.type == item.type) {
                    oldItem.copy(count = oldItem.count + 1)
                } else {
                    oldItem
                }
            }
        }
        _feedPost.value = feedPost.value?.copy(
            statistics = newStatistics
        )
    }
}