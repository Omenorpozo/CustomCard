package com.omar.ui_components.view.footer

import android.content.Context
import android.content.res.TypedArray
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.constraintlayout.widget.ConstraintLayout
import com.omar.ui_components.R
import com.omar.ui_components.databinding.ViewCustomFooterBinding

class CustomFooter
@JvmOverloads
constructor(context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = R.attr.FooterStyle) : ConstraintLayout(context, attrs, defStyleAttr) {

    private var binding = ViewCustomFooterBinding.inflate(LayoutInflater.from(context), this)

    var textLeft: CharSequence? = null
    set(value) {
        value?.let { binding.toggle.textToggle = it }
        field = value
    }

    var textRight: CharSequence? = null
    set(value) {
        value?.let { binding.buttonControl.textButton = it }
        field = value
    }

    var enableCard: Boolean = true
    set(value) {
        binding.toggle.checkedToggle = value
        field = value
    }

    init {
        attrs?.let {
            val typedArray = context.obtainStyledAttributes(it, R.styleable.CustomFooter, 0, 0)
            applyAttrs(typedArray)
            typedArray.recycle()
        }
    }

    private fun applyAttrs(attrs: TypedArray) {
        with(attrs) {
            textLeft = getString(R.styleable.CustomFooter_textLeft)
            textRight = getString(R.styleable.CustomFooter_textRight)
            enableCard = getBoolean(R.styleable.CustomFooter_enableCard, true)
        }
    }
}