package com.gorkymunoz.customviews.widget

import android.animation.ArgbEvaluator
import android.animation.ObjectAnimator
import android.animation.ValueAnimator
import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Typeface
import android.util.AttributeSet
import android.view.View
import com.gorkymunoz.customviews.enum.DotState
import kotlin.math.min


/**
 * Created by Gorky Muñoz on 4/6/2021.
 * Custom View to show a dot
 * gorkymunoz@hotmail.com
 */
class DotView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : View(context, attrs, defStyleAttr), ValueAnimator.AnimatorUpdateListener {


    private var radius = 0.5f
    private var maxElements = 0
    private val evaluator = ArgbEvaluator()
    private var dotState = DotState.EMPTY
    private var strokeWidthView = 1.5f
        set(value) {
            strokePaint.strokeWidth = value
            field = value
        }

    fun setMaxElements(totalElements: Int) {
        maxElements = totalElements
    }

    fun setDotState(state: DotState) {
        dotState = state

        val prevColor = paint.color
        val color = when (dotState) {
            DotState.EMPTY -> Color.TRANSPARENT
            DotState.FILLED -> Color.BLUE
            DotState.ERROR -> Color.RED
        }

        val animator: ObjectAnimator =
            ObjectAnimator.ofObject(paint, "color", evaluator, prevColor, color)
        animator.duration = 500
        animator.addUpdateListener(this)
        animator.start()
    }

    private val paint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        style = Paint.Style.FILL
        strokeWidth = this.strokeMiter
        textAlign = Paint.Align.CENTER
        typeface = Typeface.create("", Typeface.BOLD)
    }

    private val strokePaint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        style = Paint.Style.STROKE
        strokeWidth = strokeWidthView
        color = Color.parseColor("#707070")
    }

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)
        val divisor: Double = if (maxElements == 0) (2.0 * 1.4) else (maxElements - 1).toDouble()
        radius = (min(width, height) / divisor).toFloat()
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)

        canvas.drawCircle(
            (width / 2).toFloat(),
            (height / 2).toFloat(),
            radius,
            if (dotState == DotState.EMPTY) strokePaint else paint
        )

    }

    override fun onAnimationUpdate(animation: ValueAnimator?) {
        invalidate()
    }

}