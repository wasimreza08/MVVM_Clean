package com.codeartist.trivagochallenge.detail.domain.entity

import com.codeartist.trivagochallenge.common.base.baseentity.Convertable
import com.codeartist.trivagochallenge.detail.presentation.uimodel.HomeWorldModel
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class HomeWorldEntity(
    @SerializedName("name")
    @Expose
    var name: String?,
    @SerializedName("population")
    @Expose
    var population: String?
) : Convertable<HomeWorldModel> {
    override fun convertTo(): HomeWorldModel {
        return HomeWorldModel(name = this.name.orEmpty(), population = this.population.orEmpty())
    }
}