package com.omar.string_manager

import androidx.annotation.StringRes

interface StringLoader {

    fun getString(@StringRes stringResId: Int): CharSequence?

    fun getString(@StringRes stringResId: Int, vararg placeholders: StringReplacement): CharSequence?

    fun applyStyle(charSequence: CharSequence): CharSequence?
}