package com.codeartist.starwar.detail.domain.usecases

import com.codeartist.practicetest.data.remoteentity.FilmEntity
import com.codeartist.starwar.common.base.usecase.BaseUseCase
import com.codeartist.starwar.common.utils.DataState
import com.codeartist.starwar.detail.domain.repository.DetailRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetFilmsUseCase @Inject constructor(private val detailRepository: DetailRepository) :
    BaseUseCase<Int, Flow<DataState<FilmEntity>>> {
    override suspend fun execute(id: Int): Flow<DataState<FilmEntity>> {
        return detailRepository.getFilm(id)
    }
}