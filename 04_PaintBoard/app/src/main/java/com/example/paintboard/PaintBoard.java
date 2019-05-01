package com.example.paintboard;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.Shader;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.RectShape;
import android.util.AttributeSet;
import android.view.Display;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;

import java.util.Stack;

public class PaintBoard extends View {

    Paint paint;
    Bitmap mBitmap;
    Canvas mCanvas;

    float oldX;
    float oldY;



    public PaintBoard(Context context) {
        super(context);
        init(context);
    }

    public PaintBoard(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    private void init(Context context) {

        paint = new Paint();
        paint.setColor(Color.BLACK);
        paint.setStrokeWidth(10);

    }

    public void setColor(int color) {
        paint.setColor(color);
    }


    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mBitmap = Bitmap.createBitmap(w,h,Bitmap.Config.ARGB_8888);
        mCanvas = new Canvas();
        mCanvas.setBitmap(mBitmap);
        mCanvas.drawColor(Color.WHITE);

    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawBitmap(mBitmap, 0,0, null);

    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {

        int action = event.getAction();

        int currentX = (int) event.getX();
        int currentY = (int) event.getY();
        if (action == MotionEvent.ACTION_DOWN) {
            oldX = currentX;
            oldY = currentY;

        } else if (action == MotionEvent.ACTION_MOVE) {
            if (oldX > 0 || oldY > 0) {
                mCanvas.drawLine(oldX,oldY, currentX, currentY, paint);
            }
            oldX = currentX;
            oldY = currentY;
        } else if (action == MotionEvent.ACTION_UP) {
        }
        invalidate();
        return true;
    }

    public void updatePaintProperty(int color, int size)
    {
        paint.setColor(color);
        paint.setStrokeWidth(size);
    }

}
