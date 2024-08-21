package com.example.newsfeed.viewModel

import androidx.paging.PagingData
import com.example.newsfeed.domain.usecases.NewsFeedUseCase
import com.example.newsfeed.presentation.viewModel.NewsFeedsViewModel
import com.example.newsfeed.utils.TestingDispatcher
import com.example.newsfeedusingcompose.utils.UtilTests
import io.mockk.MockKAnnotations
import io.mockk.clearAllMocks
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Before
import org.junit.Test

class NewsFeedsViewModelUnitTest {

    @MockK
    private lateinit var newsFeedUseCase: NewsFeedUseCase
    private lateinit var newsFeedsViewModel: NewsFeedsViewModel

    @Before
    fun setup() {
        MockKAnnotations.init(this)
        val testDispatcher = TestingDispatcher()
        newsFeedsViewModel = NewsFeedsViewModel(newsFeedUseCase, testDispatcher)
    }

    @Test
    fun `when use case returns success then state should not be blank`() {
        runBlocking {
            coEvery { newsFeedUseCase() } returns flow {
                emit(PagingData.from(data = UtilTests.dummyFeedResponse.data ?: mutableListOf()))
            }

            newsFeedsViewModel.fetchFeeds()
            coVerify(exactly = 1) { newsFeedUseCase() }

            assert(newsFeedsViewModel.newsFeeds.value != null)
        }
    }

    @After
    fun tearDown() {
        clearAllMocks()
    }
}