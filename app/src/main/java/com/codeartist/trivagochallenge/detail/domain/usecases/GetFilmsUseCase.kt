package com.codeartist.trivagochallenge.detail.domain.usecases

import com.codeartist.trivagochallenge.common.base.usecase.BaseUseCase
import com.codeartist.trivagochallenge.common.utils.DataState
import com.codeartist.trivagochallenge.common.utils.Status
import com.codeartist.trivagochallenge.detail.domain.repository.DetailRepository
import com.codeartist.trivagochallenge.detail.presentation.uimodel.FilmModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetFilmsUseCase @Inject constructor(private val detailRepository: DetailRepository) :
    BaseUseCase<Int, Flow<DataState<FilmModel>>> {
    override suspend fun execute(id: Int): Flow<DataState<FilmModel>> =  flow {
        val dataState = detailRepository.getFilm(id)
        if (dataState.status == Status.SUCCESS) {
            emit(DataState.success(data = dataState.data?.convertTo()))
        } else {
           emit(DataState.error<FilmModel>(dataState.message))
        }
    }
}