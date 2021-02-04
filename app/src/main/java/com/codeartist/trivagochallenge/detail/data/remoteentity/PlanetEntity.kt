package com.codeartist.trivagochallenge.detail.data.remoteentity

import com.codeartist.trivagochallenge.common.baseentity.Convertable
import com.codeartist.trivagochallenge.detail.presentation.uimodel.PlanetModel
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class PlanetEntity(
    @SerializedName("population")
    @Expose
    var population: String?
) : Convertable<PlanetModel> {
    override fun convertTo(): PlanetModel {
        return PlanetModel(population = this.population.orEmpty())
    }
}