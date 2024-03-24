package com.example.simanappchallenge.domain.datasource

import androidx.paging.PagingData
import com.example.simanappchallenge.data.model.CharacterData
import com.example.simanappchallenge.domain.models.MarvelCharacter
import kotlinx.coroutines.flow.Flow


interface MarvelDataSource {

    suspend fun getCharacters(): Flow<PagingData<MarvelCharacter>>

    suspend fun getCharacterDetails(id: Int): Flow<Result<CharacterData>>

}