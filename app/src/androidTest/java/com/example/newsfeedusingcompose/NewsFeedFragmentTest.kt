package com.example.newsfeedusingcompose

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.assertTextEquals
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.newsfeed.data.dataSource.dto.Data
import com.example.newsfeed.utils.Constants.Companion.DETAILS_COUNTRY_TEXT_VIEW_TAG
import com.example.newsfeedusingcompose.presentation.core.theme.NewsFeedUsingComposeTheme
import com.example.newsfeedusingcompose.presentation.features.ui.FeedDetailsView
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
    fun given_title_should_be_displayed_in_Feed_Details_Screen() {
        /*Load composable*/
        composeTestRule.setContent {
            NewsFeedUsingComposeTheme {
                FeedDetailsView(Data()) {

                }
            }
        }
        /*assertIsDisplayed - Assertion*/
        composeTestRule
            .onNodeWithText("Details")
            .assertIsDisplayed()

        composeTestRule
            .onNodeWithText("Details")
            .assertExists("Francis Sardauna")

        composeTestRule.onNodeWithTag(DETAILS_COUNTRY_TEXT_VIEW_TAG).assertExists()
    }

    @Test
    fun check_Author_Is_Blank_If_Data_Is_empty() {
        /*Load composable*/
        composeTestRule.setContent {
            NewsFeedUsingComposeTheme {
                FeedDetailsView(Data()) {

                }
            }
        }
        /*assertIsDisplayed - Assertion*/
        composeTestRule.onNodeWithTag(DETAILS_COUNTRY_TEXT_VIEW_TAG).assertTextEquals("")
    }
}



