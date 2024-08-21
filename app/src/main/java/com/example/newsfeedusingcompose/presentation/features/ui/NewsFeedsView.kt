package com.example.newsfeedusingcompose.presentation.features.ui

import android.annotation.SuppressLint
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.basicMarquee
import androidx.compose.foundation.clickable
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
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.dp
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import coil.compose.AsyncImage
import com.example.newsfeed.data.dataSource.dto.Data
import com.example.newsfeed.presentation.viewModel.NewsFeedsViewModel
import com.example.newsfeedusingcompose.R
import com.example.newsfeedusingcompose.presentation.common.BaseScaffold
import com.example.newsfeedusingcompose.presentation.common.ErrorMessage
import com.example.newsfeedusingcompose.presentation.core.theme.background
import com.example.newsfeedusingcompose.presentation.core.theme.dimGrey
import com.example.newsfeedusingcompose.presentation.features.ui.previewComponents.DataPreviewProvider
import com.example.newsfeedusingcompose.utils.forwardingPainter
import kotlinx.coroutines.ExperimentalCoroutinesApi

@OptIn(ExperimentalCoroutinesApi::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun NewsFeedsView(
    viewModel: NewsFeedsViewModel,
    navigateToNextScreen: (data: Data) -> Unit
) {

    BaseScaffold(
        screenName = "News",
        shouldShowTopBar = true
    ) {
        LoadNewsFeeds(viewModel) {
            navigateToNextScreen.invoke(it)
        }
    }
}


@ExperimentalCoroutinesApi
@Composable
private fun LoadNewsFeeds(
    viewModel: NewsFeedsViewModel,
    navigateToNextScreen: (data: Data) -> Unit
) {
    val moviePagingItems: LazyPagingItems<Data> = viewModel.newsFeeds.collectAsLazyPagingItems()
    var isLoading by remember { mutableStateOf(false) }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(background)
    ) {
        LazyColumn {
            item { Spacer(modifier = Modifier.padding(dimensionResource(id = R.dimen.dimen4))) }
            items(moviePagingItems.itemCount) { index ->
                isLoading = false
                PopulateItem(
                    data = moviePagingItems.get(index) ?: Data()
                ) {
                    navigateToNextScreen.invoke(it)
                }
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
            item {
                Spacer(modifier = Modifier.padding(4.dp))
            }
        }
        if (isLoading) {
            ProgressIndicator()
        }
    }
}

@Preview(showBackground = true)
@OptIn(ExperimentalFoundationApi::class)
@Composable
private fun PopulateItem(
    @PreviewParameter(DataPreviewProvider::class, 1) data: Data,
    onClick: (data: Data) -> Unit
) {
    Card(
        modifier = Modifier
            .padding(
                horizontal = dimensionResource(id = R.dimen.dimen16),
                vertical = dimensionResource(id = R.dimen.dimen8)
            )
            .clickable {
                onClick.invoke(data)
            },
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
                verticalAlignment = Alignment.Top
            ) {
                AsyncImage(
                    model = data.image,
                    error = forwardingPainter(
                        painter = painterResource(R.drawable.news_placeholder),
                    ),
                    fallback = forwardingPainter(
                        painter = painterResource(R.drawable.news_placeholder),
                    ),
                    placeholder = forwardingPainter(
                        painter = painterResource(R.drawable.news_placeholder),
                    ),
                    contentDescription = null,
                    modifier = Modifier.size(dimensionResource(id = R.dimen.dimen128)),
                    contentScale = ContentScale.FillBounds
                )
                Spacer(modifier = Modifier.width(dimensionResource(id = R.dimen.dimen8)))
                Text(
                    modifier = Modifier.padding(top = dimensionResource(id = R.dimen.dimen24)),
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
                    style = TextStyle(color = Color.White),
                    maxLines = 2,
                    modifier = Modifier
                        .padding(horizontal = dimensionResource(id = R.dimen.dimen4))
                        .basicMarquee()
                        .padding(vertical = dimensionResource(id = R.dimen.dimen12))

                )
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
private fun ProgressIndicator() {
    val strokeWidth = dimensionResource(id = R.dimen.dimen4)
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = dimGrey),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        CircularProgressIndicator(
            modifier = Modifier.size(dimensionResource(id = R.dimen.dimen32)),
            color = Color.White,
            strokeWidth = strokeWidth
        )
    }
}


