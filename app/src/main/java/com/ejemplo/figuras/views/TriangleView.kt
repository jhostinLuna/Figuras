package com.ejemplo.figuras.views

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Path
import android.util.AttributeSet
import android.view.View

class TriangleView : View {
    private val paint: Paint = Paint()
    private val path: Path = Path()
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
        // Por ejemplo, vamos a dibujar un triángulo
        path.moveTo(width / 2f, 0f) // Parte superior del triángulo
        path.lineTo(0f, height.toFloat()) // Parte inferior izquierda del triángulo
        path.lineTo(width.toFloat(), height.toFloat()) // Parte inferior derecha del triángulo
        path.close() // Cierra el camino para formar el triángulo
        canvas.drawPath(path, paint)
    }
}