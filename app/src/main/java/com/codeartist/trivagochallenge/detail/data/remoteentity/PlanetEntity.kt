package com.codeartist.trivagochallenge.detail.data.remoteentity

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class PlanetEntity(
    @SerializedName("population")
    @Expose
    var population: String
)