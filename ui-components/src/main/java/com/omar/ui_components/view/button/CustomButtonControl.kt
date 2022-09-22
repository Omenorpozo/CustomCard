package com.omar.ui_components.view.button

import android.content.Context
import android.content.res.TypedArray
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.constraintlayout.widget.ConstraintLayout
import com.omar.ui_components.R
import com.omar.ui_components.databinding.ViewCustomButtonControlBinding

class CustomButtonControl
@JvmOverloads
constructor(context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = R.attr.ButtonControlStyle) : ConstraintLayout(context, attrs, defStyleAttr) {

    private var binding = ViewCustomButtonControlBinding.inflate(LayoutInflater.from(context), this)

    var textButton: CharSequence? = null
    set(value) {
        value?.let { binding.labelControl.text = it }
        field = value
    }

    init {
        attrs?.let {
            val typedArray = context.obtainStyledAttributes(it, R.styleable.ButtonControl, 0, 0)
            applyAttrs(typedArray)
            typedArray.recycle()
        }
    }

    private fun applyAttrs(attrs: TypedArray) {
        with(attrs) {
            textButton = getString(R.styleable.ButtonControl_textButton)
        }
    }
}