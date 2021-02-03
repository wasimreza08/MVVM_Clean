package com.codeartist.trivagochallenge.search.presentation.viewmodel

import androidx.hilt.Assisted
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.*
import com.codeartist.trivagochallenge.common.DataState
import com.codeartist.trivagochallenge.search.domain.usecase.SearchUseCase
import com.codeartist.trivagochallenge.search.presentation.uimodel.CharaterModel
import kotlinx.coroutines.Dispatchers

class SearchViewModel @ViewModelInject constructor(
    private val searchUseCase: SearchUseCase,
    @Assisted private val savedStateHandle: SavedStateHandle
) : ViewModel() {
    private val _searchText: MutableLiveData<String> = MutableLiveData("")
    val searchText: LiveData<String> = _searchText
    val searchResult: LiveData<DataState<List<CharaterModel>>> = searchText.switchMap {
        liveData(context = viewModelScope.coroutineContext + Dispatchers.IO) {
            if (it.isNotEmpty()) {
                emit(DataState.loading())
                emit(searchUseCase.execute(it))
            } else {
                emit(DataState.success(emptyList<CharaterModel>()))
            }
        }
    }

    fun setSearchString(query: String) {
        _searchText.value = query
    }
}