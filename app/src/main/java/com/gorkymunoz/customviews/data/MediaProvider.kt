package com.gorkymunoz.customviews.data

import androidx.annotation.WorkerThread
import com.gorkymunoz.customviews.enum.MediaType
import com.gorkymunoz.customviews.model.MediaItem


/**
 * Created by Gorky Muñoz on 6/7/2021.
 *
 * gorkymunoz@hotmail.com
 */

object MediaProvider {

    @WorkerThread
    fun getItems(): List<MediaItem> {
        Thread.sleep(2000)
        return (1..15).map {
            val type = if (it % 3 == 0) MediaType.VIDEO else MediaType.PHOTO
            MediaItem(
                "Title $it",
                "https://placekitten.com/200/300?image=$it",
                type
            )
        }
    }
}