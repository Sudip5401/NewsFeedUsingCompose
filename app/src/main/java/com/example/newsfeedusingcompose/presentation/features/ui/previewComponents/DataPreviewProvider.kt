package com.example.newsfeedusingcompose.presentation.features.ui.previewComponents

import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import com.example.newsfeed.data.dataSource.dto.Data

class DataPreviewProvider : PreviewParameterProvider<Data> {
    override val values = sequenceOf(
        Data(
            "Francis Sardauna",
            title = "NGO Trains CSOs, Journalists on Digital Security in Katsina",
            description = "Francis Sardauna in Katsina A non-governmental organisation, Grassroots Researchers Association (GRA), has trained civil society organisations and journalists in Katsina State on digital security and resilience as part of its",
            country = "us"
        ),
    )
}