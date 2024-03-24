package com.example.simanappchallenge.domain.mapper


import com.example.simanappchallenge.data.model.CharacterData
import com.example.simanappchallenge.data.model.Thumbnail
import com.example.simanappchallenge.domain.models.MarvelCharacter

fun CharacterData.toDomain(): MarvelCharacter {
    return MarvelCharacter(
        id = id,
        name = name,
        description = description,
        image = thumbnail.toUrl(),
    )
}

fun Thumbnail.toUrl(): String {
    return "$path.$extension"
}