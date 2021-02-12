package com.codeartist.trivagochallenge.detail.domain.usecases

import com.codeartist.trivagochallenge.common.base.usecase.BaseUseCase
import com.codeartist.trivagochallenge.common.utils.DataState
import com.codeartist.trivagochallenge.common.utils.Status
import com.codeartist.trivagochallenge.detail.domain.repository.DetailRepository
import com.codeartist.trivagochallenge.detail.presentation.uimodel.HomeWorldModel
import javax.inject.Inject

class GetHomeWorldUseCase @Inject constructor(private val detailRepository: DetailRepository) :
    BaseUseCase<Int, DataState<HomeWorldModel>> {
    override suspend fun execute(id: Int): DataState<HomeWorldModel> {
        val dataState = detailRepository.getHomeWorld(id)
        if (dataState.status == Status.SUCCESS) {
            return DataState.success(data = dataState.data?.convertTo())
        } else {
            return DataState.error(dataState.message)
        }
    }
}