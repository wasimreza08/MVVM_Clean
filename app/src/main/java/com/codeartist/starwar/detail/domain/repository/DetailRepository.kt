package com.codeartist.starwar.detail.domain.repository

import com.codeartist.practicetest.data.remoteentity.FilmEntity
import com.codeartist.starwar.common.utils.DataState
import com.codeartist.starwar.detail.domain.entity.HomeWorldEntity
import com.codeartist.starwar.detail.domain.entity.SpeciesEntity
import kotlinx.coroutines.flow.Flow

interface DetailRepository {
    suspend fun getFilm(id:Int): Flow<DataState<FilmEntity>>
    suspend fun getSpecies(id:Int): Flow<DataState<SpeciesEntity>>
    suspend fun getHomeWorld(id:Int): DataState<HomeWorldEntity>
}