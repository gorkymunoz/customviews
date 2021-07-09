package com.gorkymunoz.customviews.data

import com.gorkymunoz.customviews.enum.MediaType
import com.gorkymunoz.customviews.model.MediaItem


/**
 * Created by Gorky Muñoz on 6/7/2021.
 *
 * gorkymunoz@hotmail.com
 */
fun getItems(): List<MediaItem> = listOf(
    MediaItem("Title 1", "https://placekitten.com/200/300?image=1", MediaType.PHOTO),
    MediaItem("Title 2", "https://placekitten.com/200/300?image=2", MediaType.VIDEO),
    MediaItem("Title 3", "https://placekitten.com/200/300?image=3", MediaType.PHOTO),
    MediaItem("Title 4", "https://placekitten.com/200/300?image=4", MediaType.PHOTO),
    MediaItem("Title 5", "https://placekitten.com/200/300?image=5", MediaType.VIDEO),
    MediaItem("Title 6", "https://placekitten.com/200/300?image=6", MediaType.PHOTO),
    MediaItem("Title 7", "https://placekitten.com/200/300?image=7", MediaType.PHOTO),
    MediaItem("Title 8", "https://placekitten.com/200/300?image=8", MediaType.VIDEO),
    MediaItem("Title 9", "https://placekitten.com/200/300?image=9", MediaType.PHOTO),
    MediaItem("Title 10", "https://placekitten.com/200/300?image=10", MediaType.PHOTO),


    )