package com.omar.string_manager

import android.content.Context
import android.text.SpannableString
import androidx.annotation.StringRes
import java.util.*
import java.util.regex.Matcher
import java.util.regex.Pattern
import kotlin.collections.ArrayList
import kotlin.collections.HashMap

class StringManager(private val context: Context) : StringLoader {

    private val placeholderPattern = Pattern.compile("\\{\\{([^\\{\\}:]+):?([^\\{\\}]+)?\\}\\}")

    override fun getString(@StringRes stringResId: Int) = applyStyle(context.getString(stringResId))

    override fun getString(@StringRes stringResId: Int, vararg placeholders: StringReplacement) =
        getStringCustom(context.getString(stringResId), *placeholders)?.let { applyStyle(it) }

    override fun applyStyle(charSequence: CharSequence): CharSequence? {
        if (charSequence == null) return null

        val styles = getStyles(charSequence)
        return getStyledSpannable(charSequence, styles).toString()
    }

    private fun getStringCustom(
        originalString: String,
        vararg placeholders: StringReplacement?
    ): String? {
        var formattedString = originalString
        placeholders?.let {
            for (stringReplacement in placeholders) {
                stringReplacement?.let {
                    formattedString = formattedString.replaceFirst(
                        Pattern.quote(stringReplacement.placeholder.value).toRegex(),
                        if (stringReplacement.replacement != null) Matcher.quoteReplacement(
                            stringReplacement.replacement.toString()
                        ) else ""
                    )
                }
            }
        }
        return formattedString
    }

    private fun getStyles(charSequence: CharSequence): ArrayList<StringStyling> {
        val stylingMap = HashMap<String, StringStyling>()
        val styles = ArrayList<StringStyling>()
        var tempCharSequence = charSequence

        var matcher = placeholderPattern.matcher(tempCharSequence)
        while (matcher.find()) {
            var tag = matcher.group(1)
            val extra = matcher.group(2)
            val position = matcher.start()

            tag?.let {
                if (tag.startsWith("/")) {
                    tag = tag.replace("/", "")
                    if (stylingMap.containsKey(tag)) {
                        val stringStyling = stylingMap[tag]
                        stringStyling?.let {
                            it.endPosition = position
                            styles.add(it)
                            stylingMap.remove(tag)
                        }
                    } else run {
                        var placeholder: StringStyling.StylePlaceHolder = try {
                            StringStyling.StylePlaceHolder.valueOf(tag.uppercase(Locale.getDefault()))
                        } catch (ex: IllegalArgumentException) {
                            StringStyling.StylePlaceHolder.UNKOWN
                        }
                        stylingMap.put(
                            tag,
                            StringStyling(
                                stylePlaceHolder = placeholder,
                                startPosition = position,
                                extra = extra
                            )
                        )
                    }
                }
            }
            tempCharSequence = matcher.replaceFirst("")
            matcher = placeholderPattern.matcher(tempCharSequence)
        }

        for (tag in stylingMap.keys) {
            val stringStyling = stylingMap[tag]
            stringStyling?.let {
                it.endPosition = tempCharSequence.length
                styles.add(it)
            }
        }
        return styles
    }

    private fun getStyledSpannable(
        charSequence: CharSequence?,
        styles: List<StringStyling>
    ): SpannableString {
        val matcher = placeholderPattern.matcher(charSequence)
        var spannableString = SpannableString(matcher.replaceAll(""))
        for (style in styles) {
            spannableString = style.apply(spannableString)
        }
        return spannableString
    }
}