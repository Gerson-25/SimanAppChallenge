package com.example.simanappchallenge.domain.repository

import androidx.paging.PagingData
import com.example.simanappchallenge.data.model.CharacterData
import com.example.simanappchallenge.domain.models.MarvelCharacter
import kotlinx.coroutines.flow.Flow

interface MarvelRepository {

        suspend fun getCharacters(): Flow<PagingData<MarvelCharacter>>

        suspend fun getCharacterDetails(id: Int): Flow<Result<CharacterData>>

        suspend fun getLocalCharacters(): Flow<Result<List<MarvelCharacter>>>

        suspend fun saveLocalCharacters(character: MarvelCharacter): Flow<Boolean>

}