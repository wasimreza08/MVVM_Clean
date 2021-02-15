package com.codeartist.trivagochallenge.search.domain.entity


import com.codeartist.trivagochallenge.common.utils.Constants
import com.codeartist.trivagochallenge.util.DummyDataProvider
import junit.framework.Assert.assertNull
import org.hamcrest.CoreMatchers.equalTo
import org.hamcrest.MatcherAssert.assertThat
import org.junit.Test

class ResultNetworkEntityTest {
    var name: String? = "Adi Gallia"
    var height: String? = "184"
    var birthYear: String? = "unknown"
    var homeworld: String? = Constants.EMPTY_STRING
    var films: List<String>? = listOf(
        "http://swapi.dev/api/films/4/",
        "http://swapi.dev/api/films/6/"
    )
    var species: List<String>? = listOf("http://swapi.dev/api/species/23/")

    @Test
    fun `test resultNetworkEntity with Data`() {
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
    fun `test resultNetworkEntity with null`() {
        val result = DummyDataProvider.nullResultDataProvider()
        assertNull(result.name)
        assertNull(result.height)
        assertNull(result.birthYear)
        assertNull(result.homeworld)
        assertNull(result.films, )
        assertNull(result.species)
    }
}