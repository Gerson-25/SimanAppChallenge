package com.example.simanappchallenge.ui.screens.home.details.viewmodel

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.simanappchallenge.data.model.CharacterData
import com.example.simanappchallenge.domain.repository.MarvelRepository
import com.example.simanappchallenge.ui.screens.home.details.model.CharacterDetails
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class DetailsViewModel @Inject constructor (
    private val repository: MarvelRepository
): ViewModel() {

    var uiState = mutableStateOf(DetailsState())
    private set

        fun getCharacterDetails(id: Int) = viewModelScope.launch(Dispatchers.IO) {
            uiState.value = uiState.value.copy(isLoading = true)
            repository.getCharacterDetails(id).collectLatest {
                uiState.value = uiState.value.copy(isLoading = false)
                it.onSuccess { data ->
                    uiState.value = uiState.value.copy(data = data)
                }.onFailure { error ->
                    uiState.value = uiState.value.copy(error = error.message ?: "An error occurred")
                }
            }
        }

    data class DetailsState(
        val isLoading: Boolean = false,
        val data: CharacterData? = null,
        val error: String = ""
    )
}