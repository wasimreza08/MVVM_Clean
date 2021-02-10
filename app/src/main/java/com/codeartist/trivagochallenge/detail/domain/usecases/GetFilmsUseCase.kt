package com.codeartist.trivagochallenge.detail.domain.usecases

import com.codeartist.trivagochallenge.common.usecase.BaseUseCase
import com.codeartist.trivagochallenge.common.utils.DataState
import com.codeartist.trivagochallenge.common.utils.Status
import com.codeartist.trivagochallenge.detail.domain.repository.DetailRepository
import com.codeartist.trivagochallenge.detail.presentation.uimodel.FilmModel
import javax.inject.Inject

class GetFilmsUseCase @Inject constructor(private val detailRepository: DetailRepository) :
    BaseUseCase<Int, DataState<FilmModel>> {
    override suspend fun execute(id: Int): DataState<FilmModel> {
        val dataState = detailRepository.getFilm(id)
        if (dataState.status == Status.SUCCESS) {
            return DataState.success(data = dataState.data?.convertTo())
        } else {
            return DataState.error(dataState.message)
        }
    }
}