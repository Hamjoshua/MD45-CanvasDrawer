package com.example.md45_canvasdrawer

import android.content.Context
import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.util.Log
import android.view.MotionEvent
import android.view.View
import androidx.annotation.ColorInt
import androidx.annotation.Dimension


data class Point(
    val x: Float,
    val y: Float
)

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

    private var firstTap : Point? = null
    private var secondTap : Point? = null

    private var paint : Paint = Paint(0).apply {
        color = paintColor
        strokeWidth = paintSize
    }

    fun handleTap(x: Float, y: Float){
        if(firstTap == null){
            firstTap = Point(x, y)
        }
        else if(secondTap == null){
            secondTap = Point(x, y)
        }
        else{
            firstTap = secondTap
            secondTap = Point(x, y)
        }
    }

    override fun onTouchEvent(event: MotionEvent?): Boolean {
        Log.d("touch", "${event}")
        if(event != null){
            handleTap(event.getX(), event.getY())

            Log.d("touch", "${firstTap} ${secondTap}")
        }
        invalidate()
        return super.onTouchEvent(event)
    }
    override fun onDraw(canvas: Canvas){
        super.onDraw(canvas)

        canvas.apply {
            if(secondTap == null && firstTap == null){
                return
            }
            else if(secondTap == null){
                drawCircle(firstTap!!.x, firstTap!!.y, paintSize, paint)
                drawLine(firstTap!!.x, firstTap!!.y, firstTap!!.x, firstTap!!.y,
                    paint)
            }
            else{
                drawCircle(firstTap!!.x, firstTap!!.y, paintSize, paint)
                drawCircle(secondTap!!.x, secondTap!!.y, paintSize, paint)
                drawLine(firstTap!!.x, firstTap!!.y, secondTap!!.x, secondTap!!.y,
                    paint)
            }
        }

    }
}