package com.codeartist.trivagochallenge.detail.domain.usecases

import com.codeartist.trivagochallenge.common.usecase.BaseUseCase
import com.codeartist.trivagochallenge.common.utils.DataState
import com.codeartist.trivagochallenge.common.utils.Status
import com.codeartist.trivagochallenge.detail.domain.repository.DetailRepository
import com.codeartist.trivagochallenge.detail.presentation.uimodel.PlanetModel
import com.codeartist.trivagochallenge.detail.presentation.uimodel.SpeciesModel
import javax.inject.Inject

class GetPopulationUseCase @Inject constructor(private val detailRepository: DetailRepository) :
    BaseUseCase<Int, DataState<PlanetModel>> {
    override suspend fun execute(id: Int): DataState<PlanetModel> {
        val dataState = detailRepository.getPopulation(id)
        if (dataState.status == Status.SUCCESS) {
            return DataState.success(data = dataState.data?.convertTo())
        } else {
            return DataState.error(dataState.message)
        }
    }
}