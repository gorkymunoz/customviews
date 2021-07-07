package com.gorkymunoz.customviews.extensions

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.widget.Toast


/**
 * Created by Gorky Muñoz on 6/7/2021.
 *
 * gorkymunoz@hotmail.com
 */

fun Context.toast(message: String, duration: Int = Toast.LENGTH_LONG) {
    Toast.makeText(this, message, duration).show()
}

inline fun <reified T : Activity> Context.startActivity() {
    val intent = Intent(this, T::class.java)
}