package com.example.simanappchallenge.ui.screens.home.list.view

import android.annotation.SuppressLint
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.ListItem
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ShapeDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.items
import com.example.simanappchallenge.R
import com.example.simanappchallenge.domain.models.MarvelCharacter
import com.example.simanappchallenge.ui.components.CharacterItem
import com.example.simanappchallenge.ui.screens.home.list.viewmodel.ListViewModel
import com.example.simanappchallenge.ui.theme.SimanAppChallengeTheme

@Composable
fun ListScreen(
    viewModel: ListViewModel = hiltViewModel(),
    onGoFavorites: () -> Unit,
    onCharacterClick: (Int) -> Unit
) {
    LaunchedEffect(key1 = true){
        viewModel.getCharacters()
    }
    
    ListScreenContent(viewModel.uiState.value, {
        onGoFavorites()
    }, {
        viewModel.addToFavorite(it)
    }){
        onCharacterClick(it)
    }
}

@Composable
fun ListScreenContent(
    state: ListViewModel.UIState,
    onGoFavorites: () -> Unit,
    onAddToFavorite: (MarvelCharacter) -> Unit,
    onCharacterClick: (Int) -> Unit
) {
    val items = state.data?.collectAsLazyPagingItems()

    Column(modifier = Modifier
        .padding(10.dp)
        .fillMaxWidth()) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 20.dp, bottom = 20.dp)
        ) {
            Text(text = "Cerrar Sesión", style = MaterialTheme.typography.bodyMedium)
            Icon(painter = painterResource(id = R.drawable.baseline_logout_24),
                contentDescription = "Error",
                Modifier
                    .clickable {
                        onGoFavorites()
                    }
                    .padding(end = 10.dp)
            )
            Row(
                modifier = Modifier
                    .clickable {
                        onGoFavorites()
                    }
                    .padding(end = 10.dp)
            ) {
                Text(text = "Ver favoritos", style = MaterialTheme.typography.bodyMedium)
                Icon(
                    painter = painterResource(id = R.drawable.baseline_bookmark_border_24),
                    contentDescription = "Favoritos"
                )
            }
            Text(text = "Buscar", style = MaterialTheme.typography.bodyMedium)
            Icon(painter = painterResource(id = R.drawable.baseline_search_24), contentDescription = "Buscar")
        }

        Text(text = "Personajes de Marvel", fontFamily = FontFamily(Font(R.font.heading_now_bold)), style = MaterialTheme.typography.titleLarge)

        LazyColumn(contentPadding = PaddingValues(10.dp)) {
            items(items!!){ item ->
                CharacterItem(item ?: MarvelCharacter(0, "", "", ""),
                    onAddToFavorite = { selectedItem ->
                        onAddToFavorite(selectedItem)
                    }){
                    onCharacterClick(it)
                }
            }
        }
    }
}

@SuppressLint("UnrememberedMutableState")
@Preview(showBackground = true)
@Composable
fun ListScreenPreview() {
    SimanAppChallengeTheme {
//        val list = listOf(
//            MarvelCharacter(1, "Spiderman", "https://i.annihil.us/u/prod/marvel/i/mg/3/50/526548a343e4b.jpg", "Spiderman"),
//            MarvelCharacter(2, "Ironman", "https://i.annihil.us/u/prod/marvel/i/mg/9/c0/527bb7b37ff55.jpg", "Ironman"),
//            MarvelCharacter(3, "Hulk", "https://i.annihil.us/u/prod/marvel/i/mg/5/a0/538615ca33ab0.jpg", "Hulk"),
//        )
//        LazyColumn(contentPadding = PaddingValues(10.dp)) {
//            items(list.size){ item ->
//                CharacterItem(list[item],
//                    onAddToFavorite = { selectedItem ->
//
//                    }){
//
//                }
//            }
//        }
        ListScreenContent( ListViewModel.UIState(),onGoFavorites = { }, onCharacterClick = {}, onAddToFavorite = {})
    }
}