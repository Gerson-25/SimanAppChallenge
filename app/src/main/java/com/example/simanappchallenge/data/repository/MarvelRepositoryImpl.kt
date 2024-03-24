package com.example.simanappchallenge.data.repository

import androidx.paging.PagingData
import com.example.simanappchallenge.data.model.CharacterData
import com.example.simanappchallenge.domain.datasource.LocalDataSource
import com.example.simanappchallenge.domain.datasource.MarvelDataSource
import com.example.simanappchallenge.domain.models.MarvelCharacter
import com.example.simanappchallenge.domain.repository.MarvelRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class MarvelRepositoryImpl @Inject constructor(
    private val marvelDataSource: MarvelDataSource,
    private val localDataSource: LocalDataSource,
): MarvelRepository{
    override suspend fun getCharacters(): Flow<PagingData<MarvelCharacter>> {
        return marvelDataSource.getCharacters()
    }

    override suspend fun getCharacterDetails(id: Int): Flow<Result<CharacterData>> {
        return marvelDataSource.getCharacterDetails(id)
    }

    override suspend fun getLocalCharacters(): Flow<Result<List<MarvelCharacter>>> {
        return localDataSource.getLocalCharacters()
    }

    override suspend fun saveLocalCharacters(character: MarvelCharacter): Flow<Boolean> {
        return localDataSource.saveLocalCharacters(character)
    }


}