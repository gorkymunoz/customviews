package com.gorkymunoz.customviews.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.gorkymunoz.customviews.R
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
        val view = LayoutInflater
            .from(parent.context)
            .inflate(R.layout.view_media_item, parent, false)
        return MediaViewHolder(view)
    }

    override fun onBindViewHolder(holder: MediaViewHolder, position: Int) {
        val mediaItem = items[position]
        logD(mediaItem.toString())
        holder.bind(mediaItem)
    }

    override fun getItemCount(): Int = items.size

    class MediaViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private val title: TextView = itemView.findViewById(R.id.mediaTitle)
        private val thumb: ImageView = itemView.findViewById(R.id.mediaThumb)

        fun bind(mediaItem: MediaItem) {
            title.text = mediaItem.title
            Glide
                .with(itemView.context)
                .load(mediaItem.url)
                .into(thumb)
        }
    }
}