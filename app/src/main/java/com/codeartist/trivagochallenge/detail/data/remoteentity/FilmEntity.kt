package com.codeartist.practicetest.data.remoteentity

import com.codeartist.trivagochallenge.common.baseentity.Convertable
import com.codeartist.trivagochallenge.detail.presentation.uimodel.FilmModel
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class FilmEntity(
    @SerializedName("title")
    @Expose
    var title: String?,
    @SerializedName("opening_crawl")
    @Expose
    var openingCrawl: String?
) : Convertable<FilmModel> {
    override fun convertTo(): FilmModel {
        return FilmModel(
            title = this.title.orEmpty(),
            description = this.openingCrawl.orEmpty()
        )
    }
}
