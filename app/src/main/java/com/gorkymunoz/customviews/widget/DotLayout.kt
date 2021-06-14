package com.gorkymunoz.customviews.widget

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.core.content.withStyledAttributes
import com.gorkymunoz.customviews.R
import com.gorkymunoz.customviews.enum.DotState
import com.gorkymunoz.customviews.interfaces.PinFilled


/**
 * Created by Gorky Muñoz on 10/6/2021.
 *
 * gorkymunoz@hotmail.com
 */
class DotLayout @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : LinearLayout(context, attrs, defStyleAttr) {


    private var listener: PinFilled? = null
    private var dotCount = 0
    private var emptyColor = 0
    private var filledColor = 0
    private var errorColor = 0
    private var pinText: String = ""

    fun setListener(listener: PinFilled) {
        this.listener = listener
    }

    fun addPinEntry(entryValue: String) {
        if (listener == null) {
            throw ExceptionInInitializerError("Call setListener first")
        }
        if (pinText.length < dotCount) {
            pinText += entryValue
            changeDotStatus(DotState.FILLED)
        }
        if (pinText.length == dotCount) {
            listener!!.pinCompleted(pinText)
        }
    }

    fun deletePinEntry() {
        if (pinText.isNotEmpty()) {
            val length = pinText.length
            pinText = pinText.substring(0, length - 1)
            changeDotStatus(DotState.EMPTY)
        }
    }

    private fun changeDotStatus(status: DotState) {
        val currentIndex = if (pinText.isEmpty()) 0 else pinText.length
        val dotView: DotView = getChildAt(currentIndex) as DotView
        dotView.setDotState(status)
    }

    fun showError() {
        for (i in 0 until childCount) {
            val dotView: DotView = getChildAt(i) as DotView
            dotView.setDotState(DotState.ERROR)
        }
    }

    init {
        orientation = HORIZONTAL


        context.withStyledAttributes(attrs, R.styleable.DotLayout) {
            dotCount = getInt(R.styleable.DotLayout_totalDots, 0)
            emptyColor = getColor(R.styleable.DotLayout_emptyColorDots, 0)
            filledColor = getColor(R.styleable.DotLayout_filledColorDots, 0)
            errorColor = getColor(R.styleable.DotLayout_errorColorDots, 0)
        }

        for (i in 0 until dotCount) {
            val id = View.generateViewId()
            val dotView = DotView(context)
            dotView.layoutParams = LayoutParams(
                0,
                ViewGroup.LayoutParams.MATCH_PARENT,
                1f
            )
            dotView.id = id
            dotView.setMaxElements(dotCount - 1)
            addView(dotView)
            dotView.setColors(
                emptyColor, filledColor, errorColor
            )
        }
    }

}