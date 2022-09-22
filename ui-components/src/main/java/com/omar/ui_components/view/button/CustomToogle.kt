package com.omar.ui_components.view.button

import android.content.Context
import android.content.res.TypedArray
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.constraintlayout.widget.ConstraintLayout
import com.omar.ui_components.R
import com.omar.ui_components.databinding.ViewCustomToggleBinding

class ToggleCustomView
@JvmOverloads
constructor(context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = R.attr.ToggleStyle) : ConstraintLayout(context, attrs, defStyleAttr) {

    private var binding = ViewCustomToggleBinding.inflate(LayoutInflater.from(context), this)

    var textToggle: CharSequence? = null
    set(value) {
        value?.let { binding.labelCard.text = it }
        field = value
    }

    var checkedToggle: Boolean = false
    set(value) {
        binding.switchCard.isChecked = value
        field = value
    }

    init {
        attrs?.let {
            val typedArray = context.obtainStyledAttributes(it, R.styleable.CustomToggle, 0, 0)
            applyAttrs(typedArray)
            typedArray.recycle()
        }
    }

    private fun applyAttrs(attrs: TypedArray) {
        with(attrs) {
            textToggle = getString(R.styleable.CustomToggle_textToggle)
            checkedToggle = getBoolean(R.styleable.CustomToggle_checkedToggle, checkedToggle)
        }
    }
}