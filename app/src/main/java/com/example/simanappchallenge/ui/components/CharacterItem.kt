package com.example.simanappchallenge.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardColors
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.simanappchallenge.R
import com.example.simanappchallenge.domain.models.MarvelCharacter

@Composable
fun CharacterItem(
    item: MarvelCharacter,
    modifier: Modifier = Modifier,
    onAddToFavorite: (MarvelCharacter) -> Unit,
    onCharacterClick: (Int) -> Unit
) {
    val addedToFavorite = remember {
        mutableStateOf(false)
    }
    Card(
        modifier = modifier
            .clickable {
                onCharacterClick(item.id)
            }.padding(bottom = 10.dp),
        elevation = CardDefaults.cardElevation(4.dp)
    ) {
        Column {
            AsyncImage(
                model = item.image,
                contentDescription = "Character Image",
                contentScale = ContentScale.Crop,
                modifier = Modifier.height(200.dp)
            )
            Text(text = item.name, Modifier.padding(10.dp), style = MaterialTheme.typography.titleLarge, fontFamily = FontFamily(Font(R.font.heading_now_bold)))
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier.clickable {
                    onAddToFavorite(item)
                    addedToFavorite.value = !addedToFavorite.value
                }
            ) {
                Text(
                    text = "Guardar",
                    Modifier.padding(10.dp),
                    style = MaterialTheme.typography.bodySmall,
                    maxLines = 3,
                    fontFamily = FontFamily(Font(R.font.heading_now_bold))
                )

                Image(
                    painter =
                    if (addedToFavorite.value)
                        painterResource(id = R.drawable.baseline_bookmark_added_24)
                    else
                        painterResource(id = R.drawable.baseline_bookmark_border_24),
                    contentDescription = "Like Icon",
                    contentScale = ContentScale.FillWidth,
                    modifier = Modifier
                        .padding(10.dp)
                )
            }

        }
    }
}

@Preview
@Composable
fun CharacterItemPreview() {
    CharacterItem(
        item = MarvelCharacter(
            id = 1,
            name = "Spiderman",
            description = "Spiderman is a fictional superhero created by writer Stan Lee and artist Steve Ditko. " +
                    "He first appeared in the anthology comic book Amazing Fantasy #15 (August 1962) in the Silver Age of Comic Books. " +
                    "He appears in American comic books published by Marvel Comics, as well as in a number of movies, " +
                    "television shows, and video game adaptations set in the Marvel Universe. In the stories, Spider",
            image = "https://upload.wikimedia.org/wikipedia/en/0/0c/Spiderman50.jpg"),
        onCharacterClick = {
            // Do nothing
        },
        onAddToFavorite = {

        }
    )
}