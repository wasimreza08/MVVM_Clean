package com.codeartist.trivagochallenge.detail.data.repository

import com.codeartist.practicetest.data.remoteentity.FilmEntity
import com.codeartist.trivagochallenge.common.utils.DataState
import com.codeartist.trivagochallenge.detail.data.remoteentity.PlanetEntity
import com.codeartist.trivagochallenge.detail.data.remoteentity.SpeciesEntity
import com.codeartist.trivagochallenge.detail.data.remotesource.DetailAPI
import com.codeartist.trivagochallenge.detail.domain.repository.DetailRepository
import javax.inject.Inject

class DetailRepositoryImpl @Inject constructor(private val detailAPI: DetailAPI) :
    DetailRepository {

    override suspend fun getFilm(id: Int): DataState<FilmEntity> {
        return try {
            val films = detailAPI.getFilms(id)
            DataState.success(films)
        } catch (e: Throwable) {
            DataState.error(e.message)
        }
    }

    override suspend fun getSpecies(id: Int): DataState<SpeciesEntity> {
        return try {
            val species = detailAPI.getSpecies(id)
            //Log.e("DetailRepositoryImpl", species.toString())
            DataState.success(species)
        } catch (e: Throwable) {
            // Log.e("DetailRepositoryImpl", e.message.toString())
            DataState.error(e.message)
        }
    }

    override suspend fun getHomeWorld(id: Int): DataState<PlanetEntity> {
        return try {
            val planet = detailAPI.getPopulation(id)
            DataState.success(planet)
        } catch (e: Throwable) {
            DataState.error(e.message)
        }
    }

}