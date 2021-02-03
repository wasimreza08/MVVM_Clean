package com.codeartist.trivagochallenge.search.data.repository.domain.usecase

import android.provider.ContactsContract
import com.codeartist.trivagochallenge.common.DataState
import com.codeartist.trivagochallenge.search.data.repository.domain.repository.SearchRepository
import com.codeartist.trivagochallenge.search.presentation.uimodel.CharaterModel
import javax.inject.Inject

class SearchUseCase @Inject constructor(private val repository: SearchRepository) {
    suspend fun execute(searchString: String): DataState<List<CharaterModel>> {
        val result = repository.searchCharacter(searchString)
        return result.data?.let {
            return DataState.success(it.convertTo())
        } ?: DataState.error(result.message)
    }
}