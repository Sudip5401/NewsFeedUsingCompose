package com.example.newsfeedusingcompose.presentation.navigation

import com.example.newsfeedusingcompose.utils.RoutingPath.Arg.DETAIL_ARG_KEY
import com.example.newsfeedusingcompose.utils.RoutingPath.Route.FEED_DETAILS
import com.example.newsfeedusingcompose.utils.RoutingPath.Route.NEWS_FEEDS

sealed class Screens(val route: String) {
    data object NewsFeeds : Screens(route = NEWS_FEEDS)
    data object Details : Screens(route = "${FEED_DETAILS}/{$DETAIL_ARG_KEY}") {
        fun passData(data: String): String {
            return this.route.replace(oldValue = "{$DETAIL_ARG_KEY}", newValue = data)
        }
    }
}