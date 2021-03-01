package com.codeartist.starwar.detail.data.remotesource

import com.codeartist.practicetest.data.remoteentity.FilmEntity
import com.codeartist.starwar.detail.domain.entity.HomeWorldEntity
import com.codeartist.starwar.detail.domain.entity.SpeciesEntity
import retrofit2.http.GET
import retrofit2.http.Path

interface DetailAPI {
    @GET("/api/films/{id}/")
    suspend fun getFilms(@Path("id") id: Int): FilmEntity
    @GET("/api/species/{id}/")
    suspend fun getSpecies(@Path("id") id: Int): SpeciesEntity
    @GET("/api/planets/{id}/")
    suspend fun getPopulation(@Path("id") id: Int): HomeWorldEntity
}