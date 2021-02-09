package com.codeartist.trivagochallenge.common.utils

import java.math.RoundingMode
import java.text.DecimalFormat

object Utils {
    fun cmToFeetInches(cm: Int): String {
        val feet = Math.floor(cm / (2.54 * 12))
        val convertToInches = cm / (2.54 * 12) - feet
        val inches = convertToInches * 12
        val df = DecimalFormat("#.####")
        df.roundingMode = RoundingMode.CEILING
        return feet.toString() + " feet and " + df.format(inches).toDouble() + " inches"
    }
}