package com.codeartist.trivagochallenge.search.data.entity


import com.codeartist.trivagochallenge.util.DummyDataProvider
import org.hamcrest.CoreMatchers.equalTo
import org.hamcrest.MatcherAssert.assertThat
import org.junit.Test

class ResultNetworkEntityTest {
    var name: String? = "Adi Gallia"
    var height: String? = "184"
    var birthYear: String? = "unknown"
    var homeworld: String? = ""
    var films: List<String>? = listOf(
        "http://swapi.dev/api/films/4/",
        "http://swapi.dev/api/films/6/"
    )
    var species: List<String>? = listOf("http://swapi.dev/api/species/23/")

    @Test
    fun resultNetworkEntityTestWithData() {
        val result = DummyDataProvider.singleResultDataProvider()
        assertThat(result.name, equalTo(name))
        assertThat(result.height, equalTo(height))
        assertThat(result.birthYear, equalTo(birthYear))
        assertThat(result.homeworld, equalTo(homeworld))
        assertThat(result.films, equalTo(films))
        assertThat(result.films?.size, equalTo(2))
        assertThat(result.species, equalTo(species))
        assertThat(result.species?.size, equalTo(1))
    }

    @Test
    fun resultNetworkEntityTestWithNull() {
        val result = DummyDataProvider.nullResultDataProvider()
        assertThat(result.name, equalTo(null))
        assertThat(result.height, equalTo(null))
        assertThat(result.birthYear, equalTo(null))
        assertThat(result.homeworld, equalTo(null))
        assertThat(result.films, equalTo(null))
        assertThat(result.species, equalTo(null))
    }
}