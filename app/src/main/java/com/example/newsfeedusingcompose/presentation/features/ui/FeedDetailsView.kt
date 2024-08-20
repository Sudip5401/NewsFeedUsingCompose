package com.example.newsfeedusingcompose.presentation.features.ui

import android.annotation.SuppressLint
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.newsfeed.data.dataSource.dto.Data
import com.example.newsfeed.presentation.viewModel.FeedDetailsViewModel
import com.example.newsfeedusingcompose.presentation.common.BaseScaffold
import kotlinx.coroutines.ExperimentalCoroutinesApi

@OptIn(ExperimentalCoroutinesApi::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun FeedDetailsView(
    navigateBack: () -> Unit
) {
    BaseScaffold(
        screenName = "Details",
        shouldShowTopBar = true,
    ) {
        /*LoadNewsFeeds(viewModel) {
            navigateToNextScreen.invoke(it)
        }*/
    }
}

/*
@Composable
fun getFactoryViewModel(
    data: Data,
): FeedDetailsViewModel {
    val viewModel = hiltViewModel<FeedDetailsViewModel, FeedDetailsViewModel.DetailViewModelFactory> { factory ->
        factory.create(data)
    }
    return viewModel
}
*/

