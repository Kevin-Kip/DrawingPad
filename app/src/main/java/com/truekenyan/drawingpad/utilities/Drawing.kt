package com.truekenyan.drawingpad.utilities

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.Path
import android.graphics.PorterDuff
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View

/**
 * Created by password
 * on 7/12/18.
 */

class Drawing : View {

    private var drawPaint: Paint? = null
    private var canvasPaint: Paint? = null
    private var bitmap: Bitmap? = null
    private var drawPath: Path? = null
    private var canvas: Canvas? = null

    constructor(context: Context) : super(context) {
        init()
    }

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {
        init()
    }

    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {
        init()
    }

    private fun init() {
        val brushSize = 3f
        val drawColor = -0x1000000
        drawPaint = Paint()
        drawPaint!!.style = Paint.Style.STROKE
        drawPaint!!.color = drawColor
        drawPaint!!.strokeWidth = brushSize
        drawPaint!!.isAntiAlias = true
        drawPaint!!.strokeJoin = Paint.Join.ROUND
        drawPaint!!.strokeCap = Paint.Cap.ROUND

        canvasPaint = Paint(Paint.DITHER_FLAG)
        drawPath = Path()
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        canvas.drawBitmap(bitmap!!, 0F, 0F, canvasPaint)
        canvas.drawPath(drawPath!!, drawPaint!!)
    }

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)
        bitmap = Bitmap.createBitmap(w, h, Bitmap.Config.ARGB_8888)
        canvas = Canvas(bitmap!!)
    }

    @SuppressLint("ClickableViewAccessibility")
    override fun onTouchEvent(event: MotionEvent): Boolean {
        val eventX = event.x
        val eventY = event.y

        when (event.action) {
            MotionEvent.ACTION_DOWN -> drawPath!!.moveTo(eventX, eventY)
            MotionEvent.ACTION_MOVE -> drawPath!!.lineTo(eventX, eventY)
            MotionEvent.ACTION_UP -> {
                drawPath!!.lineTo(eventX, eventY)
                canvas!!.drawPath(drawPath!!, drawPaint!!)
                drawPath!!.reset()
            }
            else -> return false
        }

        invalidate()
        return true
    }

    fun setBrushColor(color: Int) {
        drawPaint!!.color = color
    }

    fun setBrushSize(size: Float) {
        drawPaint!!.strokeWidth = size
    }

    fun clearAll() {
        canvas!!.drawColor(0, PorterDuff.Mode.CLEAR)
        invalidate()
    }
}
