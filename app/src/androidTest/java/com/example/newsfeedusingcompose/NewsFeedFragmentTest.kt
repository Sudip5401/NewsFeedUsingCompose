package com.example.newsfeedusingcompose

import androidx.compose.ui.test.assert
import androidx.compose.ui.test.assertCountEquals
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.assertTextEquals
import androidx.compose.ui.test.hasText
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onChildren
import androidx.compose.ui.test.onFirst
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.paging.PagingData
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.newsfeed.data.dataSource.dto.Data
import com.example.newsfeedusingcompose.presentation.core.theme.NewsFeedUsingComposeTheme
import com.example.newsfeedusingcompose.presentation.features.ui.FeedDetailsView
import com.example.newsfeedusingcompose.presentation.features.ui.NewsFeedsView
import com.example.newsfeedusingcompose.presentation.navigation.RoutingPath.TestTags.DETAILS_COUNTRY_TEXT_VIEW_TAG
import com.example.newsfeedusingcompose.presentation.navigation.RoutingPath.TestTags.FEEDS_LIST_VIEW_TAG
import com.example.newsfeedusingcompose.utils.TestUtil.dummyData
import kotlinx.coroutines.flow.MutableStateFlow
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith


@RunWith(AndroidJUnit4::class)
class NewsFeedFragmentTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Before
    fun setUp() {
    }

    @Test
    fun given_title_should_be_displayed_in_news_list_screen() {/*Load composable*/
        composeTestRule.setContent {
            NewsFeedUsingComposeTheme {
                NewsFeedsView(state = MutableStateFlow(value = PagingData.empty())) {}
            }
        }
        /*assertIsExists - Assertion*/
        composeTestRule.onNodeWithText("News").assertIsDisplayed()
    }

    @Test
    fun given_title_should_be_displayed_in_feed_details_screen() {/*Load composable*/
        composeTestRule.setContent {
            NewsFeedUsingComposeTheme {
                FeedDetailsView(Data()) {}
            }
        }
        /*assertIsExists - Assertion*/
        composeTestRule.onNodeWithText("Details").assertIsDisplayed()
        composeTestRule.onNodeWithText("Details").assertExists("Francis Sardauna")
        composeTestRule.onNodeWithTag(DETAILS_COUNTRY_TEXT_VIEW_TAG).assertExists()
    }

    @Test
    fun check_author_is_blank_if_data_is_empty() {/*Load composable*/
        composeTestRule.setContent {
            NewsFeedUsingComposeTheme {
                FeedDetailsView(Data()) {}
            }
        }
        /*assertTextEquals - Assertion*/
        composeTestRule.onNodeWithTag(DETAILS_COUNTRY_TEXT_VIEW_TAG).assertTextEquals("")
    }

    @Test
    fun check_feeds_list_if_feeds_are_empty() {/*Load composable*/
        composeTestRule.setContent {
            NewsFeedUsingComposeTheme {
                NewsFeedsView(MutableStateFlow(PagingData.empty())) {}
            }
        }
        /*assertTextEquals - Assertion*/
        composeTestRule.onNodeWithTag(FEEDS_LIST_VIEW_TAG, useUnmergedTree = true).onChildren()
            .assertCountEquals(0)
    }

    @Test
    fun check_feeds_list_if_feeds_are_not_empty() {
        val list = mutableListOf(dummyData)/*Load composable*/
        composeTestRule.setContent {
            NewsFeedUsingComposeTheme {
                NewsFeedsView(MutableStateFlow(PagingData.from(data = list))) {}
            }
        }
        /*assertTextEquals - Assertion*/
        composeTestRule.onNodeWithTag(FEEDS_LIST_VIEW_TAG, useUnmergedTree = true).onChildren()
            .assertCountEquals(1)
    }

    @Test
    fun check_feeds_list_first_feed_title() {
        val list = mutableListOf(dummyData)/*Load composable*/
        composeTestRule.setContent {
            NewsFeedUsingComposeTheme {
                NewsFeedsView(MutableStateFlow(PagingData.from(data = list))) {}
            }
        }
        /*assertTextEquals - Assertion*/
        composeTestRule.onNodeWithTag(FEEDS_LIST_VIEW_TAG, useUnmergedTree = false).onChildren()
            .onFirst()
            .assert(hasText(list.first().title ?: ""))
    }
}



