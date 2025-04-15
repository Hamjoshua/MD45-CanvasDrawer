package com.example.md45_canvasdrawer

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Path
import android.net.Uri
import android.util.AttributeSet
import android.util.Log
import android.view.MotionEvent
import android.view.View
import androidx.annotation.ColorInt
import androidx.annotation.Dimension
import androidx.core.graphics.createBitmap
import java.io.File
import java.io.FileOutputStream


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

    fun saveDrawing(){
        val bitmap = createBitmap(width, height,
            Bitmap.Config.ARGB_8888)
        val canvas = Canvas(bitmap)

        draw(canvas)

        val filename = "drawing_${System.currentTimeMillis()}.png"
        val file = File(context.getExternalFilesDir(null),
            filename)
        FileOutputStream(file).use {
            bitmap.compress(Bitmap.CompressFormat.PNG, 100,
                it)
        }
    }

    fun clearAllDrawings(){
        drawingPaths.clear()
        invalidate()
    }

    fun openDrawing(uri: Uri){
        var bitmap: Bitmap? = null
        context.contentResolver.openInputStream(uri).use {
            bitmap = BitmapFactory.decodeStream(it)
        }

        val canvas = Canvas(bitmap!!)
        clearAllDrawings()
        canvas.drawBitmap(bitmap!!, 0f, 0f, null)
        invalidate()
    }


}