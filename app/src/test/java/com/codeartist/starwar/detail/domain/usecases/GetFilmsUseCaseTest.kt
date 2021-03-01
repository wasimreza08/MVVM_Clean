package com.codeartist.starwar.detail.domain.usecases

import com.codeartist.practicetest.data.remoteentity.FilmEntity
import com.codeartist.starwar.common.utils.DataState
import com.codeartist.starwar.common.utils.Status
import com.codeartist.starwar.detail.domain.repository.DetailRepository
import com.codeartist.starwar.util.DummyDataProvider
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.test.runBlockingTest
import org.hamcrest.CoreMatchers
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test

class GetFilmsUseCaseTest {
    private lateinit var mockDetailRepositoryImpl: DetailRepository
    private lateinit var getFilmUseCase: GetFilmsUseCase

    @Before
    fun setUp() {
        mockDetailRepositoryImpl = mockk()
        getFilmUseCase = GetFilmsUseCase(mockDetailRepositoryImpl)
    }

    @Test
    fun `test execute with nonEmpty filmEntity list return success datastate`() =
        runBlockingTest {
            //println(mockSearchRepositoryImpl)
            coEvery { (mockDetailRepositoryImpl.getFilm(1)) } coAnswers {
                flow { emit(DataState.success(DummyDataProvider.filmEntityProviderWithSingleItem())) }
            }

            //println(mockSearchRepositoryImpl.searchCharacter("adi"))
            val dataState = getFilmUseCase.execute(1)
            assertThat(dataState.first().status, CoreMatchers.equalTo(Status.SUCCESS))
            assertNotNull(dataState.first().data)
            //assertThat(dataState.data?.size, CoreMatchers.equalTo(1))
        }

    @Test
    fun `test execute with null filmEntity list return error datastate`() =
        runBlockingTest {
            coEvery { (mockDetailRepositoryImpl.getFilm(-1)) } coAnswers {
                flow { emit(DataState.error<FilmEntity>("exception")) }
            }
            val dataState = getFilmUseCase.execute(-1)
            assertThat(dataState.first().status, CoreMatchers.equalTo(Status.ERROR))
            assertNull(dataState.first().data)
            assertThat(dataState.first().message, CoreMatchers.equalTo("exception"))
        }
}