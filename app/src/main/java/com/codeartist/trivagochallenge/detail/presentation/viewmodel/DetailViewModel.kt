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
    private val _isError = MutableLiveData<Boolean>(false)
    val isError: LiveData<Boolean> = _isError
    private val _isLoading = MutableLiveData<Boolean>(false)
    val isLoading: LiveData<Boolean> = _isLoading


    private fun getFilm(link: String?) {
        viewModelScope.launch(Dispatchers.IO) {
            parseId(link)?.let {
                val filmInfo = getFilmsUseCase.execute(it)
                //Log.e(TAG + "films:", it.toString())
                if (filmInfo.status == Status.SUCCESS) {
                    filmInfo.data?.let {
                        val list = _filmList.value
                        list?.add(it)
                        _filmList.postValue(list)
                    }
                }
            }
        }
    }

    private fun getFilms(films: List<String>?) {
        films?.let {
           it.map { getFilm(it) }
        }
    }


    private fun getSpecie(link: String?) {
        viewModelScope.launch(Dispatchers.IO) {
            parseId(link)?.let {
                val speciesInfo = getSpeciesUseCase.execute(it)
                if (speciesInfo.status == Status.SUCCESS) {
                    _isError.postValue(false)
                    speciesInfo.data?.let {
                        val list = _speciesList.value
                        list?.add(it)
                        _speciesList.postValue(list)
                    }
                } else if(speciesInfo.status == Status.ERROR){
                    _isError.postValue(true)
                }
            }
        }
    }

    private fun getSpecies(species: List<String>?) {
        species?.let {
            it.map { getSpecie(it) }
        }
    }

    private fun getPopulation(link: String?) {
        viewModelScope.launch(Dispatchers.IO) {
            parseId(link)?.let {
                val population = getPopulationUseCase.execute(it)
                if (population.status == Status.SUCCESS) {
                    population.data?.let {
                        _isError.postValue(false)
                        _population.postValue(population.data)
                    }
                } else if (population.status == Status.ERROR){
                    _isError.postValue(true)
                }
            }
        }
    }


    fun collectionCharacterDetail(info: CharacterModel) {
        viewModelScope.launch {
            _isLoading.value = true
            val job1 = viewModelScope.launch { getFilms(info.films) }
            val job2 = viewModelScope.launch { getSpecies(info.species) }
            val job3 = viewModelScope.launch { getPopulation(info.homeworld) }
            job1.join()
            job2.join()
            job3.join()
            _isLoading.value = false
        }

    }

    private fun parseId(link: String?): Int? {
        val url = link?.split("/")
        return url?.get(url.size - 2)?.toInt()
    }
}