package com.example.newsfeedusingcompose.utils

import com.example.newsfeed.data.dataSource.dto.Data
import com.example.newsfeed.data.dataSource.dto.Feed
import com.example.newsfeed.data.dataSource.dto.Pagination

object TestUtil {
    internal val dummyData = Data(
        "Francis Sardauna",
        title = "NGO Trains CSOs, Journalists on Digital Security in Katsina",
        description = "Francis Sardauna in Katsina A non-governmental organisation, Grassroots Researchers Association (GRA), has trained civil society organisations and journalists in Katsina State on digital security and resilience as part of its",
        country = "us"
    )
    val dummyFeedResponse =
        Feed(mutableListOf(dummyData), Pagination(count = 10, limit = 50, offset = 10, total = 100))

    private val dummyFeedResponseNext =
        Feed(mutableListOf(dummyData), Pagination(count = 10, limit = 50, offset = 10, total = 100))

    val feedResponse = Feed(
        data = dummyFeedResponseNext.data,
        pagination = Pagination(limit = 1, offset = 0, count = 0, total = 100)
    )

    val modifiedResponse = Feed(
        data = dummyFeedResponseNext.data?.map { data -> Data(author = data.author) }
            ?.toMutableList(),
        pagination = Pagination(limit = 1, offset = 1, count = 0, total = 100)
    )
}