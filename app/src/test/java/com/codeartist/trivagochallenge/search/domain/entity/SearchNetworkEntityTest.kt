package com.codeartist.trivagochallenge.search.domain.entity

import com.codeartist.trivagochallenge.common.utils.Utils
import com.codeartist.trivagochallenge.util.DummyDataProvider
import org.hamcrest.CoreMatchers.equalTo
import org.hamcrest.MatcherAssert.assertThat
import org.junit.Test

class SearchNetworkEntityTest {
    @Test
    fun `test SearchNetworkEntity with non null list`() {
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
    fun `test SearchNetworkEntity with null return null`() {
        val entity = SearchNetworkEntity(null)
        assertThat(
            entity.results,
            equalTo(null)
        )
    }

    @Test
    fun `test convertTo with searchNetworkEntity list return list of searchModel`() {
        val entity = SearchNetworkEntity(DummyDataProvider.searchResultListWithOneItemsProvider())
        val searchModel = entity.convertTo()
        assertThat(
            searchModel.get(0).name,
            equalTo(DummyDataProvider.singleResultDataProvider().name)
        )
        assertThat(
            searchModel.get(0).height,
            equalTo(DummyDataProvider.singleResultDataProvider().height?.let {
                Utils.cmToFeetInches(
                    it
                )
            })
        )
        assertThat(
            searchModel.get(0).birthYear,
            equalTo(DummyDataProvider.singleResultDataProvider().birthYear)
        )
        assertThat(
            searchModel.get(0).homeWorld,
            equalTo(DummyDataProvider.singleResultDataProvider().homeworld)
        )
        assertThat(
            searchModel.get(0).films,
            equalTo(DummyDataProvider.singleResultDataProvider().films)
        )
        assertThat(searchModel.get(0).films.size, equalTo(2))
        assertThat(
            searchModel.get(0).species,
            equalTo(DummyDataProvider.singleResultDataProvider().species)
        )
        assertThat(searchModel.get(0).species.size, equalTo(1))

    }

    @Test
    fun `test convertTo with nonEmpty list having unknown height return searchModel`() {
        val entity =
            SearchNetworkEntity(mutableListOf(DummyDataProvider.singleResultProviderWithUnknownHeight()))
        val searchModel = entity.convertTo()
        assertThat(
            searchModel.get(0).name,
            equalTo(entity.results?.get(0)?.name)
        )
        assertThat(
            searchModel.get(0).height,
            equalTo(entity.results?.get(0)?.height?.let {
                Utils.cmToFeetInches(
                    it
                )
            })
        )
        assertThat(
            searchModel.get(0).birthYear,
            equalTo(DummyDataProvider.singleResultDataProvider().birthYear)
        )
        assertThat(
            searchModel.get(0).homeWorld,
            equalTo(DummyDataProvider.singleResultDataProvider().homeworld)
        )
        assertThat(
            searchModel.get(0).films,
            equalTo(DummyDataProvider.singleResultDataProvider().films)
        )
        assertThat(searchModel.get(0).films.size, equalTo(2))
        assertThat(
            searchModel.get(0).species,
            equalTo(DummyDataProvider.singleResultDataProvider().species)
        )
        assertThat(searchModel.get(0).species.size, equalTo(1))

    }

    @Test
    fun `test convertTo with list of null return list of empty string`() {
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
            searchList.get(0).homeWorld,
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
    fun `test convertTo with null return empty List`() {
        val entity = SearchNetworkEntity(null)
        val searchList = entity.convertTo()
        assertThat(searchList, equalTo(emptyList()))
    }
}