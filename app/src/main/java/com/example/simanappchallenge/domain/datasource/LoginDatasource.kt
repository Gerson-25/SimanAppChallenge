package com.example.simanappchallenge.domain.datasource

import com.example.simanappchallenge.data.model.LoginData
import kotlinx.coroutines.flow.Flow

interface LoginDatasource {
    suspend fun login(email: String, password: String): Flow<Result<LoginData>>

    suspend fun register(email: String, password: String): Flow<Result<LoginData>>

}