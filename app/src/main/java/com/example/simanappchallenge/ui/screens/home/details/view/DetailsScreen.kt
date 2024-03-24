package com.example.simanappchallenge.ui.screens.home.details.view

import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.ViewModel
import coil.compose.AsyncImage
import com.example.simanappchallenge.R
import com.example.simanappchallenge.data.model.Comics
import com.example.simanappchallenge.data.model.Item
import com.example.simanappchallenge.data.model.Series
import com.example.simanappchallenge.ui.components.ComicsItem
import com.example.simanappchallenge.ui.screens.home.details.viewmodel.DetailsViewModel

@Composable
fun DetailsScreen(
    viewModel: DetailsViewModel = hiltViewModel(),
    id: Int
) {
    LaunchedEffect(key1 = true) {
        viewModel.getCharacterDetails(id)
    }

    DetailsScreenContent(viewModel.uiState.value)

}

@Composable
fun DetailsScreenContent(
    uiState: DetailsViewModel.DetailsState
) {

    val scrollState = rememberScrollState()
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.padding(20.dp).verticalScroll(scrollState)
    ) {
        if (uiState.isLoading) {
            CircularProgressIndicator()
        } else {
            if (uiState.data != null) {
                DetailsHeader(
                    name = uiState.data.name ?: "",
                    imageUrl = uiState.data.constructImageUrl() ?: "",
                    description = uiState.data.description ?: "",
                    comics = uiState.data.comics,
                    series = uiState.data.series
                )
            } else {
                Text(text = uiState.error)
            }
        }
    }

}

@Composable
fun DetailsHeader(
    name: String,
    imageUrl: String,
    description: String,
    comics: Comics,
    series: Series
) {
    Card(
        modifier = Modifier
            .width(200.dp)
            .height(300.dp)
    ) {
        AsyncImage(model = imageUrl, contentDescription = name, modifier = Modifier.fillMaxSize(), contentScale = ContentScale.FillHeight)
    }
    Text(
        text = name,
        style = MaterialTheme.typography.titleLarge,
        fontFamily = FontFamily(Font(R.font.heading_now_bold)),
        modifier = Modifier.padding(20.dp))
    Text(
        text = description,
        style = MaterialTheme.typography.bodyMedium, modifier = Modifier.padding(bottom = 10.dp))
    val comics = comics.items.map {
        it.name
    }

    val series = series.items.map {
        it.name
    }

    Text(
        text = "Comics",
        style = MaterialTheme.typography.titleLarge,
        fontFamily = FontFamily(Font(R.font.heading_now_bold)),
        modifier = Modifier.padding(20.dp))
    ComicsItem(items = comics)

    Text(
        text = "Series",
        style = MaterialTheme.typography.titleLarge,
        fontFamily = FontFamily(Font(R.font.heading_now_bold)),
        modifier = Modifier.padding(20.dp))
    ComicsItem(items = series)

}

@Preview(showBackground = true)
@Composable
fun DetailsScreenPreview() {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {

    }

}
