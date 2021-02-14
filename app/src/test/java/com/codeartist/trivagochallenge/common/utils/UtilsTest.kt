package com.codeartist.trivagochallenge.common.utils

import com.codeartist.trivagochallenge.detail.presentation.uimodel.HomeWorldModel
import com.codeartist.trivagochallenge.util.DummyDataProvider
import org.hamcrest.CoreMatchers.equalTo
import org.junit.Assert.*
import org.junit.Test

class UtilsTest {
    @Test
    fun `test cmToFeetInches giving valid string return valid string`() {
        val height = Utils.cmToFeetInches("172")
        assertThat(height, equalTo("172 cm or 5.0 feet and 7.7166 inches"))
    }

    @Test
    fun `test cmToFeetInches giving invalid string return given string`() {
        val height = Utils.cmToFeetInches("unknown")
        assertThat(height, equalTo("unknown"))
    }

    @Test
    fun `test parseId giving valid url return id`() {
        val id = Utils.parseId("https://swapi.dev/api/planets/1/")
        assertThat(id, equalTo(1))
    }

    @Test
    fun `test parseId giving invalid url return -1`() {
        val id = Utils.parseId("https://swapi.dev/api/planets/")
        assertThat(id, equalTo(-1))
    }
    @Test
    fun `test isHomeWorldEmpty with non empty model return false`(){
        val empty = Utils.isHomeWorldEmpty(DummyDataProvider.provideSingleHomeWorldModel())
        assertThat(empty, equalTo(false))
    }
    @Test
    fun `test isHomeWorldEmpty with empty model return true`(){
        val empty = Utils.isHomeWorldEmpty(HomeWorldModel())
        assertThat(empty, equalTo(true))
    }
}