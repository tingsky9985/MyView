package com.ting.scratch.myapplication;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class MainActivity extends Activity {
    private static String TAG = "MainActivity";

    private Button checkBtn;
    private Button unCheckBtn;

    private CheckView mCheckView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(mCheckView);
        setContentView(R.layout.activity_main);
        initView();
    }

    private void initView() {
        mCheckView = (CheckView) findViewById(R.id.check_view);
        checkBtn = (Button) findViewById(R.id.check);
        unCheckBtn = (Button) findViewById(R.id.uncheck);
    }

    public void check(View v){
        Log.d(TAG, "Check: " + v);
        mCheckView.check();
    }

    public void unCheck(View v){
        Log.d(TAG, "unCheck: " + v);
        mCheckView.unCheck();
    }



}
