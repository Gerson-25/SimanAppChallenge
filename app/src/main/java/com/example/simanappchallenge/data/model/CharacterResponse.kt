package com.example.simanappchallenge.data.model

import java.util.Date

data class CharacterResponse(
    val code: Int,
    val status: String,
    val copyright: String,
    val attributionText: String,
    val attributionHTML: String,
    val data: Data,
    val etag: String
)

data class Data(
    val offset: Int,
    val limit: Int,
    val total: Int,
    val count: Int,
    val results: List<CharacterData>
)

data class CharacterData(
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
){
    fun constructImageUrl(): String {
        return "${thumbnail.path}.${thumbnail.extension}"
    }
}

data class Url(
    val type: String,
    val url: String
)

data class Thumbnail(
    val path: String,
    val extension: String
)

data class Comics(
    val available: Int,
    val returned: Int,
    val collectionURI: String,
    val items: List<Item>
)

data class Stories(
    val available: Int,
    val returned: Int,
    val collectionURI: String,
    val items: List<StoryItem>
)

data class Events(
    val available: Int,
    val returned: Int,
    val collectionURI: String,
    val items: List<Item>
)

data class Series(
    val available: Int,
    val returned: Int,
    val collectionURI: String,
    val items: List<Item>
)

data class Item(
    val resourceURI: String,
    val name: String
)

data class StoryItem(
    val resourceURI: String,
    val name: String,
    val type: String
)
