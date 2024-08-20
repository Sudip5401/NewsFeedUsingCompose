package com.example.jetpackcomposeinitial.nestednavigation

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.newsfeed.presentation.viewModel.FeedDetailsViewModel
import com.example.newsfeed.presentation.viewModel.NewsFeedsViewModel
import com.example.newsfeedusingcompose.presentation.features.ui.FeedDetailsView
import com.example.newsfeedusingcompose.presentation.features.ui.NewsFeedsView
import com.example.newsfeedusingcompose.presentation.navigation.Screens
import com.example.newsfeedusingcompose.utils.RoutingPath.Arg.DETAIL_ARG_KEY
import com.example.newsfeedusingcompose.utils.RoutingPath.Route.FEED_DETAILS
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
            NewsFeedsView(viewModel, navigateToNextScreen = {
                navController.navigate(Screens.Details.passData(Gson().toJson(it)))
            })
        }

        composable(
            route = Screens.Details.route, arguments = listOf(
                navArgument(
                    name = DETAIL_ARG_KEY
                ) {
                    type = NavType.StringType
                    defaultValue = ""
                },
            )
        ) {
//            val viewModel: FeedDetailsViewModel = viewModel()
            FeedDetailsView() {

            }
        }
    }
}