package com.codeartist.starwar.detail.presentation.uimodel

import com.codeartist.starwar.common.utils.Constants
import org.hamcrest.CoreMatchers.equalTo
import org.junit.Assert.*
import org.junit.Test

class HomeWorldModelTest{
    val name = "Naboo"
    val population = "450000000"

    @Test
    fun `test homeWorldModel with non empty data`(){
        val homeWorldModel = HomeWorldModel(name, population)
        assertThat(homeWorldModel.name, equalTo(name))
        assertThat(homeWorldModel.population, equalTo(population))
    }

    @Test
    fun `test homeWorldModel with empty data`(){
        val homeWorldModel = HomeWorldModel()
        assertThat(homeWorldModel.name, equalTo(Constants.EMPTY_STRING))
        assertThat(homeWorldModel.population, equalTo(Constants.EMPTY_STRING))
    }
}