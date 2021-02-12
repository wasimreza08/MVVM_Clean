package com.codeartist.trivagochallenge.detail.domain.entity

import com.codeartist.trivagochallenge.common.base.baseentity.Convertable
import com.codeartist.trivagochallenge.detail.presentation.uimodel.SpeciesModel
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class SpeciesEntity(
    @SerializedName("name")
    @Expose
    var name: String?,
    @SerializedName("language")
    @Expose
    var language: String?,

) : Convertable<SpeciesModel> {
    override fun convertTo(): SpeciesModel {
        return SpeciesModel(
            language = this.language.orEmpty(),
            name = this.name.orEmpty(),
        )
    }
}
