package com.codeartist.trivagochallenge.detail.domain.usecases

import com.codeartist.trivagochallenge.common.usecase.BaseUseCase
import com.codeartist.trivagochallenge.common.utils.DataState
import com.codeartist.trivagochallenge.common.utils.Status
import com.codeartist.trivagochallenge.detail.domain.repository.DetailRepository
import com.codeartist.trivagochallenge.detail.presentation.uimodel.SpeciesModel
import javax.inject.Inject

class GetSpeciesUseCase @Inject constructor(private val detailRepository: DetailRepository) :
    BaseUseCase<Int, DataState<SpeciesModel>> {
    override suspend fun execute(id: Int): DataState<SpeciesModel> {
        val dataState = detailRepository.getSpecies(id)
        if (dataState.status == Status.SUCCESS) {
            return DataState.success(data = dataState.data?.convertTo())
        } else {
            return DataState.error(dataState.message)
        }
    }
}