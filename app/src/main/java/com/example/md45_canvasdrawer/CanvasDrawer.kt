package com.example.md45_canvasdrawer

import android.content.Context
import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Path
import android.util.AttributeSet
import android.util.Log
import android.view.MotionEvent
import android.view.View
import androidx.annotation.ColorInt
import androidx.annotation.Dimension


class DrawingPath {
    var path = Path()
    var color: Int = Color.BLACK
    var size: Float = 10f
}

class CanvasDrawerView(context: Context, attrs: AttributeSet) : View(context, attrs) {
    @ColorInt
    var paintColor = Color.BLACK
        set(value){
            field = value
            paint.color = value
        }
    @Dimension
    var paintSize = 20f
        set(value){
            field = value
            paint.strokeWidth = value
            paint.textSize = value
    }

    private var paint : Paint = Paint(0).apply {
        color = paintColor
        strokeWidth = paintSize
    }

    private var currentPath: DrawingPath = DrawingPath()
    private var drawingPaths: MutableList<DrawingPath> = mutableListOf()

    override fun onTouchEvent(event: MotionEvent): Boolean {
        when(event.action){
            MotionEvent.ACTION_DOWN -> {
                currentPath = DrawingPath().apply {
                    size = paintSize
                    color = paintColor
                }
                currentPath.path.moveTo(event.x, event.y)
                drawingPaths.add(currentPath)
                Log.d("Drawer", "New path: ${currentPath}")
            }
            MotionEvent.ACTION_MOVE -> {
                currentPath.path.lineTo(event.x, event.y)
                Log.d("Drawer", "Paths: ${drawingPaths}")
            }
        }
        invalidate()
        return true
    }

    override fun onDraw(canvas: Canvas){
        super.onDraw(canvas)

        drawingPaths.forEach{
            paint.apply {
                paintColor = it.color
                paintSize = it.size
            }
            canvas.drawPath(it.path, paint)
        }
    }
}