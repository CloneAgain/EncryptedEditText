package com.hl.encryptededittext;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

/**
 * Created by santosh on 14/9/17.
 */
public class MainActivity2 extends View {

    private Bitmap mBitmap; //Covered layer
    private Canvas mCanvas; //Draw mask layer
    private Paint mOuterPaint;
    private Path mPath;
    private float mLastX;
    private float mLastY;

    private Bitmap mCoverBitmap; //Covering graph
    private int mWidth, mHeight;
    private Paint mInnerPaint;
    private String mInfo;


    public MainActivity2(Context context) {
        this(context, null);
    }

    public MainActivity2(Context context, AttributeSet attrs) {
        super(context, attrs);

        init();
    }

    private void init() {
        mPath = new Path();
        mOuterPaint = new Paint();
        mInnerPaint = new Paint();
        mCoverBitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher_round);

        mInfo = "￥ 5 0 0";
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        mWidth = mCoverBitmap.getWidth()+400;
        mHeight = mCoverBitmap.getHeight()+200;
        setMeasuredDimension(mWidth+400, mHeight);

        mBitmap = Bitmap.createBitmap(mWidth+400, mHeight, Bitmap.Config.ARGB_8888);
        mCanvas = new Canvas(mBitmap);
        mCanvas.drawBitmap(mCoverBitmap, 0, 0, null);

        setOuterPaint();
        setInnerPaint();

    }

    private void setInnerPaint() {
        mInnerPaint.setColor(Color.RED);
        mInnerPaint.setStyle(Paint.Style.STROKE);
        mInnerPaint.setStrokeCap(Paint.Cap.ROUND);
        mInnerPaint.setStrokeJoin(Paint.Join.ROUND);
        mInnerPaint.setAntiAlias(true);
        mInnerPaint.setDither(true); //Anti shake
        mInnerPaint.setStrokeWidth(5);
        mInnerPaint.setTextSize(100);
        mInnerPaint.setTextAlign(Paint.Align.CENTER);
    }

    private void setOuterPaint() {
        mOuterPaint.setColor(Color.GREEN);
        mOuterPaint.setStyle(Paint.Style.STROKE);
        mOuterPaint.setStrokeCap(Paint.Cap.ROUND);
        mOuterPaint.setStrokeJoin(Paint.Join.ROUND);
        mOuterPaint.setAntiAlias(true);
        mOuterPaint.setDither(true); //Anti shake
        mOuterPaint.setStrokeWidth(20);
    }

    @Override //Path
    public boolean onTouchEvent(MotionEvent event) {
        float x = event.getX();
        float y = event.getY();
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                mLastX = x;
                mLastY = y;
                mPath.moveTo(x, y);
                break;
            case MotionEvent.ACTION_MOVE:
                float deltaX = Math.abs(x - mLastX);
                float deltaY = Math.abs(y - mLastY);
                if (deltaX > 5 || deltaY > 5) {
                    mPath.lineTo(x, y);
                }
                mLastX = x;
                mLastY = y;
                break;
            case MotionEvent.ACTION_UP:
                break;
        }
        invalidate();//callonDraw
        return true;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        canvas.drawColor(Color.parseColor("#bbbbbb")); //Background color gray
        canvas.drawText(mInfo, mWidth / 2, mHeight / 4 * 3, mInnerPaint); //Draw text
        canvas.drawBitmap(mBitmap, 0, 0, null); //DrawmBitmap  This is a variablebitmap,adoptmCanvasDraw,First drawmCoverBitmap
        drawPath();

    }

    private void drawPath() {
        //Use thismode：dstandsrcAfter intersection, Only retaindst,And remove the intersection parts
        mOuterPaint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.CLEAR));
        mCanvas.drawPath(mPath, mOuterPaint);
    }
}