package com.codeartist.starwar.search.data.repository

import com.codeartist.starwar.common.utils.Constants
import com.codeartist.starwar.common.utils.DataState
import com.codeartist.starwar.search.data.remotesource.RemoteAPI
import com.codeartist.starwar.search.domain.entity.SearchNetworkEntity
import com.codeartist.starwar.search.domain.repository.SearchRepository
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