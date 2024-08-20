package com.example.newsfeed.presentation.viewModel

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.newsfeed.core.Dispatcher
import com.example.newsfeed.data.dataSource.dto.Data
import com.google.gson.Gson
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

//@HiltViewModel(assistedFactory = FeedDetailsViewModel.DetailViewModelFactory::class)
@HiltViewModel
class FeedDetailsViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    /*@AssistedFactory
    interface DetailViewModelFactory {
        fun create(details: Data): FeedDetailsViewModel
    }*/

    private val _feedDetails = MutableStateFlow<Data?>(value = null)
    val feedDetails: MutableStateFlow<Data?> get() = _feedDetails

    init {
        viewModelScope.launch {
            val data = savedStateHandle.get<String>("newsData") ?: ""
            val feed = Gson().fromJson(data, Data::class.java)
        }
    }

    /*private fun fetchFeeds() =
        launchOnIO {
            newsFeedsUseCase.invoke().distinctUntilChanged()
                .cachedIn(viewModelScope).collect {
                    _newsFeeds.value = it
                }
        }*/

    /* companion object {
         fun provideDetailViewModelFactory(
             assistedFactory: DetailViewModelFactory,
             details: Data
         ): ViewModelProvider.Factory = object : ViewModelProvider.Factory {
             override fun <T : ViewModel> create(modelClass: Class<T>): T {
                 return assistedFactory.create(details) as T
             }
         }
     }*/
}