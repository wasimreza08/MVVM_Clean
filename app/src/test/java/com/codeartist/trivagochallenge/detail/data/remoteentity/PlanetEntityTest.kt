package com.codeartist.trivagochallenge.detail.data.remoteentity

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
        val entity = DummyDataProvider.provideSinglePlanetEntity()
        assertThat(entity.name, CoreMatchers.equalTo(name))
        assertThat(entity.population, CoreMatchers.equalTo(population))

    }

    @Test
    fun `test PlanetEntity with null items`() {
        val entity = PlanetEntity(null, null)
        assertNull(entity.name)
        assertNull(entity.population)
    }

    @Test
    fun `test convertTo function with valid PlanetEntity return valid model`() {
        val entity = DummyDataProvider.provideSinglePlanetEntity()
        val model = entity.convertTo()
        assertThat(model.name,
          equalTo(DummyDataProvider.provideSinglePlanetEntity().name)
        )
        assertThat(
            model.population,
            equalTo(DummyDataProvider.provideSinglePlanetEntity().population)
        )
    }
    @Test
    fun `test convertTo function with null items PlanetEntity return empty items model`() {
        val entity = PlanetEntity(null, null)
        val model = entity.convertTo()

        assertThat(model.name, CoreMatchers.equalTo(""))
        assertThat(
            model.population,
            CoreMatchers.equalTo("")
        )
    }
}