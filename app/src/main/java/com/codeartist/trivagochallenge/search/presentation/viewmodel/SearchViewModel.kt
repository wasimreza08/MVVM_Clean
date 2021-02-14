package com.codeartist.trivagochallenge.search.presentation.viewmodel

import androidx.annotation.VisibleForTesting
import androidx.hilt.Assisted
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.*
import com.codeartist.trivagochallenge.common.utils.DataState
import com.codeartist.trivagochallenge.common.utils.Status
import com.codeartist.trivagochallenge.search.domain.entity.SearchNetworkEntity
import com.codeartist.trivagochallenge.search.domain.usecase.SearchUseCase
import com.codeartist.trivagochallenge.search.presentation.uimodel.CharacterModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers.IO

class SearchViewModel @ViewModelInject constructor(
    private val searchUseCase: SearchUseCase,
    @Assisted private val savedStateHandle: SavedStateHandle
) : ViewModel() {

    var defaultDispatcher: CoroutineDispatcher = IO
        @VisibleForTesting set

    private val _searchText: MutableLiveData<String> = MutableLiveData("")
    val searchResult by lazy {
        _searchText.switchMap {
            liveData(context = viewModelScope.coroutineContext + defaultDispatcher) {
                if (it.isNotEmpty()) {
                    emit(DataState.loading())
                    emit(convertSearchEntityToCharacterModel(searchUseCase.execute(it)))
                } else {
                    emit(DataState.success(emptyList<CharacterModel>()))
                }
            }
        }
    }

    private fun convertSearchEntityToCharacterModel(result: DataState<SearchNetworkEntity>): DataState<MutableList<CharacterModel>> {
        if (result.status == Status.SUCCESS) {
            return DataState.success(result.data?.let { it.convertTo().toMutableList() })
        } else {
            return DataState.error(result.message)
        }
    }

    fun setSearchString(query: String) {
        _searchText.value = query
    }
}