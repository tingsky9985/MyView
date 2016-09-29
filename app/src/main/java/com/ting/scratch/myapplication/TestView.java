package com.ting.scratch.myapplication;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Picture;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by ting on 16-9-28.
 */
public class TestView extends View {

    private Paint mPaint = new Paint();
    private Picture mPicture = new Picture();

    public TestView(Context context) {
        this(context, null);
    }

    public TestView(Context context, AttributeSet attrs) {
        this(context, attrs, -1);
    }

    public TestView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initPaint();
        recording();
    }

    /**
     * 录制内容
     */
    private void recording() {
        Canvas canvas = mPicture.beginRecording(100,100);

        mPaint.setColor(Color.DKGRAY);
        mPaint.setStrokeWidth(10f);
        mPaint.setStyle(Paint.Style.STROKE);

        canvas.translate(300, 300);
        canvas.drawCircle(100, 100, 100, mPaint);

        mPicture.endRecording();
    }

    private void initPaint() {
        mPaint.setColor(Color.GRAY);
        mPaint.setStrokeWidth(10f);
        mPaint.setStyle(Paint.Style.FILL);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
      /*  //点
        canvas.drawPoint(100, 100, mPaint);
        //线
        canvas.drawLine(200, 200, 400, 400, mPaint);
        //矩形，第一种方式
        canvas.drawRect(500, 500, 550, 550, mPaint);
        //矩形，第二种方式
        Rect mRect = new Rect(450,450,500,500);
        canvas.drawRect(mRect, mPaint);
        //圆角矩形
        RectF mRectF = new RectF(300,300,450,450);
        canvas.drawRoundRect(mRectF, 50, 50, mPaint);
        //椭圆
        RectF mRectFOval = new RectF(100,400,200,600);
        canvas.drawOval(mRectFOval, mPaint);
        //园
        canvas.drawCircle(300, 700, 70, mPaint);
        //弧
        RectF mRectFArc = new RectF(400,400,500,500);
        mPaint.setColor(Color.RED);
        canvas.drawArc(mRectFArc, 0, 50, false, mPaint);

        RectF mRectArc = new RectF(500,500,600,600);
        mPaint.setColor(Color.BLUE);
        canvas.drawArc(mRectArc, 0, 50, true, mPaint);

        //画布移动
        mPaint.setColor(Color.RED);
        canvas.translate(200, 200);
        canvas.drawCircle(0, 100, 100, mPaint);*/

/*        mPaint.setColor(Color.DKGRAY);
        canvas.translate(200, 200);
        canvas.drawCircle(0,100,100,mPaint);*/

        //draw picture
        //TODO java.lang.IllegalArgumentException: Picture playback is only supported on software canvas.
        //mPicture.draw(canvas);
        //TODO 不生效
        canvas.drawPicture(mPicture, new RectF(0,0,mPicture.getWidth(),200));

    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int widthSize = MeasureSpec.getSize(widthMeasureSpec);
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);

        int heightSize = MeasureSpec.getSize(heightMeasureSpec);
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
    }
}
