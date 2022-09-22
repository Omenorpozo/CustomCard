package com.omar.mobiletest.ui.extensions

import java.text.DecimalFormat
import java.text.DecimalFormatSymbols

private const val MASK_PATTERN_NAME_CREDIT_FIRST_THREE_DIGITS = "...####"

fun String.obfuscateCreditFirstThreeDigits(): String {
    val masked = StringBuilder()
    var index = 0

    for (element in MASK_PATTERN_NAME_CREDIT_FIRST_THREE_DIGITS) {
        when (element) {
            '#' -> {
                masked.append(this[index])
                index++
            }
            '.' -> {
                masked.append(element)
                index++
            }
            else -> {
                masked.append(element)
            }
        }
    }

    return masked.toString()
}

fun String.getFormattedValue(): String {
    return try {
        val s = DecimalFormatSymbols()
        val format = DecimalFormat("#,##0.00", s)
        s.decimalSeparator = Separator.decimalSeparator.digit
        s.groupingSeparator = Separator.groupingSeparator.digit
        format.decimalFormatSymbols = s
        format.format(this.toBigDecimal())
    } catch (e: Exception) {
        "0"
    }
}

object Separator {
    @JvmStatic var groupingSeparator: GroupingSeparator = GroupingSeparator.Dot

    @JvmStatic var decimalSeparator: DecimalSeparator = DecimalSeparator.Comma
}

sealed class DecimalSeparator(val digit: Char) {
    object Comma : DecimalSeparator(',')

    override fun toString() = digit.toString()
}

sealed class GroupingSeparator(val digit: Char) {
    object Dot : GroupingSeparator('.')

    override fun toString() = digit.toString()
}