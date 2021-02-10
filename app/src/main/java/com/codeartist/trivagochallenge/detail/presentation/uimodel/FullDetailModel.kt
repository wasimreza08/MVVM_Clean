package com.codeartist.trivagochallenge.detail.presentation.uimodel

data class FullDetailModel(
    var filmList: MutableList<FilmModel>,
    var speciesList: MutableList<SpeciesModel>,
    var homeWorldModel: HomeWorldModel
)