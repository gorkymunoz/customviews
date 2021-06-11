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
    var pinText: String = ""
        set(value) {
            if (pinText.length < dotCount) {
                field += value
                val dotView: DotView = getChildAt(pinText.length - 1) as DotView
                dotView.setDotState(DotState.FILLED)
            }
            if (pinText.length == dotCount) {
                if (listener != null) {
                    listener!!.pinCompleted(pinText)
                } else {
                    throw ExceptionInInitializerError("Initialize PinFilled listener")
                }
            }
        }

    fun setListener(listener: PinFilled) {
        this.listener = listener
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