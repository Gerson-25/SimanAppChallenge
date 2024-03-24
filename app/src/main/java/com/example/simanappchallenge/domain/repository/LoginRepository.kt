package com.example.simanappchallenge.domain.repository

import com.example.simanappchallenge.data.model.LoginData
import kotlinx.coroutines.flow.Flow

interface LoginRepository {
    suspend fun login(email: String, password: String): Flow<Result<LoginData>>

    suspend fun register(email: String, password: String): Flow<Result<LoginData>>
}