package com.codeartist.trivagochallenge.search.domain.usecase

import com.codeartist.trivagochallenge.common.utils.DataState
import com.codeartist.trivagochallenge.common.utils.Status
import com.codeartist.trivagochallenge.search.domain.repository.SearchRepository
import com.codeartist.trivagochallenge.util.DummyDataProvider
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.test.runBlockingTest

import org.hamcrest.CoreMatchers.equalTo
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test


class SearchUseCaseTest {
    private lateinit var mockSearchRepositoryImpl: SearchRepository
    private lateinit var searchUseCase: SearchUseCase

    @Before
    fun setUp() {
        mockSearchRepositoryImpl = mockk()
        searchUseCase = SearchUseCase(mockSearchRepositoryImpl)
    }

    @Test
    fun `test execute with non empty entity return CharacterModel datastate`() =
        runBlockingTest {
            println(mockSearchRepositoryImpl)
            coEvery { (mockSearchRepositoryImpl.searchCharacter("adi")) } coAnswers {
                (DataState.success(DummyDataProvider.searchNetworkEntityProvider()))
            }

            println(mockSearchRepositoryImpl.searchCharacter("adi"))
            val dataState = searchUseCase.execute("adi")
            assertThat(dataState.status, equalTo(Status.SUCCESS))
            assertNotNull(dataState.data)
           // assertThat(dataState.data?.size, equalTo(1))
        }

    @Test
    fun `test execute with exception return null charactermodel`() =
        runBlockingTest {
            coEvery { (mockSearchRepositoryImpl.searchCharacter("")) } coAnswers {
                (DataState.error("exception"))
            }
            val dataState = searchUseCase.execute("")
            assertThat(dataState.status, equalTo(Status.ERROR))
            assertNull(dataState.data)
            assertThat(dataState.message, equalTo("exception"))
        }
}