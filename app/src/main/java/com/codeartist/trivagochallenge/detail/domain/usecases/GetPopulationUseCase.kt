package com.codeartist.trivagochallenge.detail.domain.usecases

import com.codeartist.trivagochallenge.common.DataState
import com.codeartist.trivagochallenge.detail.domain.repository.DetailRepository
import com.codeartist.trivagochallenge.detail.presentation.uimodel.PlanetModel
import javax.inject.Inject

class GetPopulationUseCase @Inject constructor(private val detailRepository: DetailRepository) {
    suspend fun execute(id: Int): DataState<PlanetModel> {
        return detailRepository.getPopulation(id)
    }
}