package com.example.newsfeedusingcompose.presentation.features.ui

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import coil.compose.AsyncImage
import com.example.newsfeed.data.dataSource.dto.Data
import com.example.newsfeedusingcompose.R
import com.example.newsfeedusingcompose.presentation.common.BackPressHandler
import com.example.newsfeedusingcompose.presentation.common.BaseScaffold
import com.example.newsfeedusingcompose.presentation.core.theme.background
import com.example.newsfeedusingcompose.presentation.features.ui.previewComponents.DataPreviewProvider
import com.example.newsfeedusingcompose.presentation.navigation.RoutingPath.TestTags.DETAILS_AUTHOR_TEXT_VIEW_TAG
import com.example.newsfeedusingcompose.presentation.navigation.RoutingPath.TestTags.DETAILS_COUNTRY_TEXT_VIEW_TAG
import com.example.newsfeedusingcompose.utils.forwardingPainter

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun FeedDetailsView(
    currentFeed: Data,
    navigateBack: () -> Unit
) {
    val onBack = {
        navigateBack()
    }
    BackPressHandler(onBackPressed = onBack)
    BaseScaffold(
        screenName = "Details",
        shouldShowTopBar = true,
        showNavigationBack = true,
        setNavigationIcon = Icons.AutoMirrored.Filled.ArrowBack,
        onNavActionButtonClick = navigateBack
    ) {
        ShowDetails(currentFeed)
    }
}


@Preview(showBackground = true)
@Composable
fun ShowDetails(@PreviewParameter(DataPreviewProvider::class) currentFeed: Data) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = background)
            .padding(dimensionResource(id = R.dimen.dimen16))
    ) {
        AsyncImage(
            model = currentFeed.image,
            error = forwardingPainter(
                painter = painterResource(R.drawable.news_placeholder),
                alpha = 0.5f
            ),
            fallback = forwardingPainter(
                painter = painterResource(R.drawable.news_placeholder),
                alpha = 0.5f
            ),
            placeholder = forwardingPainter(
                painter = painterResource(R.drawable.news_placeholder),
                alpha = 0.5f
            ),
            contentDescription = null,
            modifier = Modifier
                .fillMaxWidth()
                .height(dimensionResource(id = R.dimen.dimen250))
                .clip(
                    RoundedCornerShape(dimensionResource(id = R.dimen.dimen10))
                ),
            contentScale = ContentScale.FillBounds
        )
        Spacer(modifier = Modifier.height(dimensionResource(id = R.dimen.dimen16)))
        Text(text = currentFeed.title ?: "", style = TextStyle(color = Color.Black))
        Spacer(modifier = Modifier.height(dimensionResource(id = R.dimen.dimen8)))
        Text(text = currentFeed.description ?: "", style = TextStyle(color = Color.Black))
        Spacer(modifier = Modifier.height(dimensionResource(id = R.dimen.dimen8)))
        Text(
            modifier = Modifier.testTag(DETAILS_AUTHOR_TEXT_VIEW_TAG),
            text = if (currentFeed.author?.isBlank()
                    ?.not() == true
            ) "Author: ${currentFeed.author ?: ""}" else "",
            style = TextStyle(color = Color.Black)
        )
        Spacer(modifier = Modifier.height(dimensionResource(id = R.dimen.dimen4)))
        Text(
            modifier = Modifier.testTag(DETAILS_COUNTRY_TEXT_VIEW_TAG),
            text = if (currentFeed.author?.isBlank()
                    ?.not() == true
            ) "Country: ${currentFeed.country ?: ""}" else "",
            style = TextStyle(color = Color.Black)
        )
    }
}

