package com.codeartist.trivagochallenge.detail.presentation.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.SavedStateHandle
import com.codeartist.trivagochallenge.common.utils.DataState
import com.codeartist.trivagochallenge.common.utils.Status
import com.codeartist.trivagochallenge.detail.domain.usecases.GetFilmsUseCase
import com.codeartist.trivagochallenge.detail.domain.usecases.GetHomeWorldUseCase
import com.codeartist.trivagochallenge.detail.domain.usecases.GetSpeciesUseCase
import com.codeartist.trivagochallenge.detail.presentation.uimodel.FilmModel
import com.codeartist.trivagochallenge.detail.presentation.uimodel.SpeciesModel
import com.codeartist.trivagochallenge.util.DummyDataProvider
import com.codeartist.trivagochallenge.util.MainCoroutineRule
import com.codeartist.trivagochallenge.util.getOrAwaitValue
import com.codeartist.trivagochallenge.util.runBlockingTest
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.flow.flow
import org.hamcrest.CoreMatchers.equalTo
import org.junit.Assert.assertNull
import org.junit.Assert.assertThat
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class DetailViewModelTest {
    private lateinit var mockGetFilmsUseCase: GetFilmsUseCase
    private lateinit var mockGetSpeciesUseCase: GetSpeciesUseCase
    private lateinit var mockGetHomeWorldUseCase: GetHomeWorldUseCase
    private lateinit var viewModel: DetailViewModel

    @get:Rule
    var mainCoroutineRule = MainCoroutineRule()

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    @Before
    fun setUp() {
        mockGetFilmsUseCase = mockk()
        mockGetSpeciesUseCase = mockk()
        mockGetHomeWorldUseCase = mockk()
        viewModel = DetailViewModel(
            mockGetFilmsUseCase,
            mockGetSpeciesUseCase,
            mockGetHomeWorldUseCase,
            SavedStateHandle()
        )
        viewModel.defaultDispatcher = mainCoroutineRule.testDispatcher
    }

    @Test
    fun `test setCharacterInfo with nonEmpty data update live data with nonEmpty data`() =
        mainCoroutineRule.runBlockingTest {
            coEvery { (mockGetHomeWorldUseCase.execute(1)) } coAnswers {
                DataState.success(DummyDataProvider.provideSingleHomeWorldModel())
            }

            coEvery { (mockGetSpeciesUseCase.execute(1)) } coAnswers {
                flow { emit(DataState.success(DummyDataProvider.provideSingleSpeciesModel())) }
            }

            coEvery { (mockGetFilmsUseCase.execute(1)) } coAnswers {
                flow { emit(DataState.success(DummyDataProvider.filmModelProviderWithSingleItem())) }
            }

            viewModel.setCharacterInfo(DummyDataProvider.characterModelProviderForDetail())

            val firstValue = viewModel.detailInfo.getOrAwaitValue()
            assertThat(firstValue.status, equalTo(Status.LOADING))
            val secondValue = viewModel.detailInfo.getOrAwaitValue()
            assertThat(secondValue.status, equalTo(Status.SUCCESS))
            assertThat(secondValue.data?.filmList?.size, equalTo(1))
            assertThat(secondValue.data?.speciesList?.size, equalTo(1))
            assertThat(
                secondValue.data?.homeWorldModel,
                equalTo(DummyDataProvider.provideSingleHomeWorldModel())
            )
        }

    @Test
    fun `test setCharacterInfo with nonEmpty data update live data only filmList success`() =
        mainCoroutineRule.runBlockingTest {
            coEvery { (mockGetHomeWorldUseCase.execute(1)) } coAnswers {
                DataState.error("exception")
            }

            coEvery { (mockGetSpeciesUseCase.execute(1)) } coAnswers {
                flow { emit(DataState.error<SpeciesModel>("exception")) }
            }

            coEvery { (mockGetFilmsUseCase.execute(1)) } coAnswers {
                flow { emit(DataState.success(DummyDataProvider.filmModelProviderWithSingleItem())) }
            }

            viewModel.setCharacterInfo(DummyDataProvider.characterModelProviderForDetail())

            val firstValue = viewModel.detailInfo.getOrAwaitValue()
            assertThat(firstValue.status, equalTo(Status.LOADING))
            val secondValue = viewModel.detailInfo.getOrAwaitValue()
            assertThat(secondValue.status, equalTo(Status.SUCCESS))
            assertThat(secondValue.data?.filmList?.size, equalTo(1))
            assertThat(secondValue.data?.speciesList?.size, equalTo(0))
            assertThat(secondValue.data?.homeWorldModel?.population, equalTo(""))
            assertThat(secondValue.data?.homeWorldModel?.name, equalTo(""))
            assertThat(viewModel.isError.getOrAwaitValue(), equalTo(true))
        }

    @Test
    fun `test setCharacterInfo with nonEmpty data update live data filmList and speciesList success`() =
        mainCoroutineRule.runBlockingTest {
            coEvery { (mockGetHomeWorldUseCase.execute(1)) } coAnswers {
                DataState.error("exception")
            }

            coEvery { (mockGetSpeciesUseCase.execute(1)) } coAnswers {
                flow { emit(DataState.success(DummyDataProvider.provideSingleSpeciesModel())) }
            }

            coEvery { (mockGetFilmsUseCase.execute(1)) } coAnswers {
                flow { emit(DataState.success(DummyDataProvider.filmModelProviderWithSingleItem())) }
            }

            viewModel.setCharacterInfo(DummyDataProvider.characterModelProviderForDetail())

            val firstValue = viewModel.detailInfo.getOrAwaitValue()
            assertThat(firstValue.status, equalTo(Status.LOADING))
            val secondValue = viewModel.detailInfo.getOrAwaitValue()
            assertThat(secondValue.status, equalTo(Status.SUCCESS))
            assertThat(secondValue.data?.filmList?.size, equalTo(1))
            assertThat(secondValue.data?.speciesList?.size, equalTo(1))
            assertThat(secondValue.data?.homeWorldModel?.population, equalTo(""))
            assertThat(secondValue.data?.homeWorldModel?.name, equalTo(""))
            assertThat(viewModel.isError.getOrAwaitValue(), equalTo(true))
        }

    @Test
    fun `test setCharacterInfo with nonEmpty data update live data filmList and HomeWorldModel success`() =
        mainCoroutineRule.runBlockingTest {
            coEvery { (mockGetHomeWorldUseCase.execute(1)) } coAnswers {
                DataState.success(DummyDataProvider.provideSingleHomeWorldModel())
            }

            coEvery { (mockGetSpeciesUseCase.execute(1)) } coAnswers {
                flow { emit(DataState.error<SpeciesModel>("exception")) }
            }

            coEvery { (mockGetFilmsUseCase.execute(1)) } coAnswers {
                flow { emit(DataState.success(DummyDataProvider.filmModelProviderWithSingleItem())) }
            }

            viewModel.setCharacterInfo(DummyDataProvider.characterModelProviderForDetail())

            val firstValue = viewModel.detailInfo.getOrAwaitValue()
            assertThat(firstValue.status, equalTo(Status.LOADING))
            val secondValue = viewModel.detailInfo.getOrAwaitValue()
            assertThat(secondValue.status, equalTo(Status.SUCCESS))
            assertThat(secondValue.data?.filmList?.size, equalTo(1))
            assertThat(secondValue.data?.speciesList?.size, equalTo(0))
            assertThat(
                secondValue.data?.homeWorldModel?.population,
                equalTo(DummyDataProvider.provideSingleHomeWorldModel().population)
            )
            assertThat(
                secondValue.data?.homeWorldModel?.name,
                equalTo(DummyDataProvider.provideSingleHomeWorldModel().name)
            )
            assertThat(viewModel.isError.getOrAwaitValue(), equalTo(true))
        }

    @Test
    fun `test setCharacterInfo with nonEmpty data update live data speciesList and HomeWorldModel success`() =
        mainCoroutineRule.runBlockingTest {
            coEvery { (mockGetHomeWorldUseCase.execute(1)) } coAnswers {
                DataState.success(DummyDataProvider.provideSingleHomeWorldModel())
            }

            coEvery { (mockGetSpeciesUseCase.execute(1)) } coAnswers {
                flow { emit(DataState.success(DummyDataProvider.provideSingleSpeciesModel())) }
            }

            coEvery { (mockGetFilmsUseCase.execute(1)) } coAnswers {
                flow { emit(DataState.error<FilmModel>("exception")) }
            }

            viewModel.setCharacterInfo(DummyDataProvider.characterModelProviderForDetail())

            val firstValue = viewModel.detailInfo.getOrAwaitValue()
            assertThat(firstValue.status, equalTo(Status.LOADING))
            val secondValue = viewModel.detailInfo.getOrAwaitValue()
            assertThat(secondValue.status, equalTo(Status.SUCCESS))
            assertThat(secondValue.data?.filmList?.size, equalTo(0))
            assertThat(secondValue.data?.speciesList?.size, equalTo(1))
            assertThat(
                secondValue.data?.homeWorldModel?.population,
                equalTo(DummyDataProvider.provideSingleHomeWorldModel().population)
            )
            assertThat(
                secondValue.data?.homeWorldModel?.name,
                equalTo(DummyDataProvider.provideSingleHomeWorldModel().name)
            )
            assertThat(viewModel.isError.getOrAwaitValue(), equalTo(true))
        }

    @Test
    fun `test setCharacterInfo with nonEmpty data update live data with only SpeciesList`() =
        mainCoroutineRule.runBlockingTest {
            coEvery { (mockGetHomeWorldUseCase.execute(1)) } coAnswers {
                DataState.error("exception")
            }

            coEvery { (mockGetSpeciesUseCase.execute(1)) } coAnswers {
                flow { emit(DataState.success(DummyDataProvider.provideSingleSpeciesModel())) }
            }

            coEvery { (mockGetFilmsUseCase.execute(1)) } coAnswers {
                flow { emit(DataState.error<FilmModel>("exception")) }
            }

            viewModel.setCharacterInfo(DummyDataProvider.characterModelProviderForDetail())

            val firstValue = viewModel.detailInfo.getOrAwaitValue()
            assertThat(firstValue.status, equalTo(Status.LOADING))
            val secondValue = viewModel.detailInfo.getOrAwaitValue()
            assertThat(secondValue.status, equalTo(Status.SUCCESS))
            assertThat(secondValue.data?.filmList?.size, equalTo(0))
            assertThat(secondValue.data?.speciesList?.size, equalTo(1))
            assertThat(secondValue.data?.homeWorldModel?.population, equalTo(""))
            assertThat(secondValue.data?.homeWorldModel?.name, equalTo(""))
            assertThat(viewModel.isError.getOrAwaitValue(), equalTo(true))
        }

    @Test
    fun `test setCharacterInfo with nonEmpty data update live data with only HomeWorldModel`() =
        mainCoroutineRule.runBlockingTest {
            coEvery { (mockGetHomeWorldUseCase.execute(1)) } coAnswers {
                DataState.success(DummyDataProvider.provideSingleHomeWorldModel())
            }

            coEvery { (mockGetSpeciesUseCase.execute(1)) } coAnswers {
                flow { emit(DataState.error<SpeciesModel>("exception")) }
            }

            coEvery { (mockGetFilmsUseCase.execute(1)) } coAnswers {
                flow { emit(DataState.error<FilmModel>("exception")) }
            }

            viewModel.setCharacterInfo(DummyDataProvider.characterModelProviderForDetail())

            val firstValue = viewModel.detailInfo.getOrAwaitValue()
            assertThat(firstValue.status, equalTo(Status.LOADING))
            val secondValue = viewModel.detailInfo.getOrAwaitValue()
            assertThat(secondValue.status, equalTo(Status.SUCCESS))
            assertThat(secondValue.data?.filmList?.size, equalTo(0))
            assertThat(secondValue.data?.speciesList?.size, equalTo(0))
            assertThat(
                secondValue.data?.homeWorldModel?.population,
                equalTo(DummyDataProvider.provideSingleHomeWorldModel().population)
            )
            assertThat(
                secondValue.data?.homeWorldModel?.name,
                equalTo(DummyDataProvider.provideSingleHomeWorldModel().name)
            )
            assertThat(viewModel.isError.getOrAwaitValue(), equalTo(true))
        }

    @Test
    fun `test setCharacterInfo with nonEmpty data update live data with error`() =
        mainCoroutineRule.runBlockingTest {
            coEvery { (mockGetHomeWorldUseCase.execute(1)) } coAnswers {
                DataState.error("exception")
            }

            coEvery { (mockGetSpeciesUseCase.execute(1)) } coAnswers {
                flow { emit(DataState.error<SpeciesModel>("exception")) }
            }

            coEvery { (mockGetFilmsUseCase.execute(1)) } coAnswers {
                flow { emit(DataState.error<FilmModel>("exception")) }
            }

            viewModel.setCharacterInfo(DummyDataProvider.characterModelProviderForDetail())

            val firstValue = viewModel.detailInfo.getOrAwaitValue()
            assertThat(firstValue.status, equalTo(Status.LOADING))
            val secondValue = viewModel.detailInfo.getOrAwaitValue()
            assertThat(secondValue.status, equalTo(Status.ERROR))
            assertNull(secondValue.data)
            assertThat(viewModel.isError.getOrAwaitValue(), equalTo(true))
        }
}