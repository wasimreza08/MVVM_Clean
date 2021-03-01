package com.codeartist.starwar.detail.domain.usecases

import com.codeartist.starwar.common.base.usecase.BaseUseCase
import com.codeartist.starwar.common.utils.DataState
import com.codeartist.starwar.detail.domain.entity.HomeWorldEntity
import com.codeartist.starwar.detail.domain.repository.DetailRepository
import javax.inject.Inject

class GetHomeWorldUseCase @Inject constructor(private val detailRepository: DetailRepository) :
    BaseUseCase<Int, DataState<HomeWorldEntity>> {
    override suspend fun execute(id: Int): DataState<HomeWorldEntity> {
        return detailRepository.getHomeWorld(id)
    }
}