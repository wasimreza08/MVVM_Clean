package com.codeartist.trivagochallenge.detail.data.repository

import android.util.Log
import androidx.lifecycle.viewModelScope
import com.codeartist.trivagochallenge.detail.data.DetailAPI
import com.codeartist.trivagochallenge.detail.domain.repository.DetailRepository
import kotlinx.coroutines.launch
import javax.inject.Inject

class DetailRepositoryImpl @Inject constructor() : DetailRepository {

    override suspend fun getFilms(id: Int?) {
        id?.let {
           // val temp = detailAPI.getFilms(id.toInt())
            Log.e("film repository", "")
        }
    }

}