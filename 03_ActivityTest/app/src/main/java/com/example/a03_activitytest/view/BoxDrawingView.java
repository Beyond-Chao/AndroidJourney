package com.example.a03_activitytest.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
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
import java.util.Scanner;
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
    private Paint mArcPaint;
    private Paint mTextPaint;
    private Paint mPathPaint;

    private RectF waitForDrawRectF = new RectF();

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

        initPaint();
    }

    private void initPaint() {
        mBoxPaint = new Paint();
        mBoxPaint.setColor(0x22ff0000);

        mBackgroundPaint = new Paint();
        mBackgroundPaint.setColor(0xfff8efe0);

        mCirclePaint = new Paint();
        mCirclePaint.setColor(0x2200ff00);

        mArcPaint = new Paint();
        mArcPaint.setColor(0x33ffff00);

        mTextPaint = new Paint();
        mTextPaint.setColor(0x3311ff00);
        mTextPaint.setTextSize(60f);

        mPathPaint = new Paint();
        mPathPaint.setColor(0x2f11ff00);

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

            waitForDrawRectF.left = left;
            waitForDrawRectF.top = top;
            waitForDrawRectF.right = right;
            waitForDrawRectF.bottom = bottom;

            canvas.drawRect(waitForDrawRectF.left, waitForDrawRectF.top, waitForDrawRectF.right, waitForDrawRectF.bottom, mBoxPaint);

            drawCircle(canvas, waitForDrawRectF);
            drawArc(canvas, waitForDrawRectF);
            drawText(canvas, waitForDrawRectF);
            drawPath(canvas, waitForDrawRectF);
        }
    }

    private void drawCircle(Canvas canvas, RectF rectF) {
        float width = rectF.width();
        float height = rectF.height();
        float radius = Math.min(width, height) / 2;
        canvas.drawCircle(width / 2 + rectF.left, height / 2 + rectF.top, radius, mCirclePaint);
    }

    private void drawArc(Canvas canvas, RectF rectF) {
        canvas.drawArc(rectF, 10, 80, true, mArcPaint);
    }

    private void drawText(Canvas canvas, RectF rectF) {
        canvas.drawText("hello", rectF.left, rectF.top, mTextPaint);
//        canvas.drawText("hello", 1, 3, rectF.left, rectF.top, mTextPaint);
    }

    private void drawPath(Canvas canvas, RectF rectF) {
        Path p = new Path();
        p.moveTo(rectF.left, rectF.top);
        p.lineTo(rectF.width() / 2 + rectF.left, rectF.height() / 2 + rectF.top);
        p.lineTo(rectF.right, rectF.top);
        p.close();

        canvas.drawPath(p, mPathPaint);
    }
}
