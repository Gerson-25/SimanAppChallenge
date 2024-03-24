package com.example.simanappchallenge.data.repository

import com.example.simanappchallenge.data.model.LoginData
import com.example.simanappchallenge.domain.datasource.LoginDatasource
import com.example.simanappchallenge.domain.repository.LoginRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class LoginRepositoryImpl @Inject constructor (
    private val loginDatasource: LoginDatasource
): LoginRepository {
    override suspend fun login(email: String, password: String): Flow<Result<LoginData>> {
        return loginDatasource.login(email, password)
    }

    override suspend fun register(email: String, password: String): Flow<Result<LoginData>> {
        return loginDatasource.register(email, password)
    }
}