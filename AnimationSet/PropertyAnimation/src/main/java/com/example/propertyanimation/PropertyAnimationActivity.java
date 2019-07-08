package com.example.propertyanimation;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.animation.Animator;
import android.animation.AnimatorInflater;
import android.animation.AnimatorSet;
import android.animation.ArgbEvaluator;
import android.animation.LayoutTransition;
import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.animation.ValueAnimator;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewPropertyAnimator;
import android.view.animation.AccelerateDecelerateInterpolator;

import java.util.ArrayList;

public class PropertyAnimationActivity extends AppCompatActivity {

    private View mPuppet;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_property_animation);

        initSubviews();
    }

    private void initSubviews() {
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        mPuppet = findViewById(R.id.view_pupuet);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_property, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int i = item.getItemId();
        if (i == android.R.id.home) {
            finish();
        } else if (i == R.id.action_do_byxml) {
            doAnimation(getAnimationDrawable(false));
        } else if (i == R.id.action_bycode) {
            doAnimation(getAnimationDrawable(true));
        } else if (i == R.id.action_bycustom) {
            doAnimation(getValueAnimatorByCustom());
        } else if (i == R.id.action_byviewpropertyanimator) {
            doAnimatorByViewPropertyAnimator();
        } else if (i == R.id.action_bylayoutanimator) {
            doLayoutAnimator();
        }
        return super.onOptionsItemSelected(item);
    }

    private Animator getAnimationDrawable(boolean formXml) {
        return formXml ? getAnimationByXml() : getAnimatorSet();
    }

    private void doAnimation(Animator animator) {
        animator.start();
    }

    private Animator getAnimationByXml() {
        final int height = mPuppet.getLayoutParams().height;
        final int width = mPuppet.getLayoutParams().width;
        AnimatorSet animatorSet = (AnimatorSet) AnimatorInflater.loadAnimator(this, R.animator.animatorset);

        ArrayList<Animator> childAnimations = animatorSet.getChildAnimations();
        ((ValueAnimator) childAnimations.get(childAnimations.size() - 1))
                .addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                    @Override
                    public void onAnimationUpdate(ValueAnimator animation) {
                        float animatedValue = (float) animation.getAnimatedValue();
                        mPuppet.getLayoutParams().height = (int)(height * animatedValue);
                        mPuppet.getLayoutParams().width = (int)(width * animatedValue);
                        mPuppet.requestLayout();
                    }
                });

        animatorSet.setTarget(mPuppet);
        return animatorSet;
    }

    public Animator getObjectAnimatorByPropertyValuesHolder() {
        PropertyValuesHolder bgColorAnimator = PropertyValuesHolder.ofObject("backgroundColor", new ArgbEvaluator(), 0xff009688, 0xff795548);
        PropertyValuesHolder rotateXAnimator = PropertyValuesHolder.ofFloat("rotationX", 0f, 360f);
        ObjectAnimator objectAnimator = ObjectAnimator.ofPropertyValuesHolder(mPuppet, bgColorAnimator, rotateXAnimator);
        objectAnimator.setDuration(3000);
        objectAnimator.setRepeatCount(1);
        objectAnimator.setRepeatMode(ValueAnimator.REVERSE);
        return objectAnimator;
    }

    public ValueAnimator getValueAnimator() {
        final int height = mPuppet.getLayoutParams().height;
        final int width = mPuppet.getLayoutParams().width;

        ValueAnimator sizeValueAnimator = ValueAnimator.ofFloat(1f, 3f);
        sizeValueAnimator.setDuration(3000);
        sizeValueAnimator.setRepeatCount(1);
        sizeValueAnimator.setRepeatMode(ValueAnimator.REVERSE);
        sizeValueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                float animatedValue = (float) valueAnimator.getAnimatedValue();
                mPuppet.getLayoutParams().height = (int) (height * animatedValue);
                mPuppet.getLayoutParams().width = (int) (width * animatedValue);
                mPuppet.requestLayout();
            }
        });
        return sizeValueAnimator;
    }

    public AnimatorSet getAnimatorSet() {
        AnimatorSet animatorSet = new AnimatorSet();

        // play together
        {
//            animatorSet.play(getObjectAnimator(true))
//                    .with(getObjectAnimator(false))
//                    .with(getValueAnimator());
        }

        //play sequentially
        {
//            animatorSet.play(getObjectAnimator(true))
//                    .after(getObjectAnimator(false))
//                    .before(getValueAnimator());
        }

        animatorSet.playTogether(getObjectAnimatorByPropertyValuesHolder(), getValueAnimator());

        animatorSet.setInterpolator(new AccelerateDecelerateInterpolator());
        return animatorSet;
    }

    public ObjectAnimator getObjectAnimator(boolean b) {
        if (b) {
            ObjectAnimator bgColorAnimator = ObjectAnimator.ofArgb(mPuppet,
                    "backgroundColor",
                    0xff009688, 0xff795548);
            bgColorAnimator.setRepeatCount(1);
            bgColorAnimator.setDuration(3000);
            bgColorAnimator.setRepeatMode(ValueAnimator.REVERSE);
            bgColorAnimator.setStartDelay(0);
            return bgColorAnimator;
        } else {
            ObjectAnimator rotationXAnimator = ObjectAnimator.ofFloat(mPuppet,
                    "rotationX",
                    0f, 360f);
            rotationXAnimator.setRepeatCount(1);
            rotationXAnimator.setDuration(3000);
            rotationXAnimator.setRepeatMode(ValueAnimator.REVERSE);
            return rotationXAnimator;
        }
    }

    private void doAnimatorByViewPropertyAnimator() {
        ViewPropertyAnimator viewPropertyAnimator = mPuppet.animate()
                .rotationX(360f)
                .alpha(0.5f)
                .scaleX(3).scaleY(3)
                .setInterpolator(new AccelerateDecelerateInterpolator())
                .setDuration(3000)
                .setStartDelay(0);
    }

    private void doLayoutAnimator() {
        LayoutTransition layoutTransition = new LayoutTransition();
        layoutTransition.setAnimator(LayoutTransition.APPEARING, getObjectAnimator(false));
        layoutTransition.setAnimator(LayoutTransition.DISAPPEARING, getObjectAnimator(false));
        layoutTransition.setDuration(2000);

        ViewGroup contentView = (ViewGroup) ((ViewGroup) getWindow().getDecorView().findViewById(android.R.id.content)).getChildAt(0);
        contentView.setLayoutTransition(layoutTransition);
        if (contentView.findViewById(R.id.view_pupuet) == null) {
            contentView.addView(mPuppet);
        } else {
            contentView.removeView(mPuppet);
        }
    }

    private Animator getValueAnimatorByCustom() {
        final int height = mPuppet.getLayoutParams().height;
        final int width = mPuppet.getLayoutParams().width;
        PropertyBean startPropertyBean = new PropertyBean(0xff009688, 0f, 1f);
        PropertyBean endPropertyBean = new PropertyBean(0xff795548, 360f, 3.0f);

        ValueAnimator valueAnimator = new ValueAnimator();
        valueAnimator.setDuration(3000);
        valueAnimator.setInterpolator(new SpeedUpInterpolator());
        valueAnimator.setRepeatMode(ValueAnimator.RESTART);
        valueAnimator.setRepeatCount(1);

        valueAnimator.setObjectValues(startPropertyBean, endPropertyBean);
        valueAnimator.setEvaluator(new MyTypeEvaluator());

        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                PropertyBean propertyBean = (PropertyBean) animation.getAnimatedValue();
                if (propertyBean.getBackgroundColor() != 0 && propertyBean.getBackgroundColor() != 1) {
                    mPuppet.setBackgroundColor(propertyBean.getBackgroundColor());
                }
                mPuppet.setRotationX(propertyBean.getRotationX());
                mPuppet.getLayoutParams().height = (int) (height * propertyBean.getSize());
                mPuppet.getLayoutParams().width = (int) (width * propertyBean.getSize());
                mPuppet.requestLayout();
            }
        });

        return valueAnimator;
    }
}
