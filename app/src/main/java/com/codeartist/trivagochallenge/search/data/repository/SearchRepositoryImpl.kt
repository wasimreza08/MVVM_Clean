package com.codeartist.trivagochallenge.search.data.repository

import com.codeartist.trivagochallenge.common.DataState
import com.codeartist.trivagochallenge.search.data.remotesource.RemoteAPI
import com.codeartist.trivagochallenge.search.data.entity.SearchNetworkEntity
import com.codeartist.trivagochallenge.search.domain.repository.SearchRepository
import javax.inject.Inject

class SearchRepositoryImpl @Inject constructor(private val remoteAPI: RemoteAPI): SearchRepository {
    override suspend fun searchCharacter(searchString: String): DataState<SearchNetworkEntity> {
        try {
            val data = remoteAPI.getCharacter(searchString)
            return DataState.success(data = data)
        } catch (e: Throwable) {
            return DataState.error(e.message)
        }

    }

}