package com.codeartist.trivagochallenge.search.presentation.uimodel

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class CharaterModel(
    var name: String,
    var height: String,
    var birthYear: String,
    var homeworld: String,
    var films: List<String>,
    var species: List<String>,
    var starships: List<Any>,
    var url: String,
)
