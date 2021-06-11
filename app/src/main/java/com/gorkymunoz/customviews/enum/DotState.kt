package com.gorkymunoz.customviews.enum


/**
 * Created by Gorky Muñoz on 4/6/2021.
 * States for dot
 * gorkymunoz@hotmail.com
 */
enum class DotState {
    EMPTY,
    FILLED,
    ERROR;

    fun next() = when(this){
        EMPTY -> FILLED
        FILLED -> ERROR
        ERROR -> EMPTY
    }
}