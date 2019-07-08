package com.example.propertyanimation;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.animation.Animator;
import android.animation.AnimatorInflater;
import android.animation.AnimatorSet;
import android.animation.ArgbEvaluator;
import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.animation.ValueAnimator;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
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

        } else if (i == R.id.action_byviewpropertyanimator) {

        } else if (i == R.id.action_bylayoutanimator) {

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
}
