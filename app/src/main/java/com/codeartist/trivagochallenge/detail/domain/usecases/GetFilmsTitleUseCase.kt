package com.codeartist.trivagochallenge.detail.domain.usecases

import com.codeartist.trivagochallenge.detail.domain.repository.DetailRepository
import javax.inject.Inject

class GetFilmsTitleUseCase @Inject constructor() {
    suspend fun execute(id:Int?){
       // repository.getFilms(id)
    }
}