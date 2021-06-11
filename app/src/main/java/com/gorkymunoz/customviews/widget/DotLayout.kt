package com.gorkymunoz.customviews.widget

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
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

    fun setListener(listener: PinFilled) {
        this.listener = listener
    }

    fun showError() {
        for (i in 0 until childCount) {
            val dotView: DotView = getChildAt(i) as DotView
            dotView.setDotState(DotState.ERROR)
        }
    }

    val dotCount = 6
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

    init {
        orientation = HORIZONTAL
        for (i in 1..dotCount) {
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
        }
    }

}