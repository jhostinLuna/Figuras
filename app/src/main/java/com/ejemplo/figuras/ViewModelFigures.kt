package com.ejemplo.figuras

import androidx.lifecycle.ViewModel

class ViewModelFigures: ViewModel() {

    fun getFigure(figure: String,paramA: String, paramB: String = "0"): Figure{
        return when(figure) {
            Constants.SQUARE_VIEW -> {
                Square(Constants.SQUARE_VIEW).apply {
                    sideA = paramA.toFloat()
                    sideB = paramB.toFloat()
                }
            }
            Constants.TRIANGLE_VIEW -> {
                Triangle(Constants.SQUARE_VIEW).apply {
                    base = paramA.toFloat()
                    height = paramB.toFloat()
                }
            }
            else -> {
                Circle(Constants.SQUARE_VIEW).apply {
                    radio = paramA.toFloat()
                }
            }

        }
    }
}