package com.example.newsfeedusingcompose.presentation.features.viewModel

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.newsfeed.data.dataSource.dto.Data
import com.example.newsfeedusingcompose.presentation.navigation.RoutingPath.Arg.DETAIL_ARG_KEY
import com.google.gson.Gson
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
open class FeedDetailsViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val _feedDetails = MutableStateFlow<Data?>(value = null)
    val feedDetails: MutableStateFlow<Data?> get() = _feedDetails

    init {
        viewModelScope.launch {
            val data = savedStateHandle.get<String>(DETAIL_ARG_KEY) ?: ""
            val feed = Gson().fromJson(data, Data::class.java)
            _feedDetails.value = feed
        }
    }
}