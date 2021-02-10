package com.codeartist.trivagochallenge.detail.presentation.viewmodel

import android.util.Log
import androidx.annotation.VisibleForTesting
import androidx.hilt.Assisted
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.*
import com.codeartist.trivagochallenge.common.utils.DataState
import com.codeartist.trivagochallenge.common.utils.Status
import com.codeartist.trivagochallenge.common.utils.Utils
import com.codeartist.trivagochallenge.detail.domain.usecases.GetFilmsUseCase
import com.codeartist.trivagochallenge.detail.domain.usecases.GetPopulationUseCase
import com.codeartist.trivagochallenge.detail.domain.usecases.GetSpeciesUseCase
import com.codeartist.trivagochallenge.detail.presentation.uimodel.FilmModel
import com.codeartist.trivagochallenge.detail.presentation.uimodel.FullDetailModel
import com.codeartist.trivagochallenge.detail.presentation.uimodel.PlanetModel
import com.codeartist.trivagochallenge.detail.presentation.uimodel.SpeciesModel
import com.codeartist.trivagochallenge.search.presentation.uimodel.CharacterModel
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.*

class DetailViewModel @ViewModelInject constructor(
    private val getFilmsUseCase: GetFilmsUseCase,
    private val getSpeciesUseCase: GetSpeciesUseCase,
    private val getPopulationUseCase: GetPopulationUseCase,
    @Assisted private val savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val TAG = "DetailViewModel"
    var defaultDispatcher: CoroutineDispatcher = Dispatchers.IO
        @VisibleForTesting set
    private val _isError = MutableLiveData(false)
    val isError: LiveData<Boolean> = _isError

    private val _characterModel: MutableLiveData<CharacterModel> = MutableLiveData()
    val detailInfo: LiveData<DataState<FullDetailModel>> = _characterModel.switchMap {
        liveData(context = viewModelScope.coroutineContext + defaultDispatcher) {
            emit(DataState.loading())
            val planetInfo =
                viewModelScope.async(defaultDispatcher) { getPopulation(it.homeWorld) }
            val filmInfo = viewModelScope.async(defaultDispatcher) {// getFilms(it.films)
                getFilmList(it.films)
            }
            val speciesInfo = viewModelScope.async(defaultDispatcher) { getSpeciesList(it.species) }

            val detailData = FullDetailModel(
                filmInfo.await(), speciesInfo.await(),
                planetInfo.await()
            )
            emit(
                DataState.success(
                    detailData
                )
            )
        }
    }


    @FlowPreview
    private suspend fun getFilmList(films: List<String>?): MutableList<FilmModel> {
        val list: MutableList<FilmModel> = mutableListOf()
        val ids = films?.let { it.map { Utils.parseId(it) } }
        getEachFilm(ids).buffer().collect {
            it.let {
                list.add(it)
                //  println("film model ${it.url}")
            }
        }
        return list
    }

    @FlowPreview
    private suspend fun getEachFilm(ids: List<Int>?) = flow {
        ids?.let {
            it.asFlow().buffer().flowOn(defaultDispatcher).flatMapMerge {
                // println("film model $it")
                flow {
                    emit(getFilmsUseCase.execute(it))
                }
            }.onEach {
                if (it.status == Status.ERROR) {
                    _isError.postValue(true)
                }
            }.filter { it.status == Status.SUCCESS }.collect {
                it.data?.let { emit(it) }
            }
        }
    }


    @FlowPreview
    private suspend fun getSpeciesList(specise: List<String>?): MutableList<SpeciesModel> {
        val list: MutableList<SpeciesModel> = mutableListOf()
        val ids = specise?.let { it.map { Utils.parseId(it) } }
        getEachSpecies(ids).buffer().collect {
            it.let {
                list.add(it)
                println("species model ${it}")
            }
        }
        return list
    }

    @FlowPreview
    private suspend fun getEachSpecies(ids: List<Int>?) = flow {
        ids?.let {
            it.asFlow().buffer().flowOn(defaultDispatcher).flatMapMerge {
                println("species model $it")
                flow {
                    emit(getSpeciesUseCase.execute(it))
                }
            }.onEach {
                if (it.status == Status.ERROR) {
                    _isError.postValue(true)
                }
            }.filter { it.status == Status.SUCCESS }.collect {
                it.data?.let { emit(it) }
                //println("film model $it")
            }
        }
    }

    private suspend fun getPopulation(link: String): PlanetModel {
        Utils.parseId(link).let { id ->
            val population = getPopulationUseCase.execute(id)
            when (population.status) {
                Status.SUCCESS -> population.data?.let {
                    //_population.postValue(population.data)
                    Log.e("collection population", id.toString())
                    return it
                }
                else -> _isError.postValue(true)

            }
            // Log.e(TAG, "job3 ends")
        }
        return PlanetModel()

    }

    fun setCharacterInfo(info: CharacterModel) {
        _characterModel.value = info
    }
}