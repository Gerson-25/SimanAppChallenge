package com.example.simanappchallenge.data.di

import android.app.Application
import android.content.Context
import androidx.room.Room
import com.example.simanappchallenge.data.datasource.LocalDataSourceImpl
import com.example.simanappchallenge.data.datasource.LoginDatasourceImpl
import com.example.simanappchallenge.data.datasource.MarvelDatasourceImpl
import com.example.simanappchallenge.data.network.MarvelApi
import com.example.simanappchallenge.data.room.database.MarvelDataBase
import com.example.simanappchallenge.domain.datasource.LocalDataSource
import com.example.simanappchallenge.domain.datasource.LoginDatasource
import com.example.simanappchallenge.domain.datasource.MarvelDataSource
import com.google.firebase.BuildConfig
import com.google.firebase.FirebaseOptions
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataSourceModule {

    @Provides
    @Singleton
    fun provideLoginDatasource(context: Application ): LoginDatasource {
        return LoginDatasourceImpl(context)
    }

    @Provides
    @Singleton
    fun provideMarvelDatasource(api: MarvelApi): MarvelDataSource {
        return MarvelDatasourceImpl(api)
    }

    @Provides
    @Singleton
    fun provideDataBase(context: Application): MarvelDataBase {
        return Room.databaseBuilder(
            context,
            MarvelDataBase::class.java,
            "MarvelDB"
        ).build()
    }

    @Provides
    @Singleton
    fun provideLocalDataSource(db: MarvelDataBase): LocalDataSource {
        return LocalDataSourceImpl(db.marvelDao())
    }

}