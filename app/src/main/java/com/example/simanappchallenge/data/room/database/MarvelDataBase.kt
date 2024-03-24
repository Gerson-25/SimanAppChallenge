package com.example.simanappchallenge.data.room.database

import androidx.room.Database
import androidx.room.DatabaseConfiguration
import androidx.room.InvalidationTracker
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteOpenHelper
import com.example.simanappchallenge.data.room.dao.MarvelDao
import com.example.simanappchallenge.data.room.entities.CharacterItem

@Database(entities = [CharacterItem::class], version = 1, exportSchema = false)
abstract class MarvelDataBase:RoomDatabase() {
    abstract fun marvelDao(): MarvelDao
}