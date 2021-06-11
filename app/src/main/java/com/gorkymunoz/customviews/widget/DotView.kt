package com.gorkymunoz.customviews.widget

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Typeface
import android.util.AttributeSet
import android.util.Log
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
) : View(context, attrs, defStyleAttr) {


    private var radius = 0.5f
    private var dotState = DotState.EMPTY
    private var strokeWidthView = 1.5f
        set(value) {
            strokePaint.strokeWidth = value
            field = value
        }

    fun setDotState(state: DotState) {
        dotState = state
        invalidate()
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
        radius = (min(width, height) / 4).toFloat()
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        Log.d("STATE", dotState.name)
        paint.color = when (dotState) {
            DotState.EMPTY -> Color.YELLOW
            DotState.FILLED -> Color.BLUE
            DotState.ERROR -> Color.RED
        }

        canvas.drawCircle(
            (width / 2).toFloat(),
            (height / 2).toFloat(),
            radius,
            paint
        )

/*
        if (dotState == DotState.EMPTY) {
            canvas.drawCircle((width / 2).toFloat(), (height / 2).toFloat(), radius, strokePaint)
        }
*/
    }

}