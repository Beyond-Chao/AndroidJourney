package com.example.drawableanimation;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.drawable.AnimationDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toolbar;

public class DrawableAnimationActivity extends AppCompatActivity {

    private ImageView mPuppet;

    private LinearLayout mLinearLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drawable_animation);

        mPuppet = findViewById(R.id.animation_imageview);

        findViewById(R.id.start_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                doAnimation(getAnimationDrawable(false), true);
            }
        });

        findViewById(R.id.stop_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                doAnimation(getAnimationDrawable(false), false);
            }
        });
    }

    private void doAnimation(AnimationDrawable animationDrawable, boolean doIt) {
        if (animationDrawable.isRunning()) {
            animationDrawable.stop();
        }

        if (doIt) {
            animationDrawable.start();
        }
    }

    private AnimationDrawable getAnimationDrawable(boolean fromXml) {
        if (fromXml) {
            //mPuppet.setImageDrawable(getResources().getDrawable(R.drawable.run));
            AnimationDrawable animationDrawable = (AnimationDrawable) mPuppet.getDrawable();
            //animationDrawable.setOneShot(false);
            return animationDrawable;
        } else {
            AnimationDrawable animationDrawable = new AnimationDrawable();
            for (int i = 1; i < 8; i++) {
                int id = getResources().getIdentifier("run" + i, "drawable", getPackageName());
                Drawable drawable = getDrawable(id);
                if (null != drawable) {
                    animationDrawable.addFrame(drawable, 100);
                }
            }
            mPuppet.setImageDrawable(animationDrawable);
            return animationDrawable;
        }
    }
}
