package com.codeartist.starwar.search.domain.entity

import com.google.gson.annotations.Expose

import com.google.gson.annotations.SerializedName


data class ResultNetworkEntity(
    @SerializedName("name")
    @Expose
    var name: String? = null,

    @SerializedName("height")
    @Expose
    var height: String? = null,

    @SerializedName("birth_year")
    @Expose
    var birthYear: String? = null,

    @SerializedName("homeworld")
    @Expose
    var homeworld: String? = null,

    @SerializedName("films")
    @Expose
    var films: List<String>? = null,

    @SerializedName("species")
    @Expose
    var species: List<String>? = null
)
        

