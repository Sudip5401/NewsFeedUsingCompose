package com.example.newsfeedusingcompose.presentation.common

import androidx.compose.ui.tooling.preview.PreviewParameterProvider

class ViewProvider: PreviewParameterProvider<String> {
    override val values = sequenceOf("NewsFeeds")
}