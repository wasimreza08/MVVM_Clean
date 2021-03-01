package com.codeartist.starwar.search.domain.entity

import com.codeartist.starwar.common.utils.Utils
import com.codeartist.starwar.common.base.baseentity.Convertable
import com.codeartist.starwar.common.utils.Constants
import com.codeartist.starwar.search.presentation.uimodel.CharacterModel
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
                    height = it.height?.let { Utils.cmToFeetInches(it) }
                        ?: Constants.EMPTY_STRING,
                    birthYear = it.birthYear.orEmpty(),
                    homeWorld = it.homeworld.orEmpty(),
                    films = it.films ?: emptyList(),
                    species = it.species ?: emptyList()
                )
            }
        } ?: emptyList()
    }
}

