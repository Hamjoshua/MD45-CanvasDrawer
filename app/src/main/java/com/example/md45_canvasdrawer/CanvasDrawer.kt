package com.example.md45_canvasdrawer

import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View

class CanvasDrawerView(context: Context, attrs: AttributeSet) : View(context, attrs) {
    private var fingerX : Float = 0f
    private var fingerY : Float = 0f
    private lateinit var paint : Paint
    init {
        paint = Paint(0).apply {
            color = 0x101010
        }
    }
    override fun onTouchEvent(event: MotionEvent?): Boolean {
        if(event != null){
            fingerX = event.getX()
            fingerY = event.getY()
            invalidate()
        }

        return super.onTouchEvent(event)
    }
    override fun onDraw(canvas: Canvas){
        super.onDraw(canvas)
        canvas.apply {
            canvas.drawPoint(fingerX, fingerY, paint)
        }
    }
}