package com.gorkymunoz.customviews.extensions

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.annotation.LayoutRes
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide


/**
 * Created by Gorky Muñoz on 10/6/2021.
 * Extensions for view
 * gorkymunoz@hotmail.com
 */

fun View.dpToPx(): Float {
    return 2 * resources.displayMetrics.density + 0.5f
}

fun RecyclerView.ViewHolder.toast(message: String) {
    itemView.context.toast(message)
}

fun ViewGroup.inflate(@LayoutRes layoutRes: Int, attachToRoot: Boolean = false): View =
    LayoutInflater
        .from(context)
        .inflate(layoutRes, this, attachToRoot)

fun ImageView.loadUrl(urlToLoad: String) {
    Glide
        .with(this)
        .load(urlToLoad)
        .into(this)
}