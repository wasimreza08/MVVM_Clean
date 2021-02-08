package com.codeartist.trivagochallenge.detail.presentation.viewmodel

import android.util.Log
import androidx.annotation.VisibleForTesting
import androidx.hilt.Assisted
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.*
import com.codeartist.trivagochallenge.common.DataState
import com.codeartist.trivagochallenge.common.Status
import com.codeartist.trivagochallenge.detail.domain.usecases.GetFilmsUseCase
import com.codeartist.trivagochallenge.detail.domain.usecases.GetPopulationUseCase
import com.codeartist.trivagochallenge.detail.domain.usecases.GetSpeciesUseCase
import com.codeartist.trivagochallenge.detail.presentation.uimodel.FilmModel
import com.codeartist.trivagochallenge.detail.presentation.uimodel.FullDetailModel
import com.codeartist.trivagochallenge.detail.presentation.uimodel.PlanetModel
import com.codeartist.trivagochallenge.detail.presentation.uimodel.SpeciesModel
import com.codeartist.trivagochallenge.search.presentation.uimodel.CharacterModel
import kotlinx.coroutines.*

class DetailViewModel @ViewModelInject constructor(
    private val getFilmsUseCase: GetFilmsUseCase,
    private val getSpeciesUseCase: GetSpeciesUseCase,
    private val getPopulationUseCase: GetPopulationUseCase,
    @Assisted private val savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val TAG = "DetailViewModel"
    var defaultDispatcher: CoroutineDispatcher = Dispatchers.IO
        @VisibleForTesting set
   // private val _filmList = MutableLiveData<MutableList<FilmModel>>(mutableListOf())
    //val filmList: LiveData<MutableList<FilmModel>> = _filmList
   // private val _speciesList = MutableLiveData<MutableList<SpeciesModel>>(mutableListOf())
   // val speciseList: LiveData<MutableList<SpeciesModel>> = _speciesList
   // private val _population = MutableLiveData<PlanetModel>()
    //val population: LiveData<PlanetModel> = _population
    private val _isError = MutableLiveData(false)
    val isError: LiveData<Boolean> = _isError
    private val errorCollector: MutableList<Boolean> = mutableListOf()

    private val _characterModel: MutableLiveData<CharacterModel> = MutableLiveData()
    val detailInfo: LiveData<DataState<FullDetailModel>> = _characterModel.switchMap {
        liveData(context = viewModelScope.coroutineContext + defaultDispatcher) {
            emit(DataState.loading())
            val planetInfo =
                viewModelScope.async(Dispatchers.IO) { getPopulation(it.homeWorld) }
            val filmInfo = viewModelScope.async(Dispatchers.IO) { getFilms(it.films) }
            val speciesInfo = viewModelScope.async(Dispatchers.IO) { getSpecies(it.species) }
            errorIdentifier()
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

    private fun errorIdentifier() {
        _isError.postValue(errorCollector.contains { true })
    }


    private suspend fun getFilms(films: List<String>?): MutableList<FilmModel> {
        films?.let {
            val list = it.map {
                parseId(it)?.let {
                    viewModelScope.async(Dispatchers.IO) {
                        Log.e("collection films", it.toString())
                        getFilmsUseCase.execute(it)
                        //Log.e("films collection", "it")
                    }
                }
            }.filterNotNull().onEach {
                if (it.await().status == Status.ERROR) {
                    errorCollector.add(true)
                }
            }.map { it.await().data }
                .filterNotNull()
            return list.toMutableList()
        }
        return emptyList<FilmModel>().toMutableList()
    }

    private suspend fun getSpecies(speciesUrl: List<String>?): MutableList<SpeciesModel> {
        speciesUrl?.let {
            val list = it.map {
                parseId(it)?.let {
                    viewModelScope.async(Dispatchers.IO) {
                        Log.e("collection species", it.toString())
                        getSpeciesUseCase.execute(it)
                        //Log.e("films collection", "it")
                    }
                }
            }.filterNotNull().onEach {
                if (it.await().status == Status.ERROR) {
                    errorCollector.add(true)
                    /*  _isError.postValue(
                          true
                      )*/
                }
            }.map { it.await().data }
                .filterNotNull()
            Log.e("species list", list.toString())
            //_speciesList.postValue(list.toMutableList())
            return list.toMutableList()
        }
        return emptyList<SpeciesModel>().toMutableList()
    }


    /* private suspend fun getSpecie(link: String?) {
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
     }*/

    /*  private suspend fun getSpecies(species: List<String>?) {
          species?.let {
              it.forEach { getSpecie(it) }
          }
      }*/

    private suspend fun getPopulation(link: String?): PlanetModel {
        parseId(link)?.let { id ->
            val population = getPopulationUseCase.execute(id)
            when (population.status) {
                Status.SUCCESS -> population.data?.let {
                    //_population.postValue(population.data)
                    Log.e("collection population", id.toString())
                    return it
                }
                Status.ERROR -> errorCollector.add(true)
                else -> {
                }

            }
            // Log.e(TAG, "job3 ends")
        }
        return PlanetModel()

    }

    fun setCharacterInfo(info: CharacterModel) {
        _characterModel.value = info
    }


    /*fun collectCharacterDetail(info: CharacterModel) {
        val jobs: MutableList<Job> = mutableListOf()
        viewModelScope.launch {
            jobs.add(viewModelScope.launch(Dispatchers.IO) { getFilms(info.films) })
            jobs.add(viewModelScope.launch(Dispatchers.IO) { getSpecies(info.species) })
            jobs.add(viewModelScope.launch(Dispatchers.IO) { getPopulation(info.homeWorld) })
            jobs.joinAll()
        }
    }*/


    private fun parseId(link: String?): Int? {
        val url = link?.split("/")
        return url?.get(url.size - 2)?.toInt()
    }
}