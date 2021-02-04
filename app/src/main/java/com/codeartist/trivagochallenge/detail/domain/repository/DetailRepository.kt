package com.codeartist.trivagochallenge.detail.domain.repository

import com.codeartist.trivagochallenge.common.DataState
import com.codeartist.trivagochallenge.detail.presentation.uimodel.FilmModel
import com.codeartist.trivagochallenge.detail.presentation.uimodel.PlanetModel
import com.codeartist.trivagochallenge.detail.presentation.uimodel.SpeciesModel

interface DetailRepository {
    suspend fun getFilm(id:Int):DataState<FilmModel>
    suspend fun getSpecies(id:Int):DataState<SpeciesModel>
    suspend fun getPopulation(id:Int):DataState<PlanetModel>
}