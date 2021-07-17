package com.gorkymunoz.customviews.adapters

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.gorkymunoz.customviews.R
import com.gorkymunoz.customviews.databinding.ViewMediaItemBinding
import com.gorkymunoz.customviews.enum.MediaType
import com.gorkymunoz.customviews.extensions.inflate
import com.gorkymunoz.customviews.extensions.loadUrl
import com.gorkymunoz.customviews.interfaces.Logger
import com.gorkymunoz.customviews.model.MediaItem
import kotlin.properties.Delegates


/**
 * Created by Gorky Muñoz on 6/7/2021.
 *
 * gorkymunoz@hotmail.com
 */
private typealias Listener = (MediaItem) -> Unit

class MediaAdapter(
    items: List<MediaItem> = emptyList(),
    private val clickListener: Listener
) :
    RecyclerView.Adapter<MediaAdapter.MediaViewHolder>(), Logger {

    var items: List<MediaItem> by Delegates.observable(items) { _, _, _ ->
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MediaViewHolder {
        // First way -- initialize the binding here
        //val binding = ViewMediaItemBinding.inflate(LayoutInflater.from(parent.context))
        //return MediaViewHolder(binding.root)
        // Second way -- initialize binding in viewHolder
        val view = parent.inflate(R.layout.view_media_item)
        return MediaViewHolder(view)

    }

    override fun onBindViewHolder(holder: MediaViewHolder, position: Int) {
        val mediaItem = items[position]
        holder.bind(mediaItem, clickListener)
    }

    override fun getItemCount(): Int = items.size

    class MediaViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        private val binding = ViewMediaItemBinding.bind(view)

        fun bind(mediaItem: MediaItem, clickListener: Listener) {

            with(binding) {
                mediaTitle.text = mediaItem.title
                mediaThumb.loadUrl(mediaItem.url)
                mediaVideoIndicator.visibility = when (mediaItem.type) {
                    MediaType.PHOTO -> View.GONE
                    MediaType.VIDEO -> View.VISIBLE
                }
                root.setOnClickListener {
                    clickListener(mediaItem)
                }
            }
        }
    }
}