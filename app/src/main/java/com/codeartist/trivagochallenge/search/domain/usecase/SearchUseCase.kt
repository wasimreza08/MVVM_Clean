package com.codeartist.trivagochallenge.search.domain.usecase

import com.codeartist.trivagochallenge.common.DataState
import com.codeartist.trivagochallenge.search.domain.repository.SearchRepository
import com.codeartist.trivagochallenge.search.presentation.uimodel.CharacterModel
import javax.inject.Inject

class SearchUseCase @Inject constructor(private val repository: SearchRepository) {
    suspend fun execute(searchString: String): DataState<List<CharacterModel>> {
        val result = repository.searchCharacter(searchString)
        return result.data?.let {
            return DataState.success(it.convertTo())
        } ?: DataState.error(result.message)
    }
}