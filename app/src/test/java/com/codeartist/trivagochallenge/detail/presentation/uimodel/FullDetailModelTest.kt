package com.codeartist.trivagochallenge.detail.presentation.uimodel

import com.codeartist.trivagochallenge.util.DummyDataProvider
import org.hamcrest.CoreMatchers.equalTo
import org.hamcrest.MatcherAssert.assertThat
import org.junit.Test

class FullDetailModelTest {
    val films = mutableListOf(DummyDataProvider.filmModelProviderWithSingleItem())
    val species = mutableListOf(DummyDataProvider.provideSingleSpeciesModel())
    val homeWorldModel = HomeWorldModel(name = "Naboo", population = "450000000")

    @Test
    fun `test fullDetailModel with non empty data return nonEmpty model`() {
        val detailModel = FullDetailModel(films, species, homeWorldModel)
        assertThat(detailModel.filmList.get(0).title, equalTo(films.get(0).title))
        assertThat(detailModel.filmList.get(0).description, equalTo(films.get(0).description))
        assertThat(detailModel.filmList.get(0).url, equalTo(films.get(0).url))

        assertThat(detailModel.speciesList.get(0).name, equalTo(species.get(0).name))
        assertThat(detailModel.speciesList.get(0).language, equalTo(species.get(0).language))

        assertThat(detailModel.homeWorldModel.name, equalTo(homeWorldModel.name))
        assertThat(detailModel.homeWorldModel.population, equalTo(homeWorldModel.population))
    }
}