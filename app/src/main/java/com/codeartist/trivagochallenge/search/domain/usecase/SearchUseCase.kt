package com.codeartist.trivagochallenge.search.domain.usecase

import com.codeartist.trivagochallenge.common.base.usecase.BaseUseCase
import com.codeartist.trivagochallenge.common.utils.DataState
import com.codeartist.trivagochallenge.common.utils.Status
import com.codeartist.trivagochallenge.search.domain.repository.SearchRepository
import com.codeartist.trivagochallenge.search.presentation.uimodel.CharacterModel
import javax.inject.Inject

class SearchUseCase @Inject constructor(private val repository: SearchRepository) :
    BaseUseCase<String, DataState<MutableList<CharacterModel>>> {
    override suspend fun execute(requestValues: String): DataState<MutableList<CharacterModel>> {
        val result = repository.searchCharacter(requestValues)
        if (result.status == Status.SUCCESS) {
            return DataState.success(result.data?.let { it.convertTo().toMutableList() })
        } else {
            return DataState.error(result.message)
        }
    }
}