package com.omar.string_manager

class StringReplacement(
    val placeholder: Placeholder
) {

    enum class Placeholder(val value: String) {
        VALUE("{{VALUE}}")
    }

    var replacement: CharSequence? = null

    fun setReplacement(replacement: CharSequence): StringReplacement {
        this.replacement = replacement
        return this
    }
}