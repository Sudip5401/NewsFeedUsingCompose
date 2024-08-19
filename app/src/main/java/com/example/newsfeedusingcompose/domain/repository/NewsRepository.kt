package com.example.newsfeed.domain.repository

import androidx.paging.PagingData
import com.example.newsfeed.core.State
import com.example.newsfeed.data.dataSource.dto.Data
import com.example.newsfeed.data.dataSource.dto.Feed
import kotlinx.coroutines.flow.Flow

interface NewsRepository {
    suspend fun getAllNews(): Flow<PagingData<Data>>
}