package com.codeartist.starwar.detail.domain.usecases

import com.codeartist.starwar.common.utils.DataState
import com.codeartist.starwar.common.utils.Status
import com.codeartist.starwar.detail.domain.entity.SpeciesEntity
import com.codeartist.starwar.detail.domain.repository.DetailRepository
import com.codeartist.starwar.util.DummyDataProvider
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.test.runBlockingTest
import org.hamcrest.CoreMatchers
import org.junit.Before

import org.junit.Assert.*
import org.junit.Test

class GetSpeciesUseCaseTest {

    private lateinit var mockDetailRepositoryImpl: DetailRepository
    private lateinit var getSpeciesUseCase: GetSpeciesUseCase

    @Before
    fun setUp() {
        mockDetailRepositoryImpl = mockk()
        getSpeciesUseCase = GetSpeciesUseCase(mockDetailRepositoryImpl)
    }

    @Test
    fun `test execute with nonEmpty SpeciesEntity list return success datastate`() =
        runBlockingTest {
            //println(mockSearchRepositoryImpl)
            coEvery { (mockDetailRepositoryImpl.getSpecies(1)) } coAnswers {
                flow { emit(DataState.success(DummyDataProvider.provideSingleSpeciesEntity())) }
            }

            //println(mockSearchRepositoryImpl.searchCharacter("adi"))
            val dataState = getSpeciesUseCase.execute(1)
            assertThat(dataState.first().status, CoreMatchers.equalTo(Status.SUCCESS))
            assertNotNull(dataState.first().data)
            //assertThat(dataState.data?.size, CoreMatchers.equalTo(1))
        }

    @Test
    fun `test execute with null SpeciesEntity list return error datastate`() =
        runBlockingTest {
            coEvery { (mockDetailRepositoryImpl.getSpecies(-1)) } coAnswers {
                flow { emit((DataState.error<SpeciesEntity>("exception"))) }
            }
            val dataState = getSpeciesUseCase.execute(-1)
            assertThat(dataState.first().status, CoreMatchers.equalTo(Status.ERROR))
            assertNull(dataState.first().data)
            assertThat(dataState.first().message, CoreMatchers.equalTo("exception"))
        }
}