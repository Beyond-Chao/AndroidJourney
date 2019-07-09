package com.example.a03_activitytest.view;

import android.content.Context;
import android.graphics.PointF;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

/**
 * Copyright (C), 2014-2019, Yutou Technology (Hangzhou) Co, Ltd.
 * FileName: BoxDrawingView
 * Author: BeyondChao
 * Date: 2019-07-09
 */
public class BoxDrawingView extends View {

    private static final String TAG = "BoxDrawingView";

    @Override
    public boolean onTouchEvent(MotionEvent event) {

        PointF current = new PointF(event.getX(), event.getY());
        String action = "";

        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                action = "ACTION_DOWN";
                break;
            case MotionEvent.ACTION_MOVE:
                action = "ACTION_MOVE";
                break;
            case MotionEvent.ACTION_UP:
                action = "ACTION_UP";
                break;
            case MotionEvent.ACTION_CANCEL:
                action = "ACTION_CANEL";
                break;
        }

        Log.i(TAG, action + " at x=" + current.x + ", y=" + current.y);
        return true;
    }

    public BoxDrawingView(Context context) {
        super(context);
    }

    public BoxDrawingView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }
}
