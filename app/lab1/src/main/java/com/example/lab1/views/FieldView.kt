package com.example.lab1.views

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Paint.Style.FILL
import android.graphics.Paint.Style.STROKE
import android.util.AttributeSet
import android.view.View
import com.example.lab1.domain.models.Solution

class FieldView(context: Context, attrs: AttributeSet?) : View(context, attrs) {

    private val p = Paint()

    var n: Int = 1

    var solution: Solution? = null

    override fun onDraw(canvas: Canvas) {

        val delta = width.toFloat() / n
        val startX = 0f
        val startY = 0f
        val endX = width.toFloat()
        val endY = height.toFloat()

        p.color = Color.BLACK
        p.textSize = 100f
        p.strokeWidth = 5f
        p.style = STROKE
        canvas.drawRect(startX, startY, endX, endY, p)
        for (line in 1 until n) {
            canvas.drawLine(startX, line * delta, endX, line * delta, p)
            canvas.drawLine(line * delta, startY, line * delta, endY, p)
        }
        solution?.let {
            p.color = Color.RED
            p.style = FILL
            for ((line, index) in it.arrangement.withIndex()) {
                canvas.drawCircle(
                    (index + 1) * delta - delta / 2,
                    (line + 1f) * delta - delta / 2,
                    delta / 2 - 2f,
                    p
                )
            }
        }
    }
}