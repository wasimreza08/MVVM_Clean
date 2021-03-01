package com.codeartist.starwar.detail.data.repository

import com.codeartist.practicetest.data.remoteentity.FilmEntity
import com.codeartist.starwar.common.utils.Constants
import com.codeartist.starwar.common.utils.DataState
import com.codeartist.starwar.detail.domain.entity.HomeWorldEntity
import com.codeartist.starwar.detail.domain.entity.SpeciesEntity
import com.codeartist.starwar.detail.data.remotesource.DetailAPI
import com.codeartist.starwar.detail.domain.repository.DetailRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.withTimeout
import javax.inject.Inject

class DetailRepositoryImpl @Inject constructor(private val detailAPI: DetailAPI) :
    DetailRepository {

    override suspend fun getFilm(id: Int): Flow<DataState<FilmEntity>> {
        return flow {
            val films = withTimeout(Constants.TIME_OUT) { detailAPI.getFilms(id) }
            emit(DataState.success(films))
        }.catch { throwable ->
            emit(DataState.error(throwable.message))
        }
    }


    override suspend fun getSpecies(id: Int):  Flow<DataState<SpeciesEntity>> {
        return flow {
            val species = withTimeout(Constants.TIME_OUT) { detailAPI.getSpecies(id) }
            emit(DataState.success(species))
        }.catch { throwable ->
            emit(DataState.error(throwable.message))
        }
    }

    override suspend fun getHomeWorld(id: Int): DataState<HomeWorldEntity> {
        return try {
            val planet = withTimeout(Constants.TIME_OUT) { detailAPI.getPopulation(id) }
            DataState.success(planet)
        } catch (e: Throwable) {
            DataState.error(e.message)
        }
    }

}