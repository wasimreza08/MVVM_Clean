package com.codeartist.trivagochallenge.detail.data.repository

import android.util.Log
import com.codeartist.trivagochallenge.common.DataState
import com.codeartist.trivagochallenge.detail.data.remotesource.DetailAPI
import com.codeartist.trivagochallenge.detail.domain.repository.DetailRepository
import com.codeartist.trivagochallenge.detail.presentation.uimodel.FilmModel
import com.codeartist.trivagochallenge.detail.presentation.uimodel.PlanetModel
import com.codeartist.trivagochallenge.detail.presentation.uimodel.SpeciesModel
import javax.inject.Inject

class DetailRepositoryImpl @Inject constructor(private val detailAPI: DetailAPI) :
    DetailRepository {

    override suspend fun getFilm(id: Int): DataState<FilmModel> {
        try {
            val films = detailAPI.getFilms(id)
            return DataState.success(films.convertTo())
        } catch (e: Throwable) {
            return DataState.error(e.message)
        }
    }

    override suspend fun getSpecies(id: Int): DataState<SpeciesModel> {
        try {
            val species = detailAPI.getSpecies(id)
            //Log.e("DetailRepositoryImpl", species.toString())
            return DataState.success(species.convertTo())
        } catch (e: Throwable) {
           // Log.e("DetailRepositoryImpl", e.message.toString())
            return DataState.error(e.message)
        }
    }

    override suspend fun getPopulation(id: Int): DataState<PlanetModel> {
        try {
            val planet = detailAPI.getPopulation(id)
            return DataState.success(planet.convertTo())
        } catch (e: Throwable) {
            return DataState.error(e.message)
        }
    }

}