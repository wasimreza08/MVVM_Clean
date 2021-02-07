package com.codeartist.trivagochallenge.search.presentation.uimodel

import com.codeartist.trivagochallenge.util.DummyDataProvider
import org.hamcrest.CoreMatchers
import org.hamcrest.CoreMatchers.equalTo
import org.hamcrest.MatcherAssert
import org.junit.Assert.*
import org.junit.Test

class CharacterModelTest {
    var name: String = "Adi Gallia"
    var height: String = "184"
    var birthYear: String = "unknown"
    var homeworld: String = ""
    var films: List<String> = listOf(
        "http://swapi.dev/api/films/4/",
        "http://swapi.dev/api/films/6/"
    )
    var species: List<String> = listOf("http://swapi.dev/api/species/23/")

    @Test
    fun characterModelTestWithData() {
        val result = DummyDataProvider.singleCharacterDataProvider()
        MatcherAssert.assertThat(result.name, equalTo(name))
        MatcherAssert.assertThat(result.height, equalTo(height))
        MatcherAssert.assertThat(result.birthYear, equalTo(birthYear))
        MatcherAssert.assertThat(result.homeworld, equalTo(homeworld))
        MatcherAssert.assertThat(result.films, equalTo(films))
        MatcherAssert.assertThat(result.films.size, equalTo(2))
        MatcherAssert.assertThat(result.species, equalTo(species))
        MatcherAssert.assertThat(result.species.size, equalTo(1))
    }
}