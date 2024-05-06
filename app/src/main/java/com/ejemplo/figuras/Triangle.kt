package com.ejemplo.figuras

class Triangle (name: String): Figure(name) {

    var base: Float = 0.0f
    var height: Float = 0.0f
    override fun getArea(): Float = ((base * height)/2)
}