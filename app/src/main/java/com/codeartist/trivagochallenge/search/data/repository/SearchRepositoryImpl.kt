package com.codeartist.trivagochallenge.search.data.repository

import com.codeartist.trivagochallenge.common.utils.DataState
import com.codeartist.trivagochallenge.search.data.remotesource.RemoteAPI
import com.codeartist.trivagochallenge.search.data.entity.SearchNetworkEntity
import com.codeartist.trivagochallenge.search.domain.repository.SearchRepository
import javax.inject.Inject

class SearchRepositoryImpl @Inject constructor(private val remoteAPI: RemoteAPI): SearchRepository {
    override suspend fun searchCharacter(searchString: String): DataState<SearchNetworkEntity> {
        return try {
            val data = remoteAPI.getCharacter(searchString)
            DataState.success(data = data)
        } catch (e: Throwable) {
            DataState.error(e.message)
        }

    }

}