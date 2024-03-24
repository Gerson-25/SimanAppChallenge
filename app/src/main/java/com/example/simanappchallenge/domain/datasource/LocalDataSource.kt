package com.example.simanappchallenge.domain.datasource

import com.example.simanappchallenge.domain.models.MarvelCharacter
import kotlinx.coroutines.flow.Flow

interface LocalDataSource {
    suspend fun getLocalCharacters():Flow<Result<List<MarvelCharacter>>>

    suspend fun saveLocalCharacters(character: MarvelCharacter): Flow<Boolean>
}