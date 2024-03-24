package com.example.simanappchallenge.data.datasource

import com.example.simanappchallenge.data.room.dao.MarvelDao
import com.example.simanappchallenge.data.room.entities.CharacterItem
import com.example.simanappchallenge.domain.datasource.LocalDataSource
import com.example.simanappchallenge.domain.models.MarvelCharacter
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class LocalDataSourceImpl(
   private val dao: MarvelDao
): LocalDataSource {

    override suspend fun getLocalCharacters(): Flow<Result<List<MarvelCharacter>>> = flow {
        try {
            val list = dao.getCharacters().map {
                MarvelCharacter(
                    id = it.id,
                    name = it.name,
                    description = it.description,
                    image = it.imageUrl
                )
            }
            emit(Result.success(list))
        } catch (e: Exception) {
            emit(Result.failure(e))
        }
    }

    override suspend fun saveLocalCharacters(character: MarvelCharacter): Flow<Boolean> = flow {
        try {
            dao.saveCharacters(
                CharacterItem(
                    id = character.id,
                    name = character.name,
                    description = character.description,
                    imageUrl = character.image
                )
            )
            emit(true)
        } catch (e: Exception) {
            emit(false)
        }
    }

}