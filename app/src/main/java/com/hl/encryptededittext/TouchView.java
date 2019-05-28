package com.hl.encryptededittext;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BlurMaskFilter;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.view.MotionEvent;
import android.view.View;

/**
 * Created by santosh on 15/9/17.
 */
public class TouchView extends View {
    Bitmap bgr;
    Bitmap overlayDefault;
    Bitmap overlay;
    Paint pTouch;
    int X = -100;
    int Y = -100;
    Canvas c2;

    public TouchView(Context context) {
        super(context);
        bgr = BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher_round);
        overlayDefault = BitmapFactory.decodeResource(getResources(), R.mipmap.main_background);
        overlay = BitmapFactory.decodeResource(getResources(), R.mipmap.tstbackground).copy(Bitmap.Config.ARGB_8888, true);
        c2 = new Canvas(overlay);
        pTouch = new Paint(Paint.ANTI_ALIAS_FLAG);
        pTouch.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_OUT));
        pTouch.setColor(Color.TRANSPARENT);
        pTouch.setMaskFilter(new BlurMaskFilter(1, BlurMaskFilter.Blur.NORMAL));
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        switch (ev.getAction()) {
            case MotionEvent.ACTION_DOWN: {
                X = (int) ev.getX();
                Y = (int) ev.getY();
                invalidate();
                break;
            }
            case MotionEvent.ACTION_MOVE: {
                X = (int) ev.getX();
                Y = (int) ev.getY();
                invalidate();
                break;
            }
            case MotionEvent.ACTION_UP:
                X=-100;
                Y=-100;
                invalidate();
                break;
        }
        return true;
    }

    @Override
    public void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        //draw background
       // canvas.drawBitmap(bgr, 0, 0, null);

        Paint p = new Paint();
        p.setTextSize(30);
        p.setColor(Color.WHITE);
        canvas.drawText("hello this is my encrypted test text",0,50,p);
        //copy the default overlay into temporary overlay and punch a hole in it
        c2.drawBitmap(overlayDefault, 0, 0, null); //exclude this line to show all as you draw
        //c2.drawCircle(X, Y, 180, pTouch);

        c2.drawRect(X-15, Y-65, X+140, Y-140,pTouch);
        //draw the overlay over the background
        canvas.drawBitmap(overlay, 0, 0, null);
    }
}