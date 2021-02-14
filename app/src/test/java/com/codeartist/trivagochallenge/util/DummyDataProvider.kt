package com.codeartist.trivagochallenge.util

import com.codeartist.practicetest.data.remoteentity.FilmEntity
import com.codeartist.trivagochallenge.detail.domain.entity.HomeWorldEntity
import com.codeartist.trivagochallenge.detail.domain.entity.SpeciesEntity
import com.codeartist.trivagochallenge.detail.presentation.uimodel.FilmModel
import com.codeartist.trivagochallenge.detail.presentation.uimodel.HomeWorldModel
import com.codeartist.trivagochallenge.detail.presentation.uimodel.SpeciesModel
import com.codeartist.trivagochallenge.search.domain.entity.ResultNetworkEntity
import com.codeartist.trivagochallenge.search.domain.entity.SearchNetworkEntity
import com.codeartist.trivagochallenge.search.presentation.uimodel.CharacterModel

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

    fun singleResultProviderWithUnknownHeight(): ResultNetworkEntity {
        var name: String? = "Adi Gallia"
        var height: String? = "unknown"
        var birthYear: String? = "unknown"
        var homeworld: String? = ""
        var films: List<String>? = listOf(
            "http://swapi.dev/api/films/4/",
            "http://swapi.dev/api/films/6/"
        )
        var species: List<String>? = listOf("http://swapi.dev/api/species/23/")
        return ResultNetworkEntity(name, height, birthYear, homeworld, films, species)
    }

    fun singleCharacterModelProvider(): CharacterModel {
        var name = "Adi Gallia"
        var height = "184"
        var birthYear = "unknown"
        var homeworld = ""
        var films: List<String> = listOf(
            "http://swapi.dev/api/films/4/",
            "http://swapi.dev/api/films/6/"
        )
        var species: List<String> = listOf("http://swapi.dev/api/species/23/")
        return CharacterModel(name, height, birthYear, homeworld, films, species)
    }

    fun nullResultDataProvider(): ResultNetworkEntity {
        return ResultNetworkEntity(
            null, null, null,
            null, null, null
        )
    }

    fun searchResultListWithTwoItemsProvider(): List<ResultNetworkEntity> {
        val list: MutableList<ResultNetworkEntity> = mutableListOf()
        var name: String? = "Ki-Adi-Mundi"
        var height: String? = "198"
        var birthYear: String? = "92BBY"
        var homeworld: String? = "http://swapi.dev/api/planets/43/"
        var films: List<String>? = listOf(
            "http://swapi.dev/api/films/4/",
            "http://swapi.dev/api/films/5/",
            "http://swapi.dev/api/films/6/"
        )
        var species: List<String>? = listOf("http://swapi.dev/api/species/20/")
        list.add(singleResultDataProvider())
        list.add(ResultNetworkEntity(name, height, birthYear, homeworld, films, species))
        return list
    }

    fun searchResultListWithOneItemsProvider(): List<ResultNetworkEntity> {
        return mutableListOf(singleResultDataProvider())
    }

    fun searchNetworkEntityProvider(): SearchNetworkEntity {
        return SearchNetworkEntity(searchResultListWithOneItemsProvider())
    }


    fun filmEntityProviderWithSingleItem(): FilmEntity {
        return FilmEntity(
            title = "A New Hope",
            url = "http://swapi.dev/api/films/1/",
            openingCrawl = "It is a dark time for the\\r\\nRebellion. Although the Death\\r\\nStar " +
                    "has been destroyed,\\r\\nImperial troops have driven the\\r\\nRebel forces from their hidden\\r\\nbase and pursued them across\\r\\nthe galaxy.\\r\\n\\r\\nEvading the dreaded Imperial\\r\\nStarfleet, a group of freedom\\r\\nfighters led by Luke Skywalker\\r\\nhas established a new secret\\r\\nbase on the remote ice world\\r\\nof Hoth.\\r\\n\\r\\nThe evil lord Darth Vader,\\r\\nobsessed with finding young\\r\\nSkywalker, has dispatched\\r\\nthousands of remote probes into\\r\\nthe far reaches of space...."
        )
    }

    fun filmModelProviderWithSingleItem(): FilmModel {
        return FilmModel(
            title = "A New Hope",
            url = "https://swapi.dev/api/films/1/",
            description = "It is a dark time for the\\r\\nRebellion. Although the Death\\r\\nStar " +
                    "has been destroyed,\\r\\nImperial troops have driven the\\r\\nRebel forces from their hidden\\r\\nbase and pursued them across\\r\\nthe galaxy.\\r\\n\\r\\nEvading the dreaded Imperial\\r\\nStarfleet, a group of freedom\\r\\nfighters led by Luke Skywalker\\r\\nhas established a new secret\\r\\nbase on the remote ice world\\r\\nof Hoth.\\r\\n\\r\\nThe evil lord Darth Vader,\\r\\nobsessed with finding young\\r\\nSkywalker, has dispatched\\r\\nthousands of remote probes into\\r\\nthe far reaches of space...."
        )
    }

    fun provideSingleHomeWorldEntity(): HomeWorldEntity {
        return HomeWorldEntity("Tatooine", "200000")
    }

    fun provideSingleSpeciesEntity(): SpeciesEntity {
        return SpeciesEntity(name = "Droid", language = "n/a")
    }

    fun provideSingleSpeciesModel(): SpeciesModel {
        return SpeciesModel(name = "Droid", language = "n/a")
    }
    fun provideSingleHomeWorldModel(): HomeWorldModel {
        return HomeWorldModel("Tatooine", "200000")
    }

    fun characterModelProviderForDetail(): CharacterModel {
        var name = "Luke Skywalker"
        var height = "172"
        var birthYear = "19BBY"
        var homeworld = "http://swapi.dev/api/planets/1/"
        var films: List<String> = listOf("http://swapi.dev/api/films/1/")
        var species: List<String> = listOf("http://swapi.dev/api/species/1/")
        return CharacterModel(name, height, birthYear, homeworld, films, species)
    }

}