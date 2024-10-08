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

    private val _newsFeeds = MutableStateFlow<PagingData<Data>>(value = PagingData.empty())
    val newsFeeds: MutableStateFlow<PagingData<Data>> get() = _newsFeeds

    init {
        fetchFeeds()
    }

    internal fun fetchFeeds() =
        launchOnIO {
            newsFeedsUseCase().distinctUntilChanged()
                .cachedIn(viewModelScope).collect {
                    _newsFeeds.value = it
                }
        }
}