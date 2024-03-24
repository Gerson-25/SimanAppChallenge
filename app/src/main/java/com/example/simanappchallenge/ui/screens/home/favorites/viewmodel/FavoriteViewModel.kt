package com.example.simanappchallenge.ui.screens.home.favorites.viewmodel

import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.simanappchallenge.domain.models.MarvelCharacter
import com.example.simanappchallenge.domain.repository.MarvelRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FavoriteViewModel @Inject constructor (
    private val repository: MarvelRepository
): ViewModel() {

    var uiState = mutableStateOf(FavoriteState())

    fun getLocalCharacters() = viewModelScope.launch {
        uiState.value = uiState.value.copy(loading = true)
        repository.getLocalCharacters().collectLatest {
            uiState.value = uiState.value.copy(loading = false)
            it.onSuccess {
                uiState.value = uiState.value.copy(data = it)
            }.onFailure {
                uiState.value = uiState.value.copy(error = it.message ?: "An error occurred")
            }
        }
    }


    data class FavoriteState(
        val loading: Boolean = false,
        val data: List<MarvelCharacter>? = emptyList(),
        val error: String = ""
    )

}