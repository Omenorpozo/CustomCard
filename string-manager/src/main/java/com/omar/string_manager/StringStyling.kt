package com.omar.string_manager

import android.graphics.Color
import android.graphics.Typeface
import android.text.SpannableString
import android.text.Spanned
import android.text.style.CharacterStyle
import android.text.style.ForegroundColorSpan
import android.text.style.StyleSpan
import java.lang.IllegalArgumentException
import java.lang.NumberFormatException

class StringStyling(
    val stylePlaceHolder: StylePlaceHolder,
    val startPosition: Int,
    var endPosition: Int = -1,
    val extra: String? = null
) {

    enum class StylePlaceHolder {
        BOLD,
        COLOR,
        UNKOWN
    }

    fun apply(input: SpannableString): SpannableString {
        var span: CharacterStyle? = null
        when(stylePlaceHolder) {
            StylePlaceHolder.BOLD -> StyleSpan(Typeface.BOLD)
            StylePlaceHolder.COLOR -> {
                extra?.let {
                    try {
                        val color = Color.parseColor(extra)
                        span = ForegroundColorSpan(color)
                    } catch (ex: IllegalArgumentException){}
                }
            }
        }
        return applySpan(span, input)
    }

    private fun applySpan(span: Any?, input: SpannableString): SpannableString {
        span?.let {
            if (startPosition >= 0 && endPosition < startPosition && endPosition <= input.length) {
                input.setSpan(span, startPosition, endPosition, Spanned.SPAN_EXCLUSIVE_INCLUSIVE)
            }
        }
        return input
    }
}