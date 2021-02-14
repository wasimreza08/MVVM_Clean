package com.codeartist.trivagochallenge.search.domain.usecase

import com.codeartist.trivagochallenge.common.base.usecase.BaseUseCase
import com.codeartist.trivagochallenge.common.utils.DataState
import com.codeartist.trivagochallenge.common.utils.Status
import com.codeartist.trivagochallenge.search.domain.entity.SearchNetworkEntity
import com.codeartist.trivagochallenge.search.domain.repository.SearchRepository
import com.codeartist.trivagochallenge.search.presentation.uimodel.CharacterModel
import javax.inject.Inject

class SearchUseCase @Inject constructor(private val repository: SearchRepository) :
    BaseUseCase<String, DataState<SearchNetworkEntity>> {
    override suspend fun execute(requestValues: String): DataState<SearchNetworkEntity> {
        return repository.searchCharacter(requestValues)
    }
}