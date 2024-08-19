package com.example.newsfeedusingcompose.presentation.features.ui

import android.annotation.SuppressLint
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.basicMarquee
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.AbsoluteAlignment
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import coil.compose.rememberAsyncImagePainter
import com.example.newsfeed.data.dataSource.dto.Data
import com.example.newsfeed.presentation.viewModel.NewsFeedsViewModel
import com.example.newsfeedusingcompose.R
import com.example.newsfeedusingcompose.presentation.common.BaseScaffold
import com.example.newsfeedusingcompose.presentation.common.ErrorMessage
import com.example.newsfeedusingcompose.presentation.core.theme.background
import com.example.newsfeedusingcompose.presentation.core.theme.dimGrey
import kotlinx.coroutines.ExperimentalCoroutinesApi

@OptIn(ExperimentalCoroutinesApi::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun NewsFeedsView(
    viewModel: NewsFeedsViewModel,
    navigateToNextScreen: () -> Unit
) {
    BaseScaffold(
        screenName = "News",
        shouldShowTopBar = true,
    ) {
        LoadNewsFeeds(viewModel)
    }
}

@ExperimentalCoroutinesApi
@Composable
fun LoadNewsFeeds(viewModel: NewsFeedsViewModel) {
    val moviePagingItems: LazyPagingItems<Data> = viewModel.newsFeeds.collectAsLazyPagingItems()
    var isLoading by remember { mutableStateOf(false) }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(background)
    ) {
        LazyColumn {
            item { Spacer(modifier = Modifier.padding(4.dp)) }
            items(moviePagingItems.itemCount) { index ->
                PopulateItem(
                    data = moviePagingItems.get(index) ?: Data()
                )
            }
            moviePagingItems.apply {
                when {
                    loadState.refresh is LoadState.Loading -> {
                        item {
                            isLoading = true
                        }
                    }

                    loadState.refresh is LoadState.Error -> {
                        isLoading = false
                        val error = moviePagingItems.loadState.refresh as LoadState.Error
                        item {
                            ErrorMessage(
                                modifier = Modifier.fillParentMaxSize(),
                                message = stringResource(id = R.string.something_went_wrong),
                                onClickRetry = { retry() })
                        }
                    }

                    loadState.append is LoadState.Loading -> {
                        item {
                            isLoading = true
                        }
                    }

                    loadState.append is LoadState.Error -> {
                        isLoading = false
                        val error = moviePagingItems.loadState.append as LoadState.Error
                        item {
                            ErrorMessage(
                                modifier = Modifier,
                                message = error.error.localizedMessage!!,
                                onClickRetry = { retry() })
                        }
                    }
                }
            }
            item { Spacer(modifier = Modifier.padding(4.dp)) }
        }
        if (isLoading) {
            ProgressIndicator()
        }
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun PopulateItem(data: Data) {
    Card(
        modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp),
        shape = MaterialTheme.shapes.medium,
        colors = CardDefaults.cardColors(
            containerColor = Color.White,
            contentColor = Color.Black
        ),
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Image(
                    painter = rememberAsyncImagePainter(data.image),
                    contentDescription = null,
                    modifier = Modifier.size(128.dp),
                    contentScale = ContentScale.Fit
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text(
                    text = data.description ?: "",
                    style = TextStyle(color = Color.Black),
                    maxLines = 2
                )
            }

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(dimGrey, RectangleShape)
                    .align(AbsoluteAlignment.BottomLeft)
            ) {
                Text(
                    text = data.title ?: "",
                    style = TextStyle(color = Color.Black),
                    maxLines = 2,
                    modifier = Modifier
                        .padding(horizontal = 4.dp)
                        .basicMarquee()
                        .padding(vertical = 12.dp)

                )
            }
        }
    }
}

@Composable
private fun ProgressIndicator() {
    val strokeWidth = 5.dp
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = dimGrey),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        CircularProgressIndicator(
            modifier = Modifier.size(32.dp),
            color = Color.LightGray,
            strokeWidth = strokeWidth
        )
    }
}


