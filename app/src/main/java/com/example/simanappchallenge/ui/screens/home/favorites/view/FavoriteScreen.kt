package com.example.simanappchallenge.ui.screens.home.favorites.view

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.ViewModel
import com.example.simanappchallenge.R
import com.example.simanappchallenge.domain.models.MarvelCharacter
import com.example.simanappchallenge.ui.components.CharacterItem
import com.example.simanappchallenge.ui.screens.home.favorites.viewmodel.FavoriteViewModel

@Composable
fun FavoriteScreen(
    viewModel: FavoriteViewModel = hiltViewModel(),
    onCharacterClick: (Int) -> Unit
) {

    LaunchedEffect(key1 = true) {
        viewModel.getLocalCharacters()
    }

    FavoriteScreenContent(viewModel.uiState.value){
        onCharacterClick(it)
    }

}

@Composable
fun FavoriteScreenContent(
    uiState: FavoriteViewModel.FavoriteState,
    onCharacterClick: (Int) -> Unit
) {

    Column(modifier = Modifier.padding(10.dp)) {
        Text(
            text = "Mis Personajes Favoritos",
            fontFamily = FontFamily(Font(R.font.heading_now_bold)),
            style = MaterialTheme.typography.titleLarge,
            modifier = Modifier.padding(bottom = 20.dp, start = 10.dp))
        LazyColumn(contentPadding = PaddingValues(10.dp)) {
            items(uiState.data?.size ?: 0) { index ->
                CharacterItem(item = uiState.data?.get(index) ?: MarvelCharacter(0,"", "", ""   ), onAddToFavorite = {}, onCharacterClick = {
                    onCharacterClick(it)
                })
            }
        }
    }
}