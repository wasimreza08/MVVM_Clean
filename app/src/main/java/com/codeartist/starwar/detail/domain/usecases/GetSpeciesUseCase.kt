package com.codeartist.starwar.detail.domain.usecases

import com.codeartist.starwar.common.base.usecase.BaseUseCase
import com.codeartist.starwar.common.utils.DataState
import com.codeartist.starwar.detail.domain.entity.SpeciesEntity
import com.codeartist.starwar.detail.domain.repository.DetailRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetSpeciesUseCase @Inject constructor(private val detailRepository: DetailRepository) :
    BaseUseCase<Int, Flow<DataState<SpeciesEntity>>> {
    override suspend fun execute(id: Int): Flow<DataState<SpeciesEntity>> {
        return detailRepository.getSpecies(id)
    }
}