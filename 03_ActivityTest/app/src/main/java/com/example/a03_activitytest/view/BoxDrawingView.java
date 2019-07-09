package com.example.a03_activitytest.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PointF;
import android.graphics.Rect;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import com.example.a03_activitytest.model.Box;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;

/**
 * Copyright (C), 2014-2019, Yutou Technology (Hangzhou) Co, Ltd.
 * FileName: BoxDrawingView
 * Author: BeyondChao
 * Date: 2019-07-09
 */
public class BoxDrawingView extends View {

    private static final String TAG = "BoxDrawingView";
    private Box mCurrentBox;
    private List<Box> mBoxList = new ArrayList<>();

    private Paint mBoxPaint;
    private Paint mBackgroundPaint;
    private Paint mCirclePaint;

    @Override
    public boolean onTouchEvent(MotionEvent event) {

        PointF current = new PointF(event.getX(), event.getY());
        String action = "";

        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                action = "ACTION_DOWN";
                mCurrentBox = new Box(current);
                mBoxList.add(mCurrentBox);
                break;
            case MotionEvent.ACTION_MOVE:
                action = "ACTION_MOVE";
                if (null != mCurrentBox) {
                    mCurrentBox.setCurrent(current);
                    invalidate();
                }
                break;
            case MotionEvent.ACTION_UP:
                action = "ACTION_UP";
                mCurrentBox = null;
                break;
            case MotionEvent.ACTION_CANCEL:
                action = "ACTION_CANEL";
                mCurrentBox = null;
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

        this.getParent();
        mBoxPaint = new Paint();
        mBoxPaint.setColor(0x22ff0000);

        mBackgroundPaint = new Paint();
        mBackgroundPaint.setColor(0xfff8efe0);

        mCirclePaint = new Paint();
        mCirclePaint.setColor(0x2200ff00);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        // Fill the background
        canvas.drawPaint(mBackgroundPaint);

        for (Box box : mBoxList) {
            float left = Math.min(box.getOrigin().x, box.getCurrent().x);
            float top = Math.min(box.getOrigin().y, box.getCurrent().y);

            float right = Math.max(box.getOrigin().x, box.getCurrent().x);
            float bottom = Math.max(box.getOrigin().y, box.getCurrent().y);

            RectF tempRect = new RectF(left, top, right, bottom);
            canvas.drawRect(tempRect.left, tempRect.top, tempRect.right, tempRect.bottom, mBoxPaint);

            float width = tempRect.width();
            float height = tempRect.height();
            float radius = Math.min(width, height) / 2;
            canvas.drawCircle(width / 2 + left, height / 2 + top, radius, mCirclePaint);
        }
    }
}
