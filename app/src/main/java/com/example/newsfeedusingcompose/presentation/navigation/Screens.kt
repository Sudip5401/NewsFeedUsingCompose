package com.example.newsfeedusingcompose.presentation.navigation

import com.example.newsfeedusingcompose.presentation.navigation.RoutingPath.Route.FEED_DETAILS
import com.example.newsfeedusingcompose.presentation.navigation.RoutingPath.Route.NEWS_FEEDS

sealed class Screens(val route: String) {
    data object NewsFeeds : Screens(route = NEWS_FEEDS)
    data object Details : Screens(route = FEED_DETAILS)
}