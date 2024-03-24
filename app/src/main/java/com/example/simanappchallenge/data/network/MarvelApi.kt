package com.example.simanappchallenge.data.network

import com.example.simanappchallenge.data.model.CharacterResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MarvelApi {

    @GET("/v1/public/characters")
    suspend fun getCharacters(
        @Query("limit") limit: Int,
        @Query("offset") offset: Int
    ): Response<CharacterResponse>

    @GET("/v1/public/characters/{id}")
    suspend fun getCharacterDetails(
        @Path("id") id: Int
    ): Response<CharacterResponse>

}