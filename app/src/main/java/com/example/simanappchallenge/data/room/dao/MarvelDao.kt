package com.example.simanappchallenge.data.room.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.simanappchallenge.data.room.entities.CharacterItem

@Dao
interface MarvelDao {

    @Query("SELECT * FROM character_table")
    suspend fun getCharacters(): List<CharacterItem>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveCharacters(item: CharacterItem)

}