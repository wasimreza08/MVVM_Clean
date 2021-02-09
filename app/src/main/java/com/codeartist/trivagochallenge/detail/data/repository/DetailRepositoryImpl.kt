package com.codeartist.trivagochallenge.detail.data.repository

import com.codeartist.practicetest.data.remoteentity.FilmEntity
import com.codeartist.trivagochallenge.common.utils.DataState
import com.codeartist.trivagochallenge.detail.data.remoteentity.PlanetEntity
import com.codeartist.trivagochallenge.detail.data.remoteentity.SpeciesEntity
import com.codeartist.trivagochallenge.detail.data.remotesource.DetailAPI
import com.codeartist.trivagochallenge.detail.domain.repository.DetailRepository
import com.codeartist.trivagochallenge.detail.presentation.uimodel.FilmModel
import com.codeartist.trivagochallenge.detail.presentation.uimodel.PlanetModel
import com.codeartist.trivagochallenge.detail.presentation.uimodel.SpeciesModel
import javax.inject.Inject

class DetailRepositoryImpl @Inject constructor(private val detailAPI: DetailAPI) :
    DetailRepository {

    override suspend fun getFilm(id: Int): DataState<FilmEntity> {
        try {
            val films = detailAPI.getFilms(id)
            return DataState.success(films)
        } catch (e: Throwable) {
            return DataState.error(e.message)
        }
    }

    override suspend fun getSpecies(id: Int): DataState<SpeciesEntity> {
        try {
            val species = detailAPI.getSpecies(id)
            //Log.e("DetailRepositoryImpl", species.toString())
            return DataState.success(species)
        } catch (e: Throwable) {
           // Log.e("DetailRepositoryImpl", e.message.toString())
            return DataState.error(e.message)
        }
    }

    override suspend fun getPopulation(id: Int): DataState<PlanetEntity> {
        try {
            val planet = detailAPI.getPopulation(id)
            return DataState.success(planet)
        } catch (e: Throwable) {
            return DataState.error(e.message)
        }
    }

}