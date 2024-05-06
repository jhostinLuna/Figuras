package com.ejemplo.figuras

class Circle (name:String): Figure(name) {
    companion object {
        const val PI = 3.141592F
    }
    var radio: Float = 0.0f
    override fun getArea(): Float = PI * radio
}