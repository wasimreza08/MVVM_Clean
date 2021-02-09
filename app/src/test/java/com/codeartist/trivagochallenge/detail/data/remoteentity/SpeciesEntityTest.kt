package com.codeartist.trivagochallenge.detail.data.remoteentity

import com.codeartist.trivagochallenge.util.DummyDataProvider
import org.hamcrest.CoreMatchers.equalTo
import org.junit.Assert.assertNull
import org.junit.Assert.assertThat
import org.junit.Test

class SpeciesEntityTest{
    val name = "Droid"
    val language = "n/a"

    @Test
    fun `test Species with valid entity`() {
        val entity = DummyDataProvider.provideSingleSpeciesEntity()
        assertThat(entity.name, equalTo(name))
        assertThat(entity.language, equalTo(language))

    }

    @Test
    fun `test SpeciesEntity with null items`() {
        val entity = SpeciesEntity(null, null)
        assertNull(entity.name)
        assertNull(entity.language)
    }

    @Test
    fun `test convertTo function with valid SpeciesEntity return valid model`() {
        val entity = DummyDataProvider.provideSingleSpeciesEntity()
        val model = entity.convertTo()
        assertThat(model.name,
            equalTo(DummyDataProvider.provideSingleSpeciesEntity().name)
        )
        assertThat(
            model.language,
            equalTo(DummyDataProvider.provideSingleSpeciesEntity().language)
        )
    }
    @Test
    fun `test convertTo function with null items SpeciesEntity return empty items model`() {
        val entity = SpeciesEntity(null, null)
        val model = entity.convertTo()

        assertThat(model.name, equalTo(""))
        assertThat(
            model.language,
            equalTo("")
        )
    }
}