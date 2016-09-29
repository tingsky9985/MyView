package com.ting.scratch.myapplication;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by ting on 16-9-29.
 */
public class BitmapView extends View {

    private Paint mPaint = new Paint();
    private Bitmap mBitmap;

    public BitmapView(Context context) {
        this(context, null);
    }

    public BitmapView(Context context, AttributeSet attrs) {
        this(context, attrs, -1);
    }

    public BitmapView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initPaint();
    }

    private void initPaint() {
        mPaint.setColor(Color.RED);
        mPaint.setStyle(Paint.Style.STROKE);

        //资源文件 mipmap/raw
        mBitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.aaa);

/*        //assets
        try {
            InputStream io = getContext().getAssets().open("");
            mBitmap = BitmapFactory.decodeStream(io);
            io.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        //sdcard
        mBitmap = BitmapFactory.decodeFile("");

        //网络文件
        try {
            InputStream io = getContext().getAssets().open("");
            mBitmap = BitmapFactory.decodeStream(io);
            io.close();
        } catch (IOException e) {
            e.printStackTrace();
        }*/
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        //默认坐标
        //canvas.drawBitmap(mBitmap, new Matrix(), mPaint);
        //指定坐标
        //canvas.drawBitmap(mBitmap, 100, 100, mPaint);
        canvas.translate(getWidth()/2, getHeight()/2);
        //指定图片绘制区域
        Rect src = new Rect(0, 0, mBitmap.getWidth()/2, mBitmap.getHeight()/2);
        //指定图片显示区域
        Rect dst = new Rect(0, 0, 200, 400);
        canvas.drawBitmap(mBitmap, src, dst, null);
    }
}
