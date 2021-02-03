package com.codeartist.trivagochallenge.search.data.remotesource

import com.codeartist.trivagochallenge.search.data.entity.SearchNetworkEntity
import retrofit2.http.GET
import retrofit2.http.Query

interface RemoteAPI {
    @GET("/api/people/")
    suspend fun getCharacter(@Query("search") searchString: String): SearchNetworkEntity
}