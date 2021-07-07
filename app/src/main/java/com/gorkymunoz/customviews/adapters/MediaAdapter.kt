package com.gorkymunoz.customviews.adapters

import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.gorkymunoz.customviews.R
import com.gorkymunoz.customviews.extensions.inflate
import com.gorkymunoz.customviews.extensions.loadUrl
import com.gorkymunoz.customviews.extensions.toast
import com.gorkymunoz.customviews.interfaces.Logger
import com.gorkymunoz.customviews.model.MediaItem


/**
 * Created by Gorky Muñoz on 6/7/2021.
 *
 * gorkymunoz@hotmail.com
 */
class MediaAdapter(private val items: List<MediaItem>) :
    RecyclerView.Adapter<MediaAdapter.MediaViewHolder>(), Logger {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MediaViewHolder {
        val view = parent.inflate(R.layout.view_media_item)
        return MediaViewHolder(view)
    }

    override fun onBindViewHolder(holder: MediaViewHolder, position: Int) {
        val mediaItem = items[position]
        logD(mediaItem.toString())
        holder.bind(mediaItem)
    }

    override fun getItemCount(): Int = items.size

    class MediaViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        private val title: TextView = view.findViewById(R.id.mediaTitle)
        private val thumb: ImageView = view.findViewById(R.id.mediaThumb)

        fun bind(mediaItem: MediaItem) {
            title.text = mediaItem.title
            thumb.loadUrl(mediaItem.url)

            itemView.setOnClickListener {
                toast(mediaItem.title)
            }
        }
    }
}