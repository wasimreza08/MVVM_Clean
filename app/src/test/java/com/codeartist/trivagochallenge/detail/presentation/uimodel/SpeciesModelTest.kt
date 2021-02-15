package com.codeartist.trivagochallenge.detail.presentation.uimodel

import com.codeartist.trivagochallenge.common.utils.Constants
import com.codeartist.trivagochallenge.util.DummyDataProvider
import org.hamcrest.CoreMatchers
import org.junit.Assert.*
import org.junit.Test

class SpeciesModelTest{
    val name = "Droid"
    val language = "n/a"
    @Test
    fun `test SpeciesModel with nonEmpty data`() {
        val model = DummyDataProvider.provideSingleSpeciesModel()
        assertThat(model.name, CoreMatchers.equalTo(name))
        assertThat(model.language, CoreMatchers.equalTo(language))

    }

    @Test
    fun `test SpeciesModel with empty items`() {
        val model = SpeciesModel(Constants.EMPTY_STRING, Constants.EMPTY_STRING)
        assertThat(model.name, CoreMatchers.equalTo(Constants.EMPTY_STRING))
        assertThat(model.language, CoreMatchers.equalTo(Constants.EMPTY_STRING))
    }
}