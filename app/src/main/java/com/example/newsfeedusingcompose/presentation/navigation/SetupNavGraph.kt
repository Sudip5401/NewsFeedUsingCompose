package com.example.jetpackcomposeinitial.nestednavigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.newsfeed.presentation.viewModel.NewsFeedsViewModel
import com.example.newsfeedusingcompose.presentation.features.ui.FeedDetailsView
import com.example.newsfeedusingcompose.presentation.features.ui.NewsFeedsView
import com.example.newsfeedusingcompose.presentation.features.viewModel.FeedDetailsViewModel
import com.example.newsfeedusingcompose.presentation.navigation.RoutingPath.Arg.DETAIL_ARG_KEY
import com.example.newsfeedusingcompose.presentation.navigation.Screens
import com.example.newsfeedusingcompose.utils.Utils
import com.google.gson.Gson

@Composable
fun SetupNavGraph(
    navController: NavHostController,
) {
    NavHost(
        navController = navController,
        startDestination = Screens.NewsFeeds.route,
    ) {
        composable(route = Screens.NewsFeeds.route) {
            val viewModel: NewsFeedsViewModel = hiltViewModel()
            val feedsLazyData = viewModel.newsFeeds
            NewsFeedsView(feedsLazyData, navigateToNextScreen = {
                navController.navigate(
                    Screens.Details.route + "?data=${
                        Gson().toJson(it.apply {
                            /*Applied due to com.google.gson.JsonSyntaxException: com.google.gson.stream.MalformedJsonException*/
                            title = Utils.removeSpecialCharacters(title ?: "")
                            description = Utils.removeSpecialCharacters(description ?: "")
                        })
                    }"
                )
            })
        }

        composable(
            route = Screens.Details.route + "?data={$DETAIL_ARG_KEY}", arguments = listOf(
                navArgument(
                    name = DETAIL_ARG_KEY
                ) {
                    type = NavType.StringType
                    defaultValue = ""
                },
            )
        ) {
            val viewModel = hiltViewModel<FeedDetailsViewModel>()
            val currentFeed by viewModel.feedDetails.collectAsState()
            currentFeed?.let {
                FeedDetailsView(it) {
                    navController.popBackStack()
                }
            }
        }
    }
}