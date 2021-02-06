package com.codeartist.trivagochallenge.search.presentation.uimodel

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class CharacterModel(
    var name: String,
    var height: String,
    var birthYear: String,
    var homeworld: String,
    var films: List<String>,
    var species: List<String>
):Parcelable
