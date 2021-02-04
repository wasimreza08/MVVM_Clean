package com.codeartist.trivagochallenge.search.domain.repository

import com.codeartist.trivagochallenge.common.DataState
import com.codeartist.trivagochallenge.search.data.entity.SearchNetworkEntity

interface SearchRepository {
    suspend fun searchCharacter(searchString:String): DataState<SearchNetworkEntity>
}