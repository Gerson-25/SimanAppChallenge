package com.example.simanappchallenge.data.di

import com.example.simanappchallenge.data.repository.LoginRepositoryImpl
import com.example.simanappchallenge.data.repository.MarvelRepositoryImpl
import com.example.simanappchallenge.domain.datasource.LocalDataSource
import com.example.simanappchallenge.domain.datasource.LoginDatasource
import com.example.simanappchallenge.domain.datasource.MarvelDataSource
import com.example.simanappchallenge.domain.repository.LoginRepository
import com.example.simanappchallenge.domain.repository.MarvelRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Provides
    @Singleton
    fun provideLoginRepository(
        loginDatasource: LoginDatasource
    ): LoginRepository {
        return LoginRepositoryImpl(loginDatasource)
    }

    @Provides
    @Singleton
    fun provideMarvelRepository(
        marvelDataSource: MarvelDataSource,
        localDataSource: LocalDataSource
    ): MarvelRepository {
        return MarvelRepositoryImpl(marvelDataSource, localDataSource)
    }

}