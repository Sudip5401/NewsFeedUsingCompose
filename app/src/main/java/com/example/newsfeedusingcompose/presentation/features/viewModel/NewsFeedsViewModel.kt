package com.example.newsfeed.presentation.viewModel

import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.newsfeed.core.Dispatcher
import com.example.newsfeed.data.dataSource.dto.Data
import com.example.newsfeed.domain.usecases.NewsFeedUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.distinctUntilChanged
import javax.inject.Inject

@HiltViewModel
class NewsFeedsViewModel @Inject constructor(
    private val newsFeedsUseCase: NewsFeedUseCase, dispatcher: Dispatcher
) : BaseViewModel(dispatcher) {

//    internal var offsetValue = 0
    private val _newsFeeds = MutableStateFlow<PagingData<Data>>(value = PagingData.empty())
    val newsFeeds: MutableStateFlow<PagingData<Data>> get() = _newsFeeds

    internal var feedList = mutableListOf<Data>()

    init {
        fetchFeeds()
    }

    private fun fetchFeeds() =
        launchOnIO {
            newsFeedsUseCase.invoke().distinctUntilChanged()
                .cachedIn(viewModelScope).collect {
                    _newsFeeds.value = it
                }
        }
}