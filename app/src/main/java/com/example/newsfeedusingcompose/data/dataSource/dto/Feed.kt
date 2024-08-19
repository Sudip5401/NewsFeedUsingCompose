package com.example.newsfeed.data.dataSource.dto

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class Feed(
    @SerializedName("data") val data: MutableList<Data>? = null,
    @SerializedName("pagination") val pagination: Pagination? = null
) : Parcelable