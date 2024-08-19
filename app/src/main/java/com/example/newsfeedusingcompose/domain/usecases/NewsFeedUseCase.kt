package com.example.newsfeed.domain.usecases

import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.newsfeed.data.dataSource.dto.Data
import com.example.newsfeed.domain.repository.NewsRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class NewsFeedUseCase @Inject constructor(private val repository: NewsRepository) {
    suspend operator fun invoke(): Flow<PagingData<Data>> = repository.getAllNews()
}