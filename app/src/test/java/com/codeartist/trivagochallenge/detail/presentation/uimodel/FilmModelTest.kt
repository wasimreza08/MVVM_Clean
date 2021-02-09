package com.codeartist.trivagochallenge.detail.presentation.uimodel

import com.codeartist.trivagochallenge.util.DummyDataProvider
import org.hamcrest.CoreMatchers.equalTo
import org.junit.Assert.assertThat
import org.junit.Test

class FilmModelTest {
    val title = "A New Hope"
    val description = "It is a dark time for the\\r\\nRebellion. Although the Death\\r\\nStar " +
            "has been destroyed,\\r\\nImperial troops have driven the\\r\\nRebel forces from their hidden\\r\\nbase and pursued them across\\r\\nthe galaxy.\\r\\n\\r\\nEvading the dreaded Imperial\\r\\nStarfleet, a group of freedom\\r\\nfighters led by Luke Skywalker\\r\\nhas established a new secret\\r\\nbase on the remote ice world\\r\\nof Hoth.\\r\\n\\r\\nThe evil lord Darth Vader,\\r\\nobsessed with finding young\\r\\nSkywalker, has dispatched\\r\\nthousands of remote probes into\\r\\nthe far reaches of space...."

    @Test
    fun `test FilmModel with nonEmpty data`() {
        val model = DummyDataProvider.filmModelProviderWithSingleItem()
        assertThat(model.title, equalTo(title))
        assertThat(model.description, equalTo(description))

    }

    @Test
    fun `test FilmModel with empty items`() {
        val model = FilmModel("", "")
        assertThat(model.title, equalTo(""))
        assertThat(model.description, equalTo(""))
    }
}