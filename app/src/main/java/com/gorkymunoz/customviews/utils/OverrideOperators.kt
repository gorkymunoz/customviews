package com.gorkymunoz.customviews.utils

import android.view.View
import android.view.ViewGroup


/**
 * Created by Gorky Muñoz on 16/7/2021.
 *
 * Class for examples of operators overriding
 */
operator fun ViewGroup.get(index: Int): View = getChildAt(index)

fun test(viewGroup: ViewGroup) {
    viewGroup[0]
    // same as
    viewGroup.getChildAt(0)

}