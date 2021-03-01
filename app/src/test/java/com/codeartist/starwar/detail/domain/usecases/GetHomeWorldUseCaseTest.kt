package com.codeartist.starwar.detail.domain.usecases

import com.codeartist.starwar.common.utils.DataState
import com.codeartist.starwar.common.utils.Status
import com.codeartist.starwar.detail.domain.repository.DetailRepository
import com.codeartist.starwar.util.DummyDataProvider
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.test.runBlockingTest
import org.hamcrest.CoreMatchers
import org.junit.Before

import org.junit.Assert.*
import org.junit.Test

class GetHomeWorldUseCaseTest {

    private lateinit var mockDetailRepositoryImpl: DetailRepository
    private lateinit var getHomeWorldUseCase: GetHomeWorldUseCase

    @Before
    fun setUp() {
        mockDetailRepositoryImpl = mockk()
        getHomeWorldUseCase = GetHomeWorldUseCase(mockDetailRepositoryImpl)
    }

    @Test
    fun `test execute with nonEmpty PlanetEntity list return success datastate`() =
        runBlockingTest {
            //println(mockSearchRepositoryImpl)
            coEvery { (mockDetailRepositoryImpl.getHomeWorld(1)) } coAnswers {
                (DataState.success(DummyDataProvider.provideSingleHomeWorldEntity()))
            }

            //println(mockSearchRepositoryImpl.searchCharacter("adi"))
            val dataState = getHomeWorldUseCase.execute(1)
            assertThat(dataState.status, CoreMatchers.equalTo(Status.SUCCESS))
            assertNotNull(dataState.data)
            //assertThat(dataState.data?.size, CoreMatchers.equalTo(1))
        }

    @Test
    fun `test execute with null PlanetEntity list return error datastate`() =
        runBlockingTest {
            coEvery { (mockDetailRepositoryImpl.getHomeWorld(-1)) } coAnswers {
                (DataState.error("exception"))
            }
            val dataState = getHomeWorldUseCase.execute(-1)
            assertThat(dataState.status, CoreMatchers.equalTo(Status.ERROR))
            assertNull(dataState.data)
            assertThat(dataState.message, CoreMatchers.equalTo("exception"))
        }
}