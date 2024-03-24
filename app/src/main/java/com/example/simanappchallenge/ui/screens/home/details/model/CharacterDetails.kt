package com.example.simanappchallenge.ui.screens.home.details.model

import com.example.simanappchallenge.data.model.Comics
import com.example.simanappchallenge.data.model.Events
import com.example.simanappchallenge.data.model.Series
import com.example.simanappchallenge.data.model.Stories
import com.example.simanappchallenge.data.model.Thumbnail
import com.example.simanappchallenge.data.model.Url
import java.util.Date

data class CharacterDetails(
    val id: Int,
    val name: String,
    val description: String,
    val modified: Date,
    val resourceURI: String,
    val urls: List<Url>,
    val thumbnail: Thumbnail,
    val comics: Comics,
    val stories: Stories,
    val events: Events,
    val series: Series
)
