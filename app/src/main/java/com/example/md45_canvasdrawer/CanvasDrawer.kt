package com.example.md45_canvasdrawer

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.util.Log
import android.view.MotionEvent
import android.view.View
import androidx.annotation.ColorInt
import androidx.annotation.Dimension

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
    }

    private var fingerX : Float = 0f
    private var fingerY : Float = 0f

    private var paint : Paint = Paint(0).apply {
        color = paintColor
        strokeWidth = paintSize
    }

    fun setColor(colorInt: Int){
        paintColor = colorInt
    }

    override fun onTouchEvent(event: MotionEvent?): Boolean {
        Log.d("touch", "${event}")
        if(event != null){
            fingerX = event.getX()
            fingerY = event.getY()
            Log.d("touch", "${fingerX} ${fingerY}")
        }
        invalidate()
        return super.onTouchEvent(event)
    }
    override fun onDraw(canvas: Canvas){
        super.onDraw(canvas)
        canvas.apply {
            Log.d("canvas-drawer", "Point on ${fingerX} ${fingerY}")
            // canvas.drawPoint(fingerX, fingerY, paint)
            canvas.drawCircle(fingerX, fingerY, paintSize, paint)
        }
    }
}