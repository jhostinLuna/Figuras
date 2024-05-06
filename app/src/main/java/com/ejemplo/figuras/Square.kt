package com.ejemplo.figuras

class Square (name: String): Figure(name) {
    var sideA: Float = 0.0f
    var sideB: Float = 0.0f
    override fun getArea(): Float = (sideA * sideB)

}