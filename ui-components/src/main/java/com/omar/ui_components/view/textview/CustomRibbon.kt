package com.omar.ui_components.view.textview

import android.content.Context
import android.content.res.TypedArray
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Path
import android.util.AttributeSet
import androidx.appcompat.widget.AppCompatTextView
import com.omar.ui_components.R

class CustomRibbon
@JvmOverloads
constructor(context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = R.attr.RibbonViewStyle) : AppCompatTextView(context, attrs, defStyleAttr) {

    private val fillPaint = Paint(Paint.ANTI_ALIAS_FLAG)
    private val strokePaint = Paint(Paint.ANTI_ALIAS_FLAG)
    private val ribbonViewPath = Path()
    private val strokePath = Path()

    private var strokeWidth: Int = 0

    private var initialPadding = strokeWidth / 2

    private var ribbonFillColor = DEFAULT_FILL_COLOR
    set(value) {
        field = value
        fillPaint.color = value
        invalidate()
    }

    private var ribbonStrokeColor = DEFAULT_STROKE_COLOR
    set(value) {
        field = value
        strokePaint.color = value
        invalidate()
    }

    private var flagWidth: Float = 0f

    private var arcLength: Int = 0
    set(value) {
        field = value
        invalidate()
    }

    private var gravity = Gravity.RIGHT
    set(value) {
        field = value
        invalidate()
    }

    companion object {
        const val DEFAULT_ARC_LENGTH = 10
        const val DEFAULT_FILL_COLOR = Color.RED
        const val DEFAULT_STROKE_COLOR = Color.YELLOW
        const val DEFAULT_SPACING = 20
    }

    enum class Gravity {
        LEFT, RIGHT
    }

    init {
        attrs?.let {
            val typedArray = context.obtainStyledAttributes(it, R.styleable.RibbonView, 0, 0)
            applyAttrs(typedArray)
            typedArray.recycle()
        }
    }

    private fun applyAttrs(attrs: TypedArray) {
        arcLength = DEFAULT_ARC_LENGTH
        with(attrs) {
            ribbonFillColor = getColor(R.styleable.RibbonView_ribbonFillColor, Color.RED)
            ribbonStrokeColor = getColor(R.styleable.RibbonView_ribbonStrokeColor, Color.YELLOW)
            strokeWidth = getDimensionPixelSize(R.styleable.RibbonView_ribbonStrokeWidth, 0)
            gravity = getGravity(getInt(R.styleable.RibbonView_ribbonGravity,0))
        }
    }

    private fun getGravity(gravity: Int): Gravity = if (gravity == 0) Gravity.LEFT else Gravity.RIGHT

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
        flagWidth = (measuredWidth / arcLength).toFloat()
        initialPadding = strokeWidth / 2
        setMeasuredDimension((measuredWidth + flagWidth + DEFAULT_SPACING).toInt(), measuredHeight + DEFAULT_SPACING)
    }

    override fun onDraw(canvas: Canvas) {
        val width = measuredWidth.toFloat()
        val height = measuredHeight.toFloat()
        if (gravity == Gravity.LEFT) {
            canvas.translate(-(flagWidth + DEFAULT_SPACING), 0f)
        }
        drawTagPath(canvas, width, height)
        drawStrokePath(canvas, width, height)
        canvas.translate(flagWidth, 0f)
        super.onDraw(canvas)
    }

    private fun drawStrokePath(canvas: Canvas, width: Float, height: Float) {
        strokePath.moveTo(initialPadding.toFloat(), initialPadding.toFloat())
        strokePath.lineTo(width - initialPadding, initialPadding.toFloat())
        if (gravity != Gravity.RIGHT) {
            strokePath.lineTo(width - flagWidth - initialPadding, (height / 2) + initialPadding)
        }
        strokePath.lineTo(width - initialPadding, height - initialPadding)
        strokePath.lineTo(initialPadding.toFloat(), height - initialPadding)
        if (gravity != Gravity.LEFT) {
            strokePath.lineTo(flagWidth + initialPadding, (height / 2) + initialPadding)
        }
        strokePath.close()
        if (strokeWidth > 0) {
            canvas.drawPath(strokePath, strokePaint)
        }
    }

    private fun drawTagPath(canvas: Canvas, width: Float, height: Float) {
        ribbonViewPath.moveTo(0f, 0f)
        ribbonViewPath.lineTo(width, 0f)
        if (gravity != Gravity.RIGHT) {
            ribbonViewPath.lineTo(width - flagWidth, height / 2)
        }
        ribbonViewPath.lineTo(width, height)
        ribbonViewPath.lineTo(0f, height)
        if (gravity != Gravity.LEFT) {
            ribbonViewPath.lineTo(flagWidth, height / 2)
        }
        ribbonViewPath.close()
        canvas.drawPath(ribbonViewPath, fillPaint)
    }
}