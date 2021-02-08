package com.codeartist.trivagochallenge.search.data.entity

import com.codeartist.trivagochallenge.common.Utils
import com.codeartist.trivagochallenge.common.baseentity.Convertable
import com.codeartist.trivagochallenge.search.presentation.uimodel.CharacterModel
import com.google.gson.annotations.Expose

import com.google.gson.annotations.SerializedName


data class SearchNetworkEntity(
    @SerializedName("results")
    @Expose
    var results: List<ResultNetworkEntity>? = null
) : Convertable<List<CharacterModel>> {
    override fun convertTo(): List<CharacterModel> {
        return results?.let {
            it.map {
                CharacterModel(
                    name = it.name.orEmpty(),
                    height = it.height?.let { it + " cm or " + Utils.cmToFeetInches(it.toInt()) }
                        ?: "",
                    birthYear = it.birthYear.orEmpty(),
                    homeWorld = it.homeworld.orEmpty(),
                    films = it.films ?: emptyList(),
                    species = it.species ?: emptyList()
                )
            }
        } ?: emptyList()
    }
}

