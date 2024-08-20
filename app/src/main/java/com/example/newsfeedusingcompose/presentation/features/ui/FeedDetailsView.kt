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
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.core.content.res.ResourcesCompat
import coil.compose.AsyncImage
import com.example.newsfeed.data.dataSource.dto.Data
import com.example.newsfeedusingcompose.R
import com.example.newsfeedusingcompose.presentation.common.BaseScaffold
import com.example.newsfeedusingcompose.presentation.core.theme.background
import com.example.newsfeedusingcompose.presentation.common.BackPressHandler
import com.example.newsfeedusingcompose.utils.forwardingPainter
import com.example.newsfeedusingcompose.utils.getPlaceholder

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

@Composable
fun ShowDetails(currentFeed: Data) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = background)
            .padding(16.dp)
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
                .height(250.dp)
                .clip(
                    RoundedCornerShape(10.dp)
                ),
            contentScale = ContentScale.FillBounds
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(text = currentFeed.title ?: "", style = TextStyle(color = Color.Black))
        Spacer(modifier = Modifier.height(8.dp))
        Text(text = currentFeed.description ?: "", style = TextStyle(color = Color.Black))
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = if (currentFeed.author?.isBlank()
                    ?.not() == true
            ) "Author: ${currentFeed.author ?: ""}" else "",
            style = TextStyle(color = Color.Black)
        )
        Spacer(modifier = Modifier.height(4.dp))
        Text(
            text = if (currentFeed.author?.isBlank()
                    ?.not() == true
            ) "Country: ${currentFeed.country ?: ""}" else "",
            style = TextStyle(color = Color.Black)
        )
    }
}

