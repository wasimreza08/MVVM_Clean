package com.codeartist.trivagochallenge.detail.domain.usecases

import com.codeartist.trivagochallenge.common.base.usecase.BaseUseCase
import com.codeartist.trivagochallenge.common.utils.DataState
import com.codeartist.trivagochallenge.common.utils.Status
import com.codeartist.trivagochallenge.detail.domain.entity.HomeWorldEntity
import com.codeartist.trivagochallenge.detail.domain.repository.DetailRepository
import com.codeartist.trivagochallenge.detail.presentation.uimodel.HomeWorldModel
import javax.inject.Inject

class GetHomeWorldUseCase @Inject constructor(private val detailRepository: DetailRepository) :
    BaseUseCase<Int, DataState<HomeWorldEntity>> {
    override suspend fun execute(id: Int): DataState<HomeWorldEntity> {
        return detailRepository.getHomeWorld(id)
    }
}