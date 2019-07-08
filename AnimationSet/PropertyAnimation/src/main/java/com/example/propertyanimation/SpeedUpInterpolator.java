package com.example.propertyanimation;

import android.view.animation.Interpolator;

/**
 * Copyright (C), 2014-2019, Yutou Technology (Hangzhou) Co, Ltd.
 * FileName: SpeedUpInterpolator
 * Author: BeyondChao
 * Date: 2019-07-08
 */
public class SpeedUpInterpolator implements Interpolator {
    private final float mFactor;
    private final double mDoubleFactor;

    public SpeedUpInterpolator() {
        mFactor = 1.0f;
        mDoubleFactor = 2.0;
    }

    @Override
    public float getInterpolation(float v) {
        //实现核心代码块
        if (mFactor == 1.0f) {
            return v * v;
        } else {
            return (float) Math.pow(v, mDoubleFactor);
        }
    }
}
