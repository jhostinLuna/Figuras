package com.ejemplo.figuras.views

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.view.View

class CircleView: View {
    private val paint: Paint = Paint()
    constructor(context: Context): super(context){
        init()
    }

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {
        init()
    }
    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {
        init()
    }
    private fun init() {
        paint.color = Color.RED
        paint.strokeWidth = 10f
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        // Aquí puedes usar los métodos de Canvas para dibujar lo que quieras
        // Por ejemplo, vamos a dibujar un círculo en el centro de la vista
        val x = (width / 2).toFloat()
        val y = (height / 2).toFloat()
        val radius = Math.min(width, height) / 2f
        canvas.drawCircle(x, y, radius, paint)
    }
}