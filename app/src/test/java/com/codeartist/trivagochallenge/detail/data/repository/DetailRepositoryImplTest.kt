package com.codeartist.trivagochallenge.detail.data.repository

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.codeartist.trivagochallenge.common.utils.Status
import com.codeartist.trivagochallenge.detail.data.remotesource.DetailAPI
import com.codeartist.trivagochallenge.detail.domain.repository.DetailRepository
import com.codeartist.trivagochallenge.search.data.repository.MockWebServerBaseTest
import junit.framework.Assert.assertNotNull
import junit.framework.Assert.assertNull
import kotlinx.coroutines.InternalCoroutinesApi
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.runBlockingTest
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
    fun `test getFilm with error entity return error DataState with null`() = runBlockingTest {
        mockHttpResponse(
            "failed_response.json",
            HttpURLConnection.HTTP_CLIENT_TIMEOUT
        )
        val dataState = detailRepository.getFilm(-1)
        val film = dataState.first()
        println(film.message)
        assertThat(film.status, equalTo(Status.ERROR))
        assertNull(film.data)
    }

    @Test
    fun `test getFilm with nonEmpty entity return success DataState with data`() = runBlocking {
        mockHttpResponse(
            "film_success_response.json",
            HttpURLConnection.HTTP_OK
        )
        val dataState = detailRepository.getFilm(1)
        val film = dataState.first()
        assertThat(film.status, equalTo(Status.SUCCESS))
        assertNotNull(film.data)
    }


    @Test
    fun `test getSpecies with error entity return error DataState with null`() = runBlockingTest {
        mockHttpResponse(
            "failed_response.json",
            HttpURLConnection.HTTP_CLIENT_TIMEOUT
        )
        val dataState = detailRepository.getSpecies(-1)
        println(dataState)
        Assert.assertThat(dataState.first().status, equalTo(Status.ERROR))
        Assert.assertThat(dataState.first().data, equalTo(null))
    }

    @Test
    fun `test getSpecies with nonEmpty entity return success DataState with data`() = runBlocking {
        mockHttpResponse(
            "species_success_response.json",
            HttpURLConnection.HTTP_OK
        )
        val dataState = detailRepository.getSpecies(1)
        val species = dataState.first()
        println(dataState)
        assertThat(species.status, equalTo(Status.SUCCESS))
        assertNotNull(species.data)
    }

    @Test
    fun `test getHomeWorld with error entity return error DataState with null`() = runBlockingTest {
        mockHttpResponse(
            "failed_response.json",
            HttpURLConnection.HTTP_CLIENT_TIMEOUT
        )
        val dataState = detailRepository.getHomeWorld(-1)
        println(dataState)
        Assert.assertThat(dataState.status, equalTo(Status.ERROR))
        Assert.assertThat(dataState.data, equalTo(null))
    }

    @Test
    fun `test getHomeWorld with nonEmpty entity return success DataState with data`() =
        runBlocking {
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