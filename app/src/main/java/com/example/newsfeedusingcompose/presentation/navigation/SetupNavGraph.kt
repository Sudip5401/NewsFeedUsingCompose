package com.example.jetpackcomposeinitial.nestednavigation

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.newsfeed.presentation.viewModel.NewsFeedsViewModel
import com.example.newsfeedusingcompose.presentation.features.ui.NewsFeedsView
import com.example.newsfeedusingcompose.utils.RoutingPath.Route.FEED_DETAILS
import com.example.newsfeedusingcompose.utils.RoutingPath.Route.NEWS_FEEDS

@Composable
fun SetupNavGraph(
    navController: NavHostController,
) {
    NavHost(
        navController = navController,
        startDestination = NEWS_FEEDS,
    ) {
        composable(route = NEWS_FEEDS) {
            val viewModel: NewsFeedsViewModel = hiltViewModel()
            NewsFeedsView(viewModel, navigateToNextScreen = {
                navController.navigate(FEED_DETAILS)
            })
        }
    }
}