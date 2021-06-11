package com.gorkymunoz.customviews.widget

import android.content.Context
import android.util.AttributeSet
import android.view.ViewGroup
import android.widget.LinearLayout


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


    val dotCount = 5

    init {
        orientation = HORIZONTAL
        for (i in 1..dotCount) {
            val dotView = DotView(context)
            dotView.layoutParams = LayoutParams(
                0,
                ViewGroup.LayoutParams.MATCH_PARENT,
                1f
            )
            addView(dotView)
        }
    }

}