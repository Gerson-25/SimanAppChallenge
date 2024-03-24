package com.example.simanappchallenge.ui.screens.home.list.viewmodel

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.simanappchallenge.data.room.entities.CharacterItem
import com.example.simanappchallenge.domain.models.MarvelCharacter
import com.example.simanappchallenge.domain.repository.MarvelRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.emptyFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ListViewModel @Inject constructor (
    private val repository: MarvelRepository
): ViewModel() {

    var uiState = mutableStateOf(UIState())
    private set

    fun getCharacters() = viewModelScope.launch(Dispatchers.IO) {
        repository.getCharacters().cachedIn(this).collectLatest {
            val data = flow {
                emit(it)
            }
            uiState.value = uiState.value.copy(data = data)
        }
    }

    fun addToFavorite(item: MarvelCharacter) = viewModelScope.launch(Dispatchers.IO) {
        repository.saveLocalCharacters(item).collectLatest {
            if (it) {
                uiState.value = uiState.value.copy(addToFavorite = true)
            } else {
                uiState.value = uiState.value.copy(addToFavorite = false, error = "Error adding character to favorites")
            }
        }
    }

    data class UIState(
        val loading: Boolean = false,
        val data: Flow<PagingData<MarvelCharacter>>? = emptyFlow(),
        val addToFavorite: Boolean = false,
        val error: String = ""
    )

}