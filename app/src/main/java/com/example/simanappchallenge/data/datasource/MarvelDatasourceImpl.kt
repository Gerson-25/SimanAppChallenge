package com.example.simanappchallenge.data.datasource

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.simanappchallenge.data.model.CharacterData
import com.example.simanappchallenge.data.network.MarvelApi
import com.example.simanappchallenge.data.paging.MarvelPagingSource
import com.example.simanappchallenge.data.room.dao.MarvelDao
import com.example.simanappchallenge.domain.datasource.MarvelDataSource
import com.example.simanappchallenge.domain.models.MarvelCharacter
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class MarvelDatasourceImpl @Inject constructor(
    private val api: MarvelApi
): MarvelDataSource {
    override suspend fun getCharacters(): Flow<PagingData<MarvelCharacter>> =
        Pager(
            config = PagingConfig(
                pageSize = 20,
                enablePlaceholders = false
            ),
            pagingSourceFactory = { MarvelPagingSource(api) }
        ).flow

    override suspend fun getCharacterDetails(id: Int): Flow<Result<CharacterData>> =
        flow {
            try {
                val response = api.getCharacterDetails(id)
                if (response.isSuccessful) {
                    val data = response.body()?.data?.results?.first()
                    if (data != null) {
                        emit(Result.success(data))
                    } else {
                        emit(Result.failure(Throwable("Character not found")))
                    }
                } else {
                    emit(Result.failure(Throwable(response.message())))
                }
            } catch (e: Exception) {
                emit(Result.failure(e))
            }
        }


}