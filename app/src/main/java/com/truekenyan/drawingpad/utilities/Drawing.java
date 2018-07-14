package com.truekenyan.drawingpad.utilities;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PorterDuff;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import java.util.ArrayList;

/**
 * Created by password
 * on 7/12/18.
 */

public class Drawing extends View {

    private Paint drawPaint;
    private Paint canvasPaint;
    private Bitmap bitmap;
    private Path drawPath;
    private Canvas canvas;
    private int drawColor = 0xFF000000;
    private float brushSize = 3F;

    private ArrayList<Path> allPaths = new ArrayList<>();
    private ArrayList<Path> undonePaths = new ArrayList<>();

    public Drawing (Context context) {
        super(context);
        init();
    }

    public Drawing (Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public Drawing (Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    @RequiresApi(21)
    public Drawing (Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init();
    }

    private void init(){
        drawPaint = new Paint();
        drawPaint.setStyle(Paint.Style.STROKE);
        drawPaint.setColor(drawColor);
        drawPaint.setStrokeWidth(brushSize);
        drawPaint.setAntiAlias(true);
        drawPaint.setStrokeJoin(Paint.Join.ROUND);
        drawPaint.setStrokeCap(Paint.Cap.ROUND);

        canvasPaint = new Paint(Paint.DITHER_FLAG);
        drawPath = new Path();
    }

    @Override
    protected void onDraw (Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawBitmap(bitmap, 0, 0, canvasPaint);
        canvas.drawPath(drawPath, drawPaint);
    }

    @Override
    protected void onSizeChanged (int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        bitmap = Bitmap.createBitmap(w, h, Bitmap.Config.ARGB_8888);
        canvas = new Canvas(bitmap);
    }

    @Override
    public boolean onTouchEvent (MotionEvent event) {
        float eventX = event.getX();
        float eventY = event.getY();

        switch (event.getAction()){
            case MotionEvent.ACTION_DOWN:
                drawPath.moveTo(eventX, eventY);
                break;
            case MotionEvent.ACTION_MOVE:
                drawPath.lineTo(eventX, eventY);
                break;
            case MotionEvent.ACTION_UP:
                drawPath.lineTo(eventX, eventY);
                canvas.drawPath(drawPath, drawPaint);
                drawPath.reset();
                break;
            default:
                return false;
        }

        invalidate();
        return true;
    }

    public void touchUp(){

    }

    public void touchDown(float x, float y){

    }

    public void touchMove(float x, float y){

    }

    public void setBrushColor(int color){
        drawPaint.setColor(color);
    }

    public void setBrushSize(float size){
        drawPaint.setStrokeWidth(size);
    }

    public void clearAll(){
        canvas.drawColor(0, PorterDuff.Mode.CLEAR);
        invalidate();
    }
}