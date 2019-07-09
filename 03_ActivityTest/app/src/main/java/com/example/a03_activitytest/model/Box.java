package com.example.a03_activitytest.model;

import android.graphics.PointF;

/**
 * Copyright (C), 2014-2019, Yutou Technology (Hangzhou) Co, Ltd.
 * FileName: Box
 * Author: BeyondChao
 * Date: 2019-07-09
 */
public class Box {
    private PointF mOrigin;
    private PointF mCurrent;

    public Box(PointF origin) {
        mOrigin = origin;
    }

    public PointF getCurrent() {
        return mCurrent;
    }

    public void setCurrent(PointF current) {
        mCurrent = current;
    }

    public PointF getOrigin() {
        return mOrigin;
    }
}
