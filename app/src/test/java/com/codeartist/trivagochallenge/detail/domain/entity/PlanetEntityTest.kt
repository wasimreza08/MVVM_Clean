package com.codeartist.trivagochallenge.detail.domain.entity

import com.codeartist.trivagochallenge.common.utils.Constants
import com.codeartist.trivagochallenge.util.DummyDataProvider
import org.hamcrest.CoreMatchers
import org.hamcrest.CoreMatchers.equalTo
import org.junit.Assert.assertNull
import org.junit.Assert.assertThat
import org.junit.Test

class PlanetEntityTest{
    val name = "Tatooine"
    val population = "200000"

    @Test
    fun `test PlanetEntity with valid entity`() {
        val entity = DummyDataProvider.provideSingleHomeWorldEntity()
        assertThat(entity.name, CoreMatchers.equalTo(name))
        assertThat(entity.population, CoreMatchers.equalTo(population))

    }

    @Test
    fun `test PlanetEntity with null items`() {
        val entity = HomeWorldEntity(null, null)
        assertNull(entity.name)
        assertNull(entity.population)
    }

    @Test
    fun `test convertTo function with valid PlanetEntity return valid model`() {
        val entity = DummyDataProvider.provideSingleHomeWorldEntity()
        val model = entity.convertTo()
        assertThat(model.name,
          equalTo(DummyDataProvider.provideSingleHomeWorldEntity().name)
        )
        assertThat(
            model.population,
            equalTo(DummyDataProvider.provideSingleHomeWorldEntity().population)
        )
    }
    @Test
    fun `test convertTo function with null items PlanetEntity return empty items model`() {
        val entity = HomeWorldEntity(null, null)
        val model = entity.convertTo()

        assertThat(model.name, CoreMatchers.equalTo(Constants.EMPTY_STRING))
        assertThat(
            model.population,
            CoreMatchers.equalTo(Constants.EMPTY_STRING)
        )
    }
}