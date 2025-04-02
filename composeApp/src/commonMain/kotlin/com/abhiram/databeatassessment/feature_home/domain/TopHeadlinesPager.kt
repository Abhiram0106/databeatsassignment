package com.abhiram.databeatassessment.feature_home.domain

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.abhiram.databeatassessment.core.util.CountryData
import com.abhiram.databeatassessment.feature_home.domain.model.NewsItem
import com.abhiram.databeatassessment.core.util.UnknownPagingException

class TopHeadlinesPagingSource(
    private val query: String,
    private val selectedCountry: CountryData,
    private val selectedCategory: NewsCategories,
    private val homeRepository: HomeRepository,
) : PagingSource<Int, NewsItem>() {

    override fun getRefreshKey(state: PagingState<Int, NewsItem>): Int? {
        return state.anchorPosition?.let { position ->
            state.closestPageToPosition(position)?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, NewsItem> {
        val pageNumber = params.key ?: 1

        val response = homeRepository.getTopHeadlines(
            searchQuery = query,
            country = selectedCountry,
            category = selectedCategory,
            pageSize = params.loadSize,
            pageNumber = pageNumber
        )

        if (response.isFailure) {
            return LoadResult.Error(response.exceptionOrNull() ?: UnknownPagingException())
        }

        // data never null because isFailure check above but did this anyway
        val data = response.getOrNull()?.articles.orEmpty()
        val endOfPaginationReached = data.size < params.loadSize

        return LoadResult.Page(
            data = data,
            prevKey = if (pageNumber <= 1) null else pageNumber - 1,
            nextKey = if (endOfPaginationReached) null else pageNumber + 1
        )
    }
}