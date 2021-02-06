package com.codeartist.trivagochallenge.detail.presentation.viewmodel

import androidx.hilt.Assisted
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.*
import com.codeartist.trivagochallenge.common.Status
import com.codeartist.trivagochallenge.detail.domain.usecases.GetFilmsUseCase
import com.codeartist.trivagochallenge.detail.domain.usecases.GetPopulationUseCase
import com.codeartist.trivagochallenge.detail.domain.usecases.GetSpeciesUseCase
import com.codeartist.trivagochallenge.detail.presentation.uimodel.FilmModel
import com.codeartist.trivagochallenge.detail.presentation.uimodel.PlanetModel
import com.codeartist.trivagochallenge.detail.presentation.uimodel.SpeciesModel
import com.codeartist.trivagochallenge.search.presentation.uimodel.CharacterModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.joinAll
import kotlinx.coroutines.launch

class DetailViewModel @ViewModelInject constructor(
    private val getFilmsUseCase: GetFilmsUseCase,
    private val getSpeciesUseCase: GetSpeciesUseCase,
    private val getPopulationUseCase: GetPopulationUseCase,
    @Assisted private val savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val TAG = "DetailViewModel"
    private val _filmList = MutableLiveData<MutableList<FilmModel>>(mutableListOf())
    val filmList: LiveData<MutableList<FilmModel>> = _filmList
    private val _speciesList = MutableLiveData<MutableList<SpeciesModel>>(mutableListOf())
    val speciseList: LiveData<MutableList<SpeciesModel>> = _speciesList
    private val _population = MutableLiveData<PlanetModel>()
    val population: LiveData<PlanetModel> = _population
    private val _isError = MutableLiveData(false)
    val isError: LiveData<Boolean> = _isError
    private val _isLoading: MutableLiveData<Boolean> = MutableLiveData()
    val isLoading: LiveData<Boolean> = _isLoading


    private suspend fun getFilm(link: String?) {
        parseId(link)?.let {
            val filmInfo = getFilmsUseCase.execute(it)
            when (filmInfo.status) {
                Status.SUCCESS -> filmInfo.data?.let {
                    val list = _filmList.value
                    list?.add(it)
                    _filmList.postValue(list)
                }
                Status.ERROR -> _isError.postValue(true)
                else -> return
            }

            //Log.e(TAG, "job1 ends")
        }
    }

    private suspend fun getFilms(films: List<String>?) {
        films?.let {
            it.forEach { getFilm(it) }
        }
    }


    private suspend fun getSpecie(link: String?) {
        parseId(link)?.let {
            val speciesInfo = getSpeciesUseCase.execute(it)
            when (speciesInfo.status) {
                Status.SUCCESS -> speciesInfo.data?.let {
                    _isError.postValue(false)
                    val list = _speciesList.value
                    list?.add(it)
                    _speciesList.postValue(list)
                }
                Status.ERROR -> _isError.postValue(true)
                else -> return
                //Log.e(TAG, "job2 ends")
            }
        }
    }

    private suspend fun getSpecies(species: List<String>?) {
        species?.let {
            it.forEach { getSpecie(it) }
        }
    }

    private suspend fun getPopulation(link: String?) {
        parseId(link)?.let {
            val population = getPopulationUseCase.execute(it)
            when (population.status) {
                Status.SUCCESS -> population.data?.let {
                    _isError.postValue(false)
                    _population.postValue(population.data)
                }
                Status.ERROR -> _isError.postValue(true)
                else -> return

            }
            // Log.e(TAG, "job3 ends")
        }

    }


    fun collectionCharacterDetail(info: CharacterModel) {
        val jobs: MutableList<Job> = mutableListOf()
        viewModelScope.launch {
            _isLoading.value = true
            jobs.add(viewModelScope.launch(Dispatchers.IO) { getFilms(info.films) })
            jobs.add(viewModelScope.launch(Dispatchers.IO) { getSpecies(info.species) })
            jobs.add(viewModelScope.launch(Dispatchers.IO) { getPopulation(info.homeworld) })
            jobs.joinAll()
            _isLoading.value = false
        }
    }


    private fun parseId(link: String?): Int? {
        val url = link?.split("/")
        return url?.get(url.size - 2)?.toInt()
    }
}