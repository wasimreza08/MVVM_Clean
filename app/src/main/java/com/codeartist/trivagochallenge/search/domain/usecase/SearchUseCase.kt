package com.codeartist.trivagochallenge.search.domain.usecase

import com.codeartist.trivagochallenge.common.usecase.BaseUseCase
import com.codeartist.trivagochallenge.common.utils.DataState
import com.codeartist.trivagochallenge.search.domain.repository.SearchRepository
import com.codeartist.trivagochallenge.search.presentation.uimodel.CharacterModel
import javax.inject.Inject

class SearchUseCase @Inject constructor(private val repository: SearchRepository):
    BaseUseCase<String,  DataState<MutableList<CharacterModel>>> {
    override suspend fun execute(requestValues: String): DataState<MutableList<CharacterModel>> {
        val result = repository.searchCharacter(requestValues)
        return result.data?.let {
            return DataState.success(it.convertTo().toMutableList())
        } ?: DataState.error(result.message)
    }
}