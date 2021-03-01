package com.codeartist.starwar.common.utils

import com.codeartist.starwar.detail.presentation.uimodel.HomeWorldModel
import java.math.RoundingMode
import java.text.DecimalFormat
import kotlin.math.floor

object Utils {
    fun cmToFeetInches(height: String): String {
        val cm = height.toIntOrNull() ?: return height
        val feet = floor(cm / (2.54 * 12))
        val convertToInches = cm / (2.54 * 12) - feet
        val inches = convertToInches * 12
        val df = DecimalFormat("#.####")
        df.roundingMode = RoundingMode.CEILING
        return "$height cm or $feet feet and " + df.format(inches)
            .toDouble() + " inches"
    }

    fun parseId(link: String): Int {
        val url = link.split("/")
        return url.get(url.size - 2).toIntOrNull() ?: -1
    }

    fun isHomeWorldEmpty(item: HomeWorldModel): Boolean {
        return item.name.isEmpty() && item.population.isEmpty()
    }
}