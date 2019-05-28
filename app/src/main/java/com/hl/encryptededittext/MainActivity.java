package com.hl.encryptededittext;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.RelativeLayout;

public class MainActivity extends AppCompatActivity
     //   implements View.OnTouchListener
{
    RelativeLayout rl_parent_view;
    View RectangleOverLayerView;
    View LayerOverEditTextView;
    //changePosition ref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        rl_parent_view = (RelativeLayout) findViewById(R.id.rl_parent_draw_view);
        //rl_parent_view.addView(new DrawingView(this, getRawEditText()));
        TouchView touchView = new TouchView(getApplicationContext());
       // LayerOverEditTextView = new LayerOverEditTextView(getApplicationContext());
//        rl_parent_view.addView(LayerOverEditTextView, 1);
        rl_parent_view.addView(touchView);
        //LayerOverEditTextView.setOnTouchListener(this);
      //  RectangleOverLayerView.setOnTouchListener(this);
    }

    EditText getRawEditText() {
        EditText editText = new EditText(this);
        editText.setText("My Test Text that i can use in future");
        editText.setWidth(300);
        editText.setBackgroundColor(Color.WHITE);
        return editText;
    }
/*
    void setPositionListener(changePosition reference) {
        this.ref = reference;
    }

    float dX, dY;*/
/*
    @Override
    public boolean onTouch(View view, MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                dX = view.getX() - event.getRawX();
                dY = view.getY() - event.getRawY();
                break;
            case MotionEvent.ACTION_MOVE:
               *//* view.animate()
                        .x(event.getRawX() + dX)
                        .y(event.getRawY() + dY)
                        .setDuration(0)
                        .start();*//*
                ref.OnPositionChanged(event.getRawX() + dX, event.getRawY() + dY);
                // view.invalidate();
                break;
            default:
                return false;
        }
        return true;
    }

    public class DrawingView extends View {
        EditText et;

        public DrawingView(Context context, EditText et) {
            super(context);
            this.et = et;
            //setBackgroundColor(Color.CYAN);
        }

        @Override
        protected void onDraw(Canvas canvas) {
            Paint paint = new Paint();
            paint.setStyle(Paint.Style.FILL);
            paint.setTextSize(30);
            canvas.drawText("hello how are you m working with it", 0, 50, paint);
            super.onDraw(canvas);
        }
    }

   *//* public class LayerOverEditTextView extends View implements changePosition {
        float x = 0;
        float y = 0;
        Canvas canvas;

        public LayerOverEditTextView(Context context) {
            super(context);
            setPositionListener(this);
        }

        @Override
        protected void onDraw(Canvas canvas) {
            super.onDraw(canvas);
            Paint transparentPaint = new Paint();
            transparentPaint.setColor(getResources().getColor(android.R.color.transparent));
            //transparentPaint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.CLEAR));
            transparentPaint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.DST_OUT));
            Rect rectangle;
            rectangle = new Rect((int) x, (int) y, 1000, 100);
            canvas.drawRect(rectangle, transparentPaint);
        }

        @Override
        public void OnPositionChanged(float x, float y) {
            this.x = x;
            this.y = y;
            invalidate();
        }
    }*//*

    public class RectangleOverLayerView extends View implements changePosition {
        private Bitmap mBitmap; //Covered layer
        private Canvas mCanvas;
        private Bitmap mCoverBitmap;
        float x = 0;
        float y = 0;
        Canvas canvas;

        public RectangleOverLayerView(Context context) {
            super(context);
            setPositionListener(this);
        }

        @Override
        protected void onDraw(Canvas canvas) {
            mCoverBitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.main_background);
            mBitmap = Bitmap.createBitmap(400, 100, Bitmap.Config.ARGB_8888);
            //canvas = new Canvas(mBitmap);
            canvas.drawBitmap(mCoverBitmap, 0, 0, null);
            Rect rectangle;
            rectangle = new Rect(0, 50, 100, 100);
            Paint paint = new Paint();
            paint.setColor(getResources().getColor(android.R.color.transparent));
            paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.DST_OUT));
            canvas.drawRect(rectangle, paint);
            super.onDraw(canvas);
        }

        @Override
        public void OnPositionChanged(float x, float y) {
            this.x = x;
            this.y = y;
            invalidate();
        }
    }

    public interface changePosition {
        void OnPositionChanged(float x, float y);
    }*/
}
