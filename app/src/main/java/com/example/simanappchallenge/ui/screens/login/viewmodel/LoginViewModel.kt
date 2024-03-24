package com.example.simanappchallenge.ui.screens.login.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.simanappchallenge.data.model.LoginData
import com.example.simanappchallenge.domain.repository.LoginRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor (
     private val loginRepository: LoginRepository
) : ViewModel() {

    var uiState by mutableStateOf(UiState())
        private set


    fun login(email: String, password: String) = viewModelScope.launch(Dispatchers.IO) { uiState = uiState.copy(isLoading = true, loginData = null, error = "")
        loginRepository.login(email, password).collectLatest { result ->
            withContext(Dispatchers.Main) {
                if (result.isSuccess) {
                    uiState = uiState.copy(isLoading = false, loginData = result.getOrNull())
                } else {
                    uiState = uiState.copy(
                        isLoading = false,
                        error = result.exceptionOrNull()?.message ?: "Error"
                    )
                }
            }
        }
    }

    data class UiState(
        var isLoading:Boolean = false,
        var loginData: LoginData? = null,
        var error: String = ""
    )

}