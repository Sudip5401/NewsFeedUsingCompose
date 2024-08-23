package com.example.newsfeedusingcompose.repository

import androidx.paging.PagingSource
import com.example.newsfeed.data.dataSource.ApiService
import com.example.newsfeed.data.dataSource.dto.Data
import com.example.newsfeedusingcompose.data.dataSource.NewsPagingSource
import com.example.newsfeedusingcompose.utils.TestUtil
import com.example.newsfeedusingcompose.utils.TestUtil.feedResponse
import com.example.newsfeedusingcompose.utils.TestUtil.modifiedResponse
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner
import org.mockito.kotlin.any
import org.mockito.kotlin.given


@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class UseCaseUnitTest {

    @Mock
    private lateinit var apiService: ApiService
    private lateinit var newsPagingSource: NewsPagingSource

    @Before
    fun setUp() {
        newsPagingSource = NewsPagingSource(apiService)
    }

    @Test
    fun `news paging source load - failure - http error`() = runBlocking {
        val error = RuntimeException("404", Throwable())
        given(apiService.getAllNews(any(), any())).willThrow(error)
        val expectedResult = PagingSource.LoadResult.Error<Int, Data>(error)
        assertEquals(
            expectedResult.throwable, error
        )
    }

    @Test
    fun `news paging source load - failure - received null`() = runBlocking {
        given(apiService.getAllNews(any(), any())).willReturn(null)
        val expectedResult = PagingSource.LoadResult.Error<Int, Data>(NullPointerException())
        assertEquals(
            expectedResult.throwable.message, NullPointerException().message
        )
    }

    @Test
    fun `news paging source prepend - success`() = runBlocking {
        given(apiService.getAllNews(any(), any())).willReturn(feedResponse)
        val expectedResult = PagingSource.LoadResult.Page(
            data = TestUtil.dummyFeedResponse.data ?: mutableListOf(),
            prevKey = -1,
            nextKey = 20
        )
        assertEquals(
            expectedResult, newsPagingSource.load(
                PagingSource.LoadParams.Prepend(
                    key = 0,
                    loadSize = 1,
                    placeholdersEnabled = false
                )
            )
        )
    }

    @Test
    fun `news paging source refresh - success`() = runBlocking {
        given(apiService.getAllNews(any(), any())).willReturn(modifiedResponse)
        val expectedResult = PagingSource.LoadResult.Page(
            data = feedResponse.data?.map { data -> Data(author = data.author) } ?: mutableListOf(),
            prevKey = null,
            nextKey = 21
        )
        assertEquals(
            expectedResult, newsPagingSource.load(
                PagingSource.LoadParams.Refresh(
                    key = 1,
                    loadSize = 1,
                    placeholdersEnabled = false
                )
            )
        )
    }
}