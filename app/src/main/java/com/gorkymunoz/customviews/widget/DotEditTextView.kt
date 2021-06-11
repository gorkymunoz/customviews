package com.gorkymunoz.customviews.widget

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.PointF
import android.util.AttributeSet
import android.util.Log
import android.view.Gravity
import androidx.appcompat.widget.AppCompatEditText
import kotlin.math.min


/**
 * Created by Gorky Muñoz on 7/6/2021.
 *
 * gorkymunoz@hotmail.com
 */
class DotEditTextView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : AppCompatEditText(context, attrs, defStyleAttr) {

    private var radius: Float = 32f
    val dotCount = 6
    var space = 24f
    val paint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        color = Color.GREEN
        style = Paint.Style.FILL
        gravity = Gravity.CENTER_HORIZONTAL
    }

    val point: PointF = PointF()

    init {
        val multi = context.resources.displayMetrics.density
        space *= multi
    }

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)
        radius = min((width / dotCount).toFloat(), radius)
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        print(point.x)
        Log.d("RADIUS", "$radius")
        val availableWidth = width - paddingStart - paddingEnd
        var startX = paddingLeft.toFloat() + radius

        for (i in 1..dotCount) {
            canvas.drawCircle(startX, (height / 2).toFloat(), radius, paint)
            startX += radius * 3
        }
    }

}