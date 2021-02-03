package com.codeartist.practicetest.data.remoteentity

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class FilmEntity(
    @SerializedName("title")
    @Expose
    var title: String,
    @SerializedName("opening_crawl")
    @Expose
    var openingCrawl: String
)