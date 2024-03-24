package com.example.simanappchallenge.data.datasource

import android.content.Context
import com.example.simanappchallenge.data.model.LoginData
import com.example.simanappchallenge.domain.datasource.LoginDatasource
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException
import com.google.firebase.auth.FirebaseAuthInvalidUserException
import com.google.firebase.auth.auth
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.tasks.await

class LoginDatasourceImpl(
   context: Context
): LoginDatasource {

    private lateinit var auth: FirebaseAuth

    override suspend fun login(email: String, password: String): Flow<Result<LoginData>> =
        flow {
            auth = Firebase.auth
            try {
                val result = auth.signInWithEmailAndPassword(email, password).await()
                if (result.user == null) {
                    throw Exception("Error al iniciar sesión")
                } else {
                    emit(Result.success(LoginData(email, auth.currentUser?.uid ?: "")))
                }
            } catch (e: Exception) {
                when (e) {
                    is FirebaseAuthInvalidUserException -> {
                        emit(Result.failure(Exception("Usuario no encontrado")))
                    }
                    is FirebaseAuthInvalidCredentialsException -> {
                        emit(Result.failure(Exception("Contraseña incorrecta")))
                    }
                    else -> {
                        emit(Result.failure(e))
                    }
                }

            }
        }

    override suspend fun register(email: String, password: String): Flow<Result<LoginData>> =
        flow {
            auth = Firebase.auth
            try {
                val result = auth.createUserWithEmailAndPassword(email, password).await()
                if (result.user == null) {
                    throw Exception("Error al registrar usuario")
                } else {
                    emit(Result.success(LoginData(email, auth.currentUser?.uid ?: "")))
                }
            } catch (e: Exception) {
                emit(Result.failure(e))
            }
        }
}