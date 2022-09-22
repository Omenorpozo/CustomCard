package com.omar.ui_components.view.utils

object CardUtils {

    private const val SPACE_SEPERATOR = " "
    private const val CARD_NUMBER_FORMAT = "XXXX XXXX XXXX XXXX"
    private const val CHAR_X = 'X'

    fun formatCardNumber(cardNumber: String, separator: String = SPACE_SEPERATOR): String {
        val unformattedText = cardNumber.replace(separator, "")
        val format = CARD_NUMBER_FORMAT
        val sbFormattedNumber = StringBuilder()
        var iIdx = 0
        var jIdx = 0
        while (iIdx < format.length) {
            if (format[iIdx] == CHAR_X && unformattedText.length > jIdx) sbFormattedNumber.append(
                unformattedText[jIdx++]
            ) else sbFormattedNumber.append(format[iIdx])
            iIdx++
        }
        return sbFormattedNumber.toString()
            .replace(SPACE_SEPERATOR, SPACE_SEPERATOR + SPACE_SEPERATOR)
    }
}