package com.example.lab1.view.custom_views

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
    private val padding = 1f

    var n: Int = 1

    var solution: Solution? = null

    override fun onDraw(canvas: Canvas) {

        val delta = width.toFloat() / n
        val startX = padding
        val startY = padding
        val endX = width.toFloat() - padding
        val endY = height.toFloat() - padding

        p.color = Color.BLACK
        p.textSize = 100f
        p.strokeWidth = 3f
        p.style = STROKE
        canvas.drawRect(startX, startY, endX, endY, p)
        for (line in 1 until n) {
            canvas.drawLine(startX, line * delta, endX, line * delta, p)
            canvas.drawLine(line * delta, startY, line * delta, endY, p)
        }

        fillCells(canvas, delta)

        solution?.let {
            p.color = Color.RED
            p.style = FILL
            for ((line, index) in it.arrangement.withIndex()) {
                canvas.drawCircle(
                    (index + 1) * delta - delta / 2,
                    (line + 1f) * delta - delta / 2,
                    (delta * 0.75f) / 2,
                    p
                )
            }
        }
    }

    private fun fillCells(canvas: Canvas, delta: Float) {
        p.color = Color.argb(0.3f, 0f, 0f, 0f)
        p.style = FILL

        for (row in 0 until n) {
            var startCol: Int
            if (row.mod(2) == 0) {
                startCol = 1
            } else startCol = 0
            for (col in startCol until n step 2) {
                canvas.drawRect(delta * col, delta * row, delta * col + delta, delta * row + delta, p)
            }
        }
    }
}