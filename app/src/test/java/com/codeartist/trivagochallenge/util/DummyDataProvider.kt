package com.codeartist.trivagochallenge.util

import com.codeartist.trivagochallenge.search.data.entity.ResultNetworkEntity
import com.codeartist.trivagochallenge.search.data.entity.SearchNetworkEntity
import com.codeartist.trivagochallenge.search.presentation.uimodel.CharacterModel
import com.codeartist.trivagochallenge.search.presentation.uimodel.CharacterModelTest

object DummyDataProvider {
    fun singleResultDataProvider(): ResultNetworkEntity {
        var name: String? = "Adi Gallia"
        var height: String? = "184"
        var birthYear: String? = "unknown"
        var homeworld: String? = ""
        var films: List<String>? = listOf(
            "http://swapi.dev/api/films/4/",
            "http://swapi.dev/api/films/6/"
        )
        var species: List<String>? = listOf("http://swapi.dev/api/species/23/")
        return ResultNetworkEntity(name, height, birthYear, homeworld, films, species)
    }

    fun singleCharacterDataProvider(): CharacterModel {
        var name: String = "Adi Gallia"
        var height: String = "184"
        var birthYear: String = "unknown"
        var homeworld: String = ""
        var films: List<String> = listOf(
            "http://swapi.dev/api/films/4/",
            "http://swapi.dev/api/films/6/"
        )
        var species: List<String> = listOf("http://swapi.dev/api/species/23/")
        return CharacterModel(name, height, birthYear, homeworld, films, species)
    }

    fun nullResultDataProvider(): ResultNetworkEntity {
        return ResultNetworkEntity(null, null, null,
            null, null, null)
    }

    fun searchResultListWithTwoItemsProvider():List<ResultNetworkEntity>{
        val list:MutableList<ResultNetworkEntity> = mutableListOf()
        var name: String? = "Ki-Adi-Mundi"
        var height: String? = "198"
        var birthYear: String? = "92BBY"
        var homeworld: String? = "http://swapi.dev/api/planets/43/"
        var films: List<String>? = listOf(
            "http://swapi.dev/api/films/4/",
            "http://swapi.dev/api/films/5/",
            "http://swapi.dev/api/films/6/"
        )
        var species: List<String>? = listOf( "http://swapi.dev/api/species/20/")
        list.add(singleResultDataProvider())
        list.add(ResultNetworkEntity(name, height, birthYear, homeworld, films, species))
        return list
    }
    fun searchResultListWithOneItemsProvider():List<ResultNetworkEntity>{
        return mutableListOf(singleResultDataProvider())
    }

    fun searchNetworkEntityProvider(): SearchNetworkEntity{
        return SearchNetworkEntity(searchResultListWithOneItemsProvider())
    }
}