package com.codeartist.trivagochallenge.search.data.repository

import com.codeartist.trivagochallenge.common.utils.Constants
import com.codeartist.trivagochallenge.common.utils.DataState
import com.codeartist.trivagochallenge.search.data.remotesource.RemoteAPI
import com.codeartist.trivagochallenge.search.domain.entity.SearchNetworkEntity
import com.codeartist.trivagochallenge.search.domain.repository.SearchRepository
import kotlinx.coroutines.withTimeout
import javax.inject.Inject

class SearchRepositoryImpl @Inject constructor(private val remoteAPI: RemoteAPI) :
    SearchRepository {
    override suspend fun searchCharacter(searchString: String): DataState<SearchNetworkEntity> {
        return try {
            val data = withTimeout(Constants.TIME_OUT) { remoteAPI.getCharacter(searchString) }
            DataState.success(data = data)
        } catch (e: Throwable) {
            DataState.error(e.message)
        }
    }
}