package com.example.viewanimation;

import android.content.IntentFilter;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.ViewModelProvider;

import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.AnimationUtils;
import android.view.animation.BounceInterpolator;
import android.view.animation.LinearInterpolator;
import android.view.animation.RotateAnimation;
import android.view.animation.ScaleAnimation;
import android.view.animation.TranslateAnimation;

public class CommonViewAnimationActivity extends AppCompatActivity {
    private static final String TAG = "View Animation";
    private View mPuppet;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_common_view_animation);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        mPuppet = findViewById(R.id.view_puppet);
        findViewById(R.id.btn_doit).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                doAnimation(getAnimationSet(true), "XML Animation");
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_action, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int i = item.getItemId();
        if (i == android.R.id.home) {
            finish();
        } else if (R.id.action_alpha == i) {
            doAnimation(getAlphaAnimation(), "AlphaAnimation");
        } else if (R.id.action_rotate == i) {
            doAnimation(getRotateAnimation(), "RotateAnimation");
        } else if (R.id.action_scale == i) {
            doAnimation(getScaleAnimation(), "ScaleAnimation");
        } else if (R.id.action_translate == i) {
            doAnimation(getTranslateAnimation(), "TranslateAnimation");
        } else if (R.id.action_set == i) {
            doAnimation(getAnimationSet(false), "AnimationSet");
        }
        return super.onOptionsItemSelected(item);
    }

    private void doAnimation(Animation animation, @Nullable final String animationType) {
        Animation oldAnimation = mPuppet.getAnimation();
        if (oldAnimation != null) {
            if (oldAnimation.hasStarted() || !oldAnimation.hasEnded()) {
                oldAnimation.cancel();
                mPuppet.clearAnimation();
            }
        }

        animation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
                Log.i(TAG, animationType + "start");
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                Log.i(TAG, animationType + "end");
            }

            @Override
            public void onAnimationRepeat(Animation animation) {
                Log.i(TAG, animationType + "repeat");
            }
        });

        mPuppet.startAnimation(animation);
    }

    private Animation getAnimationSet(boolean fromXML) {
        if (fromXML) {
            Animation animation = AnimationUtils.loadAnimation(this, R.animator.view_animation);
            return animation;
        } else {
            AnimationSet innerAnimationSet = new AnimationSet(true);
            innerAnimationSet.setInterpolator(new BounceInterpolator());
            innerAnimationSet.setStartOffset(1000);
            innerAnimationSet.addAnimation(getScaleAnimation());
            innerAnimationSet.addAnimation(getTranslateAnimation());

            AnimationSet animationSet = new AnimationSet(true);
            animationSet.setInterpolator(new LinearInterpolator());
            animationSet.addAnimation(getAlphaAnimation());
            animationSet.addAnimation(getRotateAnimation());
            animationSet.addAnimation(innerAnimationSet);

            return animationSet;
        }
    }

    private Animation getAlphaAnimation() {
        AlphaAnimation alphaAnimation = new AlphaAnimation(1f, 0f);
        alphaAnimation.setDuration(2000);
        alphaAnimation.setRepeatCount(1);
        alphaAnimation.setFillAfter(true);
        alphaAnimation.setFillBefore(false);
        alphaAnimation.setRepeatMode(Animation.REVERSE);
        return alphaAnimation;
    }

    private Animation getRotateAnimation() {
        RotateAnimation rotateAnimation = new RotateAnimation(0f, 100f, 0, getHeight() / 2);
        rotateAnimation.setDuration(2000);
        rotateAnimation.setRepeatCount(2);
        rotateAnimation.setRepeatMode(Animation.REVERSE);
        rotateAnimation.setFillAfter(true);
        rotateAnimation.setFillBefore(false);
        return rotateAnimation;
    }

    private Animation getScaleAnimation() {
        ScaleAnimation scaleAnimation = new ScaleAnimation(1f, 2f, 1f, 2f);
        scaleAnimation.setDuration(2000);
        scaleAnimation.setRepeatCount(2);
        scaleAnimation.setFillAfter(true);
        scaleAnimation.setFillBefore(false);
        scaleAnimation.setRepeatMode(Animation.REVERSE);
        return scaleAnimation;
    }

    private Animation getTranslateAnimation() {
        TranslateAnimation translateAnimation = new TranslateAnimation(0, getWidth() * 2, 0, getHeight() * 2);
        translateAnimation.setDuration(2000);
        translateAnimation.setFillAfter(true);
        translateAnimation.setFillBefore(false);
        translateAnimation.setRepeatMode(Animation.REVERSE);
        return translateAnimation;
    }

    private int getWidth() {
        return mPuppet.getWidth();
    }

    private int getHeight() {
        return mPuppet.getHeight();
    }
}
