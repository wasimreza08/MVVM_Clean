package com.codeartist.trivagochallenge.search.data.repository

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.codeartist.trivagochallenge.common.utils.Status
import com.codeartist.trivagochallenge.search.data.remotesource.RemoteAPI
import com.codeartist.trivagochallenge.search.domain.repository.SearchRepository
import kotlinx.coroutines.runBlocking
import okhttp3.mockwebserver.MockWebServer
import org.hamcrest.CoreMatchers.equalTo
import org.junit.Before

import org.junit.Assert.*
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule
import org.junit.runner.RunWith
import org.mockito.MockitoAnnotations
import org.mockito.junit.MockitoJUnitRunner
import java.net.HttpURLConnection

@RunWith(MockitoJUnitRunner::class)
class SearchRepositoryImplTest : MockWebServerBaseTest() {
    @get:Rule
    val testInstantTaskExecutorRule: TestRule = InstantTaskExecutorRule()
    lateinit var mockWebServer: MockWebServer
    private lateinit var apiService: RemoteAPI
    private lateinit var searchRepositoryImpl: SearchRepository


    @Before
    fun start() {
        MockitoAnnotations.initMocks(this)
        mockWebServer = MockWebServer()
        mockWebServer.start()
        apiService =
            provideRemoteApiService()
        searchRepositoryImpl = SearchRepositoryImpl(apiService)
    }

    @Test
    fun `Given error search entity return error DataState with null`() = runBlocking{
        mockHttpResponse(
            "failed_response.json",
            HttpURLConnection.HTTP_BAD_REQUEST
        )
        val dataState = searchRepositoryImpl.searchCharacter("R2")
        println(dataState)
        assertThat(dataState.status, equalTo(Status.ERROR))
        assertThat(dataState.data, equalTo(null))
    }

    @Test
    fun `Given valid search entity return success DataState with data`() = runBlocking {
        mockHttpResponse(
            "success_response.json",
            HttpURLConnection.HTTP_OK
        )
        val dataState = searchRepositoryImpl.searchCharacter("adi")
        println(dataState)
        assertThat(dataState.status, equalTo(Status.SUCCESS))
        assertThat(dataState.data?.results?.size, equalTo(3))
    }

    override fun isMockServerEnabled() = true
}