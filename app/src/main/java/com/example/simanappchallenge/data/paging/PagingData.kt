package com.example.simanappchallenge.data.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.simanappchallenge.data.network.MarvelApi
import com.example.simanappchallenge.domain.mapper.toDomain
import com.example.simanappchallenge.domain.models.MarvelCharacter

class MarvelPagingSource(
    private val api: MarvelApi
): PagingSource<Int, MarvelCharacter>() {
    override fun getRefreshKey(state: PagingState<Int, MarvelCharacter>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, MarvelCharacter> {
        val page = params.key ?: 10
        return try {
            val response = api.getCharacters(
                offset = page,
                limit = 10
            )
            val data = response.body()?.data?.results?.map { it.toDomain() }
            val total = response.body()?.data?.total
            val current = response.body()?.data?.offset
            LoadResult.Page(
                data = data ?: emptyList(),
                prevKey = if (current!! >= total!!) null else current - 10,
                nextKey = if (data?.isEmpty() == true) null else current + 10
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }

}