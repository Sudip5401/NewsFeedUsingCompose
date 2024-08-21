package com.example.newsfeedusingcompose.utils

import com.example.newsfeed.data.dataSource.dto.Data
import com.example.newsfeed.data.dataSource.dto.Feed
import com.example.newsfeed.data.dataSource.dto.Pagination

object UtilTests {
    private val dummyData = Data(
        "Francis Sardauna",
        title = "NGO Trains CSOs, Journalists on Digital Security in Katsina\\u00a0",
        description = "Francis Sardauna in Katsina A non-governmental organisation, Grassroots Researchers Association (GRA), has trained civil society organisations and journalists in Katsina State on digital security and resilience as part of its",
        country = "us"
    )
    val dummyFeedResponse =
        Feed(mutableListOf(dummyData), Pagination(count = 10, limit = 50, offset = 10, total = 100))

    val dummyFeedResponseNext =
        Feed(mutableListOf(dummyData), Pagination(count = 10, limit = 50, offset = 10, total = 100))
}