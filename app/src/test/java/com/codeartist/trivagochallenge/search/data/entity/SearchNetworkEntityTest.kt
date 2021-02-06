package com.codeartist.trivagochallenge.search.data.entity

import com.codeartist.trivagochallenge.util.DummyDataProvider
import org.hamcrest.CoreMatchers.equalTo
import org.hamcrest.MatcherAssert.assertThat
import org.junit.Test

class SearchNetworkEntityTest {
    @Test
    fun `Given valid list to SearchNetworkEntity return valid list`() {
        val entity = SearchNetworkEntity(DummyDataProvider.searchResultListWithTwoItemsProvider())
        assertThat(
            entity.results,
            equalTo(DummyDataProvider.searchResultListWithTwoItemsProvider())
        )
        assertThat(
            entity.results?.size,
            equalTo(2)
        )
    }

    @Test
    fun `Given null to SearchNetworkEntity return null`() {
        val entity = SearchNetworkEntity(null)
        assertThat(
            entity.results,
            equalTo(null)
        )
    }

    @Test
    fun `Given valid list to ConvertToFunction return Valid list`() {
        val entity = SearchNetworkEntity(DummyDataProvider.searchResultListWithOneItemsProvider())
        val searchModel = entity.convertTo()
        assertThat(
            searchModel.get(0).name,
            equalTo(DummyDataProvider.singleResultDataProvider().name)
        )
        assertThat(
            searchModel.get(0).height,
            equalTo(DummyDataProvider.singleResultDataProvider().height)
        )
        assertThat(
            searchModel.get(0).birthYear,
            equalTo(DummyDataProvider.singleResultDataProvider().birthYear)
        )
        assertThat(
            searchModel.get(0).homeworld,
            equalTo(DummyDataProvider.singleResultDataProvider().homeworld)
        )
        assertThat(
            searchModel.get(0).films,
            equalTo(DummyDataProvider.singleResultDataProvider().films)
        )
        assertThat(searchModel.get(0).films?.size, equalTo(2))
        assertThat(
            searchModel.get(0).species,
            equalTo(DummyDataProvider.singleResultDataProvider().species)
        )
        assertThat(searchModel.get(0).species?.size, equalTo(1))

    }
    @Test
    fun `Given list of null data to ConvertToFunction return empty list of string`() {
        val entity = SearchNetworkEntity(mutableListOf(DummyDataProvider.nullResultDataProvider()))
        val searchList = entity.convertTo()
        assertThat(
            searchList.get(0).name,
            equalTo("")
        )
        assertThat(
            searchList.get(0).height,
            equalTo("")
        )
        assertThat(
            searchList.get(0).birthYear,
            equalTo("")
        )
        assertThat(
            searchList.get(0).homeworld,
            equalTo("")
        )
        assertThat(
            searchList.get(0).films,
            equalTo(emptyList())
        )
        assertThat(searchList.get(0).films.size, equalTo(0))
        assertThat(
            searchList.get(0).species,
            equalTo(emptyList())
        )
        assertThat(searchList.get(0).species.size, equalTo(0))

    }

    @Test
    fun `Given null to ConvertToFunction return empty List`(){
        val entity = SearchNetworkEntity(null)
        val searchList = entity.convertTo()
        assertThat(searchList, equalTo(emptyList()))
    }
}