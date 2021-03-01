package com.codeartist.starwar.search.domain.usecase

import com.codeartist.starwar.common.base.usecase.BaseUseCase
import com.codeartist.starwar.common.utils.DataState
import com.codeartist.starwar.search.domain.entity.SearchNetworkEntity
import com.codeartist.starwar.search.domain.repository.SearchRepository
import javax.inject.Inject

class SearchUseCase @Inject constructor(private val repository: SearchRepository) :
    BaseUseCase<String, DataState<SearchNetworkEntity>> {
    override suspend fun execute(requestValues: String): DataState<SearchNetworkEntity> {
        return repository.searchCharacter(requestValues)
    }
}