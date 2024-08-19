package com.example.newsfeed.domain.repository

import androidx.paging.PagingData
import com.example.newsfeed.data.dataSource.dto.Data
import kotlinx.coroutines.flow.Flow

interface NewsRepository {
    suspend fun getAllNews(): Flow<PagingData<Data>>
}