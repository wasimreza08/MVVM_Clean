package com.codeartist.trivagochallenge.detail.domain.repository

import com.codeartist.practicetest.data.remoteentity.FilmEntity
import com.codeartist.trivagochallenge.common.utils.DataState
import com.codeartist.trivagochallenge.detail.data.remoteentity.PlanetEntity
import com.codeartist.trivagochallenge.detail.data.remoteentity.SpeciesEntity
import com.codeartist.trivagochallenge.detail.presentation.uimodel.FilmModel
import com.codeartist.trivagochallenge.detail.presentation.uimodel.PlanetModel
import com.codeartist.trivagochallenge.detail.presentation.uimodel.SpeciesModel

interface DetailRepository {
    suspend fun getFilm(id:Int): DataState<FilmEntity>
    suspend fun getSpecies(id:Int): DataState<SpeciesEntity>
    suspend fun getPopulation(id:Int): DataState<PlanetEntity>
}