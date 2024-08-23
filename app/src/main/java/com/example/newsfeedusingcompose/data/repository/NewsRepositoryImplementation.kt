package com.example.newsfeed.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.newsfeed.data.dataSource.ApiService
import com.example.newsfeed.data.dataSource.dto.Data
import com.example.newsfeed.domain.repository.NewsRepository
import com.example.newsfeedusingcompose.data.dataSource.NewsPagingSource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class NewsRepositoryImplementation @Inject constructor(private val api: ApiService) :
    NewsRepository {
    override suspend fun getAllNews(): Flow<PagingData<Data>> =
        Pager(
            config = PagingConfig(
                pageSize = MAX_PAGE_SIZE, prefetchDistance = 10
            ),
            pagingSourceFactory = {
                NewsPagingSource(api)
            }
        ).flow

    companion object {
        const val MAX_PAGE_SIZE = 20
    }
}
