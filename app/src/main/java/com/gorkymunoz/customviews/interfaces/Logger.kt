package com.gorkymunoz.customviews.interfaces

import android.util.Log


/**
 * Created by Gorky Muñoz on 6/7/2021.
 *
 * gorkymunoz@hotmail.com
 */
interface Logger {

    val tag: String
        get() = javaClass.simpleName

    fun logD(message: String) {
        Log.d(tag, message)
    }

    fun logE(message: String) {
        Log.e(tag, message)
    }

    fun logE(message: String, error: Throwable) {
        Log.e(tag, message, error)
    }

}