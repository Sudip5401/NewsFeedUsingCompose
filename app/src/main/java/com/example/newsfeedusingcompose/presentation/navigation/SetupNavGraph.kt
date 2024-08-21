package com.example.jetpackcomposeinitial.nestednavigation

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.newsfeed.data.dataSource.dto.Data
import com.example.newsfeed.presentation.viewModel.NewsFeedsViewModel
import com.example.newsfeedusingcompose.presentation.features.ui.FeedDetailsView
import com.example.newsfeedusingcompose.presentation.features.ui.NewsFeedsView
import com.example.newsfeedusingcompose.presentation.navigation.Screens
import com.example.newsfeedusingcompose.presentation.navigation.RoutingPath.Arg.DETAIL_ARG_KEY
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
            NewsFeedsView(viewModel, navigateToNextScreen = {
                navController.navigate(
                    Screens.Details.route + "?data=${
                        Gson().toJson(it.apply {
                            /*Applied due to com.google.gson.JsonSyntaxException: com.google.gson.stream.MalformedJsonException*/
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
            val jsonString = it.arguments?.getString("data")
            val currentFeed = Gson().fromJson(jsonString, Data::class.java)
            FeedDetailsView(currentFeed) {
                navController.popBackStack()
            }
        }
    }
}