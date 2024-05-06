package com.ejemplo.figuras

abstract class Figure (val name: String){
    val areas: Float get() = getArea()
    abstract fun getArea(): Float

}