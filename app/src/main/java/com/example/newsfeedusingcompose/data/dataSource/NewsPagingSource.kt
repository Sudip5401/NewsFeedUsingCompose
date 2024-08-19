package com.example.newsfeedusingcompose.data.dataSource

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.newsfeed.data.dataSource.ApiService
import com.example.newsfeed.data.dataSource.dto.Data
import retrofit2.HttpException
import java.io.IOException

class NewsPagingSource(
    private val newsApiService: ApiService,
) : PagingSource<Int, Data>() {
    override fun getRefreshKey(state: PagingState<Int, Data>): Int? {
        return state.anchorPosition
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Data> {
        return try {
            val page = params.key ?: 1
            val response = newsApiService.getAllNews(offset = page.toString())

            LoadResult.Page(
                data = response.data ?: listOf(),
                prevKey = if (page == 1) null else page.minus(1),
                nextKey = if (response.data?.isEmpty() == true) null else page.plus(20),
            )
        } catch (exception: IOException) {
            return LoadResult.Error(exception)
        } catch (exception: HttpException) {
            return LoadResult.Error(exception)
        }
    }
}