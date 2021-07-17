package com.gorkymunoz.customviews.data

import com.gorkymunoz.customviews.enum.MediaType


/**
 * Created by Gorky Muñoz on 16/7/2021.
 * Sealed class to filter items
 *
 */
sealed class FilterMediaItem {
    class ByType(val type: MediaType) : FilterMediaItem()
    object None : FilterMediaItem()
}
