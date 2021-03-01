package com.codeartist.starwar.search.data.remotesource

import com.codeartist.starwar.search.domain.entity.SearchNetworkEntity
import retrofit2.http.GET
import retrofit2.http.Query

interface RemoteAPI {
    @GET("/api/people/")
    suspend fun getCharacter(@Query("search") searchString: String): SearchNetworkEntity
}