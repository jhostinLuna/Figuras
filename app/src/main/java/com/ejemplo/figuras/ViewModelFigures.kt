package com.ejemplo.figuras

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class ViewModelFigures: ViewModel() {

    private val _selectedFigure = MutableStateFlow<Figure?>(null)
    val selectedFigure: StateFlow<Figure?> = _selectedFigure

    fun updateFigure(figure: String,paramA: String, paramB: String = "0"){
        when(figure) {
            Constants.SQUARE_VIEW -> {

                _selectedFigure.value = Square(Constants.SQUARE_VIEW).apply {
                    sideA = paramA.toFloat()
                    sideB = paramB.toFloat()
                }
            }
            Constants.TRIANGLE_VIEW -> {
                _selectedFigure.value = Triangle(Constants.SQUARE_VIEW).apply {
                    base = paramA.toFloat()
                    height = paramB.toFloat()
                }
            }
            else -> {
                _selectedFigure.value = Circle(Constants.SQUARE_VIEW).apply {
                    radio = paramA.toFloat()
                }
            }

        }
    }
}