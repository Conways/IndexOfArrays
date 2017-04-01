package com.conways.indexofarrays;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

/**
 * Created by Conways on 2017/2/27.
 */

public class IndexView extends View {




    public static final String[] arrays = {"#", "A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M",
            "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z"};
    private int textColor;
    private int textSize;
    private int bgColor;

    private Paint textPaint;
    private Paint bgPaint;

    private int mWith;
    private int mHeight;

    private Rect rectText;

    private int itemPosition=-1;

    private TouchCallBack touchCallBack;
    private String TAG="zzzzzzz";


    public IndexView(Context context) {
        super(context);

    }

    public IndexView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
        init(attrs, context);
    }

    public IndexView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(attrs, context);
    }


    public void setTouchCallBack(TouchCallBack touchCallBack) {
        this.touchCallBack = touchCallBack;
    }

    private void init(AttributeSet attrs, Context context) {
        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.indexView);
        textColor = a.getColor(R.styleable.indexView_textColor, 0xff000000);
        textSize = a.getDimensionPixelSize(R.styleable.indexView_textSize, 18);
        textPaint = new Paint();
        textPaint.setColor(textColor);
        textPaint.setTextSize(textSize);
        textPaint.setAntiAlias(true);
        bgColor = a.getColor(R.styleable.indexView_bgColor, 0xffffffff);
        bgPaint = new Paint();
        bgPaint.setColor(bgColor);
        bgPaint.setAntiAlias(true);
        rectText = new Rect();
        a.recycle();
    }


    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int widthMeasureMode = MeasureSpec.getMode(widthMeasureSpec);
        int heightMeasureMode = MeasureSpec.getMode(heightMeasureSpec);
        if (widthMeasureMode == MeasureSpec.EXACTLY) {
            mWith = MeasureSpec.getSize(widthMeasureSpec);
        } else {
            mWith = dip2px(16);
        }
        if (heightMeasureMode == MeasureSpec.EXACTLY) {
            mHeight = MeasureSpec.getSize(heightMeasureSpec);
        } else {
            mHeight = dip2px(160);
        }
        setMeasuredDimension(mWith, mHeight);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        drawBg(canvas);
        drawText(canvas);
    }


    private void drawBg(Canvas canvas) {
        canvas.drawRect(0, 0, mWith, mHeight, bgPaint);
    }

    private void drawText(Canvas canvas) {
        int unitHeight = mHeight / arrays.length;
        for (int i = 0; i < arrays.length; i++) {
            String text = arrays[i];
            textPaint.getTextBounds(text, 0, text.length(), rectText);
            int x = (mWith - rectText.width()) / 2;
            int bottom = i * unitHeight + (unitHeight + unitHeight) / 2;
            canvas.drawText(text, 0, text.length(), x, bottom, textPaint);
        }
    }

    int currentPositon=-1;
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                if (touchCallBack==null){
                    break;
                }
                currentPositon=(int)(event.getY()/(mHeight/(arrays.length-1)));
                touchCallBack.callBack(arrays[currentPositon],true);
                break;
            case MotionEvent.ACTION_MOVE:
                if (event.getY() < 0 || event.getY() > mHeight) {
                    break;
                }

                int positon=(int)(event.getY()/(mHeight/(arrays.length-1)));
                if (currentPositon==positon){
                    break;
                }
                currentPositon=positon;
                if (touchCallBack==null){
                    break;
                }
                touchCallBack.callBack(arrays[currentPositon],true);
                break;
            case MotionEvent.ACTION_UP:
                currentPositon=-1;
                if (touchCallBack==null){
                    break;
                }
                touchCallBack.callBack("",false);
                break;
            default:
                break;

        }
        return true;
    }
    int i=0;

    private int dip2px(float dpValue) {
        final float scale = getContext().getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }

    public interface TouchCallBack {
        void callBack(String str,boolean show);
    }


}
