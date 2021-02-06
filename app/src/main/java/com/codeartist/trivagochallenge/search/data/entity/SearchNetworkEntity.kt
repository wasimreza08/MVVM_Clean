package com.codeartist.trivagochallenge.search.data.entity

import com.codeartist.trivagochallenge.common.baseentity.Convertable
import com.codeartist.trivagochallenge.search.presentation.uimodel.CharacterModel
import com.google.gson.annotations.Expose

import com.google.gson.annotations.SerializedName


data class SearchNetworkEntity(
    @SerializedName("count")
    @Expose
    var count: Int? = null,

    @SerializedName("next")
    @Expose
    var next: Any? = null,

    @SerializedName("previous")
    @Expose
    var previous: Any? = null,

    @SerializedName("results")
    @Expose
    var results: List<ResultNetworkEntity>? = null

) : Convertable<List<CharacterModel>> {
    override fun convertTo(): List<CharacterModel> {
        return results?.let {
            it.map {
                CharacterModel(
                    name = it.name.orEmpty(),
                    height = it.height.orEmpty(),
                    birthYear = it.birthYear.orEmpty(),
                    homeworld = it.homeworld.orEmpty(),
                    films = it.films ?: emptyList(),
                    species = it.species ?: emptyList(),
                    url = it.url.orEmpty()
                )
            }
        } ?: emptyList()
    }
}

