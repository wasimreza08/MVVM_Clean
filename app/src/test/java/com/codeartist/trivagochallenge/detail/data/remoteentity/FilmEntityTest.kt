package com.codeartist.trivagochallenge.detail.data.remoteentity

import com.codeartist.practicetest.data.remoteentity.FilmEntity
import com.codeartist.trivagochallenge.util.DummyDataProvider
import org.hamcrest.CoreMatchers.equalTo
import org.junit.Assert.assertNull
import org.junit.Assert.assertThat
import org.junit.Test

class FilmEntityTest {
    val title = "A New Hope"
    val url = "http://swapi.dev/api/films/1/"
    val openingCrawl = "It is a dark time for the\\r\\nRebellion. Although the Death\\r\\nStar " +
            "has been destroyed,\\r\\nImperial troops have driven the\\r\\nRebel forces from their hidden\\r\\nbase and pursued them across\\r\\nthe galaxy.\\r\\n\\r\\nEvading the dreaded Imperial\\r\\nStarfleet, a group of freedom\\r\\nfighters led by Luke Skywalker\\r\\nhas established a new secret\\r\\nbase on the remote ice world\\r\\nof Hoth.\\r\\n\\r\\nThe evil lord Darth Vader,\\r\\nobsessed with finding young\\r\\nSkywalker, has dispatched\\r\\nthousands of remote probes into\\r\\nthe far reaches of space...."

    @Test
    fun `test FilmEntity with valid entity`() {
        val entity = DummyDataProvider.filmEntityProviderWithSingleItem()
        assertThat(entity.title, equalTo(title))
        assertThat(entity.openingCrawl, equalTo(openingCrawl))
        assertThat(entity.url, equalTo(url))

    }

    @Test
    fun `test FilmEntity with null items`() {
        val entity = FilmEntity(null, null, null)
        assertNull(entity.title)
        assertNull(entity.openingCrawl)
        assertNull(entity.url)
    }

    @Test
    fun `test convertTo function with valid entity return valid model`() {
        val entity = DummyDataProvider.filmEntityProviderWithSingleItem()
        val model = entity.convertTo()
        assertThat(model.title, equalTo(DummyDataProvider.filmEntityProviderWithSingleItem().title))
        assertThat(
            model.description, equalTo(
                DummyDataProvider
                    .filmEntityProviderWithSingleItem().openingCrawl
            )
        )
        assertThat(model.url, equalTo(DummyDataProvider.filmEntityProviderWithSingleItem().url))
    }

    @Test
    fun `test convertTo function with null items entity return empty items model`() {
        val entity = FilmEntity(null, null, null)
        val model = entity.convertTo()

        assertThat(model.title, equalTo(""))
        assertThat(model.description, equalTo(""))
        assertThat(model.url, equalTo(""))
    }
}