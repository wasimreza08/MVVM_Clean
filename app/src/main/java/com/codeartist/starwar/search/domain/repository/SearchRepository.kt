package com.codeartist.starwar.search.domain.repository

import com.codeartist.starwar.common.utils.DataState
import com.codeartist.starwar.search.domain.entity.SearchNetworkEntity

interface SearchRepository {
    suspend fun searchCharacter(searchString:String): DataState<SearchNetworkEntity>
}