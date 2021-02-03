package com.codeartist.trivagochallenge.detail.presentation.viewmodel

import androidx.hilt.Assisted
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.codeartist.trivagochallenge.detail.domain.usecases.GetFilmsTitleUseCase
import kotlinx.coroutines.launch

class DetailViewModel @ViewModelInject constructor(
    private val getFilmsTitleUseCase: GetFilmsTitleUseCase,
    @Assisted private val savedStateHandle: SavedStateHandle
) : ViewModel() {

    fun getFilms(link: String?) {
        viewModelScope.launch {
            parseId(link)?.let {
                getFilmsTitleUseCase.execute(it)
            }

        }
    }

    private fun parseId(link: String?): Int? {
        val url = link?.split("/")
        return url?.get(url?.size - 2)?.toInt()
    }
}