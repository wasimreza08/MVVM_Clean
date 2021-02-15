package com.codeartist.trivagochallenge.detail.presentation.viewmodel

import androidx.annotation.VisibleForTesting
import androidx.hilt.Assisted
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.*
import com.codeartist.trivagochallenge.common.utils.Constants
import com.codeartist.trivagochallenge.common.utils.DataState
import com.codeartist.trivagochallenge.common.utils.Status
import com.codeartist.trivagochallenge.common.utils.Utils
import com.codeartist.trivagochallenge.common.utils.Utils.isHomeWorldEmpty
import com.codeartist.trivagochallenge.detail.domain.usecases.GetFilmsUseCase
import com.codeartist.trivagochallenge.detail.domain.usecases.GetHomeWorldUseCase
import com.codeartist.trivagochallenge.detail.domain.usecases.GetSpeciesUseCase
import com.codeartist.trivagochallenge.detail.presentation.uimodel.FilmModel
import com.codeartist.trivagochallenge.detail.presentation.uimodel.FullDetailModel
import com.codeartist.trivagochallenge.detail.presentation.uimodel.HomeWorldModel
import com.codeartist.trivagochallenge.detail.presentation.uimodel.SpeciesModel
import com.codeartist.trivagochallenge.search.presentation.uimodel.CharacterModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.*

class DetailViewModel @ViewModelInject constructor(
    private val getFilmsUseCase: GetFilmsUseCase,
    private val getSpeciesUseCase: GetSpeciesUseCase,
    private val getPopulationUseCase: GetHomeWorldUseCase,
    @Assisted private val savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val TAG = "DetailViewModel"
    var defaultDispatcher: CoroutineDispatcher = Dispatchers.IO
        @VisibleForTesting set
    private var isError = false

    private val _characterModel: MutableLiveData<CharacterModel> = MutableLiveData()

    @FlowPreview
    val detailInfo: LiveData<DataState<FullDetailModel>> = _characterModel.switchMap {
        liveData(context = viewModelScope.coroutineContext + defaultDispatcher) {
            emit(DataState.loading())
            val homeWorldInfo =
                viewModelScope.async(defaultDispatcher) { getHomeWorldModel(it.homeWorld) }
            val filmInfo = viewModelScope.async(defaultDispatcher) {// getFilms(it.films)
                getFilmList(it.films)
            }
            val speciesInfo = viewModelScope.async(defaultDispatcher) { getSpeciesList(it.species) }

            val detailData = FullDetailModel(
                filmInfo.await(), speciesInfo.await(),
                homeWorldInfo.await()
            )
            if (isError && detailData.filmList.size == 0
                && detailData.speciesList.size == 0 && isHomeWorldEmpty(detailData.homeWorldModel)
            ) {
                emit(DataState.error<FullDetailModel>(Constants.EMPTY_STRING))

            } else {
                emit(DataState.success(detailData))
            }

        }
    }



    @FlowPreview
    private suspend fun getFilmList(films: List<String>?): MutableList<FilmModel> {
        val list: MutableList<FilmModel> = mutableListOf()
        val ids = films?.let { it.map { Utils.parseId(it) } }
        ids?.let {
            it.asFlow().buffer().flowOn(defaultDispatcher).flatMapMerge {
                // println("film model $it")
                getFilmsUseCase.execute(it)
            }.onEach {
                if (it.status == Status.ERROR) {
                    isError = true
                }
            }.filter { it.status == Status.SUCCESS }.collect {
                it.data?.let { list.add(it.convertTo()) }
            }
        }
        // Log.e(TAG, "job2 ends")
        return list
    }

    @FlowPreview
    private suspend fun getSpeciesList(species: List<String>?): MutableList<SpeciesModel> {
        val list: MutableList<SpeciesModel> = mutableListOf()
        val ids = species?.let { it.map { Utils.parseId(it) } }
        ids?.let {
            it.asFlow().buffer().flowOn(defaultDispatcher).flatMapMerge {
                println("species model $it")
                getSpeciesUseCase.execute(it)
            }.onEach {
                if (it.status == Status.ERROR) {
                    isError = true
                }
            }.filter { it.status == Status.SUCCESS }.collect {
                it.data?.let { list.add(it.convertTo()) }
                //println("film model $it")
            }
        }
        // Log.e(TAG, "job3 ends")
        return list
    }

    private suspend fun getHomeWorldModel(link: String): HomeWorldModel {
        Utils.parseId(link).let { id ->
            val population = getPopulationUseCase.execute(id)
            when (population.status) {
                Status.SUCCESS -> population.data?.let {
                    //Log.e(TAG, "job1 ends")
                    return it.convertTo()
                }
                else -> isError = true
            }

        }
        return HomeWorldModel()
    }

    fun setCharacterInfo(info: CharacterModel) {
        _characterModel.value = info
    }
}