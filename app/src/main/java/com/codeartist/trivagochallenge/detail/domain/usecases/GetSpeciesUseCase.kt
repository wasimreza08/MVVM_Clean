package com.codeartist.trivagochallenge.detail.domain.usecases

import com.codeartist.trivagochallenge.common.base.usecase.BaseUseCase
import com.codeartist.trivagochallenge.common.utils.DataState
import com.codeartist.trivagochallenge.common.utils.Status
import com.codeartist.trivagochallenge.detail.domain.repository.DetailRepository
import com.codeartist.trivagochallenge.detail.presentation.uimodel.SpeciesModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetSpeciesUseCase @Inject constructor(private val detailRepository: DetailRepository) :
    BaseUseCase<Int, Flow<DataState<SpeciesModel>>> {
    override suspend fun execute(id: Int): Flow<DataState<SpeciesModel>> = flow {
        val dataState = detailRepository.getSpecies(id)
        if (dataState.status == Status.SUCCESS) {
            emit(DataState.success(data = dataState.data?.convertTo()))
        } else {
            emit(DataState.error<SpeciesModel>(dataState.message))
        }
    }
}