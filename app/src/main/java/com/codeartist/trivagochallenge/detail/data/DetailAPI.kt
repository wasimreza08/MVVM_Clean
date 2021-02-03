package com.codeartist.trivagochallenge.detail.data

import com.codeartist.practicetest.data.remoteentity.FilmEntity
import com.codeartist.trivagochallenge.detail.data.remoteentity.SpeciesEntity
import retrofit2.http.GET
import retrofit2.http.Path

interface DetailAPI {
    @GET("/films/{id}")
    suspend fun getFilms(@Path("id") id: Int): FilmEntity

    @GET("/species/{id}")
    suspend fun getSpecies(@Path("id") id: Int): SpeciesEntity
}