package com.codeartist.trivagochallenge.detail.data.repository

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.codeartist.trivagochallenge.common.utils.Status
import com.codeartist.trivagochallenge.detail.data.remotesource.DetailAPI
import com.codeartist.trivagochallenge.detail.domain.repository.DetailRepository
import com.codeartist.trivagochallenge.search.data.repository.MockWebServerBaseTest
import junit.framework.Assert.assertNotNull
import kotlinx.coroutines.runBlocking
import okhttp3.mockwebserver.MockWebServer
import org.hamcrest.CoreMatchers.equalTo
import org.hamcrest.MatcherAssert.assertThat
import org.junit.*

import org.junit.rules.TestRule
import org.junit.runner.RunWith
import org.mockito.MockitoAnnotations
import org.mockito.junit.MockitoJUnitRunner
import java.net.HttpURLConnection

@RunWith(MockitoJUnitRunner::class)
class DetailRepositoryImplTest : MockWebServerBaseTest() {
    @get:Rule
    val testInstantTaskExecutorRule: TestRule = InstantTaskExecutorRule()
    lateinit var mockWebServer: MockWebServer
    private lateinit var apiService: DetailAPI
    private lateinit var detailRepository: DetailRepository


    @Before
    fun start() {
        MockitoAnnotations.initMocks(this)
        mockWebServer = MockWebServer()
        mockWebServer.start()
        apiService =
            provideDetailApiService()
        detailRepository = DetailRepositoryImpl(apiService)
    }

    @Test
    fun `film error entity return error DataState with null`() = runBlocking {
        mockHttpResponse(
            "failed_response.json",
            HttpURLConnection.HTTP_BAD_REQUEST
        )
        val dataState = detailRepository.getFilm(-1)
        println(dataState)
        Assert.assertThat(dataState.status, equalTo(Status.ERROR))
        Assert.assertThat(dataState.data, equalTo(null))
    }

    @Test
    fun `film nonEmpty entity return success DataState with data`() = runBlocking {
        mockHttpResponse(
            "film_success_response.json",
            HttpURLConnection.HTTP_OK
        )
        val dataState = detailRepository.getFilm(1)
        println(dataState)
        assertThat(dataState.status, equalTo(Status.SUCCESS))
        assertNotNull(dataState.data)
    }


    @Test
    fun `species error entity return error DataState with null`() = runBlocking {
        mockHttpResponse(
            "failed_response.json",
            HttpURLConnection.HTTP_BAD_REQUEST
        )
        val dataState = detailRepository.getSpecies(-1)
        println(dataState)
        Assert.assertThat(dataState.status, equalTo(Status.ERROR))
        Assert.assertThat(dataState.data, equalTo(null))
    }

    @Test
    fun `species nonEmpty entity return success DataState with data`() = runBlocking {
        mockHttpResponse(
            "species_success_response.json",
            HttpURLConnection.HTTP_OK
        )
        val dataState = detailRepository.getSpecies(1)
        println(dataState)
        assertThat(dataState.status, equalTo(Status.SUCCESS))
        assertNotNull(dataState.data)
    }

    @Test
    fun `homeWorld error entity return error DataState with null`() = runBlocking {
        mockHttpResponse(
            "failed_response.json",
            HttpURLConnection.HTTP_BAD_REQUEST
        )
        val dataState = detailRepository.getSpecies(-1)
        println(dataState)
        Assert.assertThat(dataState.status, equalTo(Status.ERROR))
        Assert.assertThat(dataState.data, equalTo(null))
    }

    @Test
    fun `homeWorld nonEmpty entity return success DataState with data`() = runBlocking {
        mockHttpResponse(
            "homeworld_success_response.json",
            HttpURLConnection.HTTP_OK
        )
        val dataState = detailRepository.getHomeWorld(1)
        println(dataState)
        assertThat(dataState.status, equalTo(Status.SUCCESS))
        assertNotNull(dataState.data)
    }

    override fun isMockServerEnabled() = true
}