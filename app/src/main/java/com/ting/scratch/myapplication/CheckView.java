package com.ting.scratch.myapplication;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.os.Handler;
import android.os.Message;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

/**
 * Created by ting on 16-9-29.
 */
public class CheckView extends View {

    private static String TAG = "CheckView";

    private static int ANIM_NULL = -1;  //动画状态-没有
    private static int ANIM_CHECK = 0;  //动画状态-选择
    private static int ANIM_UNCHECK = 1; //动画状态-结束

    //画笔
    private Paint mPaint = new Paint();
    //获取图像
    private Bitmap mBitmap;

    //宽高
    private int width, height;

    private Handler mHandler;

    private int animaCurrentPage = -1;  //开始页数
    private int animTotal = 13;         //总共页数
    private int animDuration = 500;     //动画时长
    private int animState = ANIM_NULL;  //动画状态

    private boolean isCheck = false; //是否是被选中的状态

    private static final int MSG_ANIM = 0;

    public CheckView(Context context) {
        this(context, null);
    }

    public CheckView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initPaint();
        setWillNotDraw(false);
    }

    private void initPaint() {
        Log.d(TAG, "initPaint()");

        CheckView.this.setBackgroundColor(Color.RED);

        //初始化画笔
        mPaint.setColor(Color.RED);
        mPaint.setStyle(Paint.Style.FILL);
        mPaint.setStrokeWidth(10f);
        mPaint.setAntiAlias(true);

        //获取图片
        mBitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.checkmark);

        //根据尺寸，更新界面
        mHandler = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                Log.d(TAG, "initPaint: animaCurrentPage" + animaCurrentPage + ",animTotal: " + animTotal);
                Log.d(TAG, "initPaint: animState" + animState);

                if(animaCurrentPage < animTotal && animaCurrentPage >= 0) {
                    invalidate();
                    if(animState == ANIM_NULL){
                        return;
                    } else if(animState == ANIM_CHECK) {
                        animaCurrentPage ++;
                    } else if(animState == ANIM_UNCHECK) {
                        animaCurrentPage --;
                    }

                    //发送消息
                    this.sendEmptyMessageDelayed(MSG_ANIM, animDuration / animTotal);
                    Log.d(TAG, "currentPage: " + animaCurrentPage);
                } else {
                    if(isCheck){
                        animaCurrentPage = animTotal - 1;
                    }else {
                        animaCurrentPage = -1;
                    }
                    invalidate();
                    animState = ANIM_NULL;
                }
            }
        };


    }


    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        width = w;
        height = h;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        Log.d(TAG, "onDraw()");
        //移动到画布中间
        canvas.translate(width / 2, height / 2);
        //画背景园
        canvas.drawCircle(0, 0, 250, mPaint);

        int sideLength = mBitmap.getHeight();
        int sideWidth = mBitmap.getWidth() / 13;
        Log.d(TAG, "onDraw() sideLength: " + sideLength + ",sideWidth: " + sideWidth);
        Log.d(TAG, "onDraw() animaCurrentPage: " + animaCurrentPage);

        //图像选择和图像显示区域
        //Rect src = new Rect(0, 0, sideWidth * animaCurrentPage / animTotal, sideLength);
        //Rect dst = new Rect(-200, -200, (-200 + (400 * (animaCurrentPage)) / (animTotal)), 200);
        Rect src = new Rect(sideWidth * animaCurrentPage, 0, sideWidth * (animaCurrentPage +1), sideLength);
        Rect dst = new Rect(-200,-200,200,200);
        //绘制
        canvas.drawBitmap(mBitmap, src, dst, null);
        //canvas.drawBitmap(mBitmap, getMatrix(), mPaint);
    }

    public void check(){
        if(isCheck || animState != ANIM_NULL){
            return;
        }
        animState = ANIM_CHECK;
        animaCurrentPage = 0;
        mHandler.sendEmptyMessageDelayed(MSG_ANIM, animDuration / animTotal);
        isCheck = true;
    }

    public void unCheck(){
        if(!isCheck || animState != ANIM_NULL){
            return;
        }
        animState = ANIM_UNCHECK;
        animaCurrentPage = animTotal - 1;
        mHandler.sendEmptyMessageDelayed(MSG_ANIM, animDuration / animTotal);
        isCheck = false;
    }

    public void setAnimDuration(int animaDuration){
        this.animDuration = animDuration;
    }

    public void setBackgroundColor(int color){
        mPaint.setColor(color);
    }
}
