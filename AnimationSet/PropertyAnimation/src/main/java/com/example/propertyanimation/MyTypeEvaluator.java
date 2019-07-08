package com.example.propertyanimation;

import android.animation.ArgbEvaluator;
import android.animation.TypeEvaluator;

/**
 * Copyright (C), 2014-2019, Yutou Technology (Hangzhou) Co, Ltd.
 * FileName: MyTypeEvaluator
 * Author: BeyondChao
 * Date: 2019-07-08
 */
public class MyTypeEvaluator implements TypeEvaluator<PropertyBean> {

    ArgbEvaluator mArgbEvaluator = new ArgbEvaluator();

    @Override
    public PropertyBean evaluate(float fraction, PropertyBean startPropertyBean, PropertyBean endPropertyBean) {
//        float currentColor = startPropertyBean.getBackgroundColor() + (endPropertyBean.getBackgroundColor() - startPropertyBean.getBackgroundColor()) * fraction;
        int currentColor = (int) mArgbEvaluator.evaluate(fraction, startPropertyBean.getBackgroundColor(), endPropertyBean.getBackgroundColor());
        float currentRotationX = startPropertyBean.getRotationX() + (endPropertyBean.getRotationX() - startPropertyBean.getRotationX()) * fraction;
        float currentSize = startPropertyBean.getSize() + (endPropertyBean.getSize() - startPropertyBean.getSize()) * fraction;
        return new PropertyBean((int) currentColor, currentRotationX, currentSize);
    }
}
