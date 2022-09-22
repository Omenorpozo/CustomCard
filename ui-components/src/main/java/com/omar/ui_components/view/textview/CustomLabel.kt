package com.omar.ui_components.view.textview

import android.content.Context
import android.content.res.TypedArray
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.annotation.ColorRes
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat
import com.omar.ui_components.R
import com.omar.ui_components.databinding.ViewCustomLabelBinding

class CustomLabel
@JvmOverloads
constructor(context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = R.attr.LabelStyle) : ConstraintLayout(context, attrs, defStyleAttr) {

    private var binding = ViewCustomLabelBinding.inflate(LayoutInflater.from(context), this)

    @ColorRes
    var drawableResColor: Int = R.color.yellow
    set(value) {
        setBadgeBackgroundColor(value)
        field = value
    }

    var textTitle: CharSequence? = null
    set(value) {
        value?.let { binding.title.text = it }
        field = value
    }

    var textSubtitle: CharSequence? = null
    set(value) {
        value?.let { binding.subtitle.text = it }
        field = value
    }

    init {
        attrs?.let {
            val typedArray = context.obtainStyledAttributes(it, R.styleable.CustomLabel, 0, 0)
            applyAttrs(typedArray)
            typedArray.recycle()
        }
    }

    private fun applyAttrs(attrs: TypedArray) {
        with(attrs) {
            drawableResColor = getResourceId(R.styleable.CustomLabel_colorBadge, drawableResColor)
            textTitle = getString(R.styleable.CustomLabel_textTitle)
            textSubtitle = getString(R.styleable.CustomLabel_textSubtitle)
        }
    }

    private fun setBadgeBackgroundColor(drawableResColor: Int) {
        with(binding) {
            badge.setColorFilter(ContextCompat.getColor(context, drawableResColor))
        }
    }
}