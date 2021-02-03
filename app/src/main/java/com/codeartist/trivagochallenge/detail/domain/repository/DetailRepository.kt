package com.codeartist.trivagochallenge.detail.domain.repository

interface DetailRepository {
    suspend fun getFilms(id:Int?)
}