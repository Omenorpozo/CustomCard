package com.omar.ui_components.view.cardview

import android.content.Context
import android.content.res.TypedArray
import android.graphics.drawable.Drawable
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.constraintlayout.widget.ConstraintLayout
import com.omar.ui_components.R
import com.omar.ui_components.databinding.ViewCustomCardBinding
import com.omar.ui_components.view.utils.CardUtils

class CustomCard
@JvmOverloads
constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = R.attr.CreditCardStyle
) : ConstraintLayout(context, attrs, defStyleAttr) {

    private var binding = ViewCustomCardBinding.inflate(LayoutInflater.from(context), this)

    var srcBackground: Drawable? = null
        set(value) {
            value?.let { binding.background.setImageDrawable(it) }
            field = value
        }

    var subtitleCard: CharSequence? = null
        set(value) {
            value?.let { binding.cardName.text = it }
            field = value
        }

    var numberCard: CharSequence? = null
        set(value) {
            value?.let { binding.creditCardNumber.text = CardUtils.formatCardNumber(it.toString()) }
            field = value
        }

    var cardHolder: CharSequence? = null
        set(value) {
            value?.let { binding.cardHolder.text = it }
            field = value
        }

    var dateExpired: CharSequence? = null
        set(value) {
            value?.let { binding.creditCardExpiration.text = it }
            field = value
        }

    init {
        attrs?.let {
            val typedArray = context.obtainStyledAttributes(it, R.styleable.CustomCreditCard, 0, 0)
            applyAttrs(typedArray)
            typedArray.recycle()
        }
    }

    private fun applyAttrs(attrs: TypedArray) {
        with(attrs) {
            srcBackground = getDrawable(R.styleable.CustomCreditCard_srcBackground)
            subtitleCard = getString(R.styleable.CustomCreditCard_subtitleCard)
            numberCard = getString(R.styleable.CustomCreditCard_numberCard)
            cardHolder = getString(R.styleable.CustomCreditCard_cardHolder)
            dateExpired = getString(R.styleable.CustomCreditCard_dateExpired)
        }
    }
}