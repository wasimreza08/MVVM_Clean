package com.codeartist.trivagochallenge.search.presentation.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.SavedStateHandle
import com.codeartist.trivagochallenge.common.utils.DataState
import com.codeartist.trivagochallenge.common.utils.Status
import com.codeartist.trivagochallenge.search.domain.usecase.SearchUseCase
import com.codeartist.trivagochallenge.util.DummyDataProvider.searchNetworkEntityProvider
import com.codeartist.trivagochallenge.util.DummyDataProvider.singleCharacterModelProvider
import com.codeartist.trivagochallenge.util.DummyDataProvider.singleResultDataProvider
import com.codeartist.trivagochallenge.util.MainCoroutineRule
import com.codeartist.trivagochallenge.util.getOrAwaitValue
import com.codeartist.trivagochallenge.util.runBlockingTest
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.hamcrest.CoreMatchers.equalTo
import org.hamcrest.MatcherAssert.assertThat
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@ExperimentalCoroutinesApi
class SearchViewModelTest {
    private lateinit var searchViewModel: SearchViewModel
    private lateinit var searchUseCase: SearchUseCase

    @get:Rule
    var mainCoroutineRule = MainCoroutineRule()

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    @Before
    fun setUp() {
        searchUseCase = mockk()
        searchViewModel =
            SearchViewModel(searchUseCase, SavedStateHandle())
        searchViewModel.defaultDispatcher = mainCoroutineRule.testDispatcher
    }

    @Test
    fun `test setSearchString with nonEmpty search string return valid datastate`() =
        mainCoroutineRule.runBlockingTest {
            coEvery { searchUseCase.execute("adi") } coAnswers {
                DataState.success(
                    searchNetworkEntityProvider()
                )
            }

            searchViewModel.setSearchString("adi")
            //println(searchViewModel.searchResult.getOrAwaitValue())
            val firstValue = searchViewModel.searchResult.getOrAwaitValue()
            assertThat(
                firstValue.status,
                equalTo(Status.LOADING)
            )
            val secondValue = searchViewModel.searchResult.getOrAwaitValue()
            assertThat(
                secondValue.status,
                equalTo(Status.SUCCESS)
            )
            assertThat(
                secondValue.data?.size,
                equalTo(1)
            )
            // println(searchViewModel.searchResult.getOrAwaitValue().status)
        }

    @Test
    fun `test setSearchString with nonEmpty search string return datastate with error`() =
        mainCoroutineRule.runBlockingTest {
            coEvery { searchUseCase.execute("adi") } coAnswers {
                DataState.error("exception")
            }

            searchViewModel.setSearchString("adi")
            //println(searchViewModel.searchResult.getOrAwaitValue())
            val firstValue = searchViewModel.searchResult.getOrAwaitValue()
            assertThat(
                firstValue.status,
                equalTo(Status.LOADING)
            )
            val secondValue = searchViewModel.searchResult.getOrAwaitValue()
            assertThat(
                secondValue.status,
                equalTo(Status.ERROR)
            )
            assertThat(
                secondValue.message,
                equalTo("exception")
            )
            // println(searchViewModel.searchResult.getOrAwaitValue().status)
        }

    @Test
    fun `test setSearchString with empty search string return datastate with empty list`() =
        mainCoroutineRule.runBlockingTest {
            searchViewModel.setSearchString("")
            //println(searchViewModel.searchResult.getOrAwaitValue())
            val firstValue = searchViewModel.searchResult.getOrAwaitValue()
            assertThat(
                firstValue.status,
                equalTo(Status.SUCCESS)
            )

            assertThat(
                firstValue.data?.size,
                equalTo(0)
            )
            // println(searchViewModel.searchResult.getOrAwaitValue().status)
        }


}
