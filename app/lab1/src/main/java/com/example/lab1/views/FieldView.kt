package com.example.lab1.views

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Paint.Style.STROKE
import android.util.AttributeSet
import android.view.View

class FieldView(context: Context, attrs: AttributeSet?) : View(context, attrs) {

    private val p = Paint()

    var padding = 10f
    var radius = 50f
    var n: Int = 5

    override fun onDraw(canvas: Canvas) {

        val delta = (width / n).toFloat()
        val startX = padding
        val startY = padding
        val endX = width - padding
        val endY = height - padding

        p.color = Color.BLACK
        p.textSize = 100f
        p.strokeWidth = 10f
        p.style = STROKE
//        canvas.drawText("${width / n}", 100f, 100f, p)
        canvas.drawRoundRect(startX, startY, endX, endY, radius, radius, p)
        for (line in 1 until n) {
            canvas.drawLine(startX, line * delta, endX, line * delta, p)
            canvas.drawLine(line * delta, startY, line * delta, endY, p)
        }
    }
}