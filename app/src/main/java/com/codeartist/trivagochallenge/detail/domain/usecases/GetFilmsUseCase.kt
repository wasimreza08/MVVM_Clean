package com.codeartist.trivagochallenge.detail.domain.usecases

import com.codeartist.trivagochallenge.common.DataState
import com.codeartist.trivagochallenge.detail.domain.repository.DetailRepository
import com.codeartist.trivagochallenge.detail.presentation.uimodel.FilmModel
import javax.inject.Inject

class GetFilmsUseCase @Inject constructor(private val detailRepository: DetailRepository) {
    suspend fun execute(id:Int): DataState<FilmModel>{
        return detailRepository.getFilm(id)
    }
}