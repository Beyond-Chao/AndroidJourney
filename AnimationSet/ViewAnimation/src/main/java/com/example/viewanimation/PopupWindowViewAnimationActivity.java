package com.example.viewanimation;

import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;
import android.widget.ImageView;
import android.widget.PopupWindow;

public class PopupWindowViewAnimationActivity extends AppCompatActivity {

    private PopupWindow mImgPopupWindow;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_popup_window_view_animation);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        findViewById(R.id.btn_show_img).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showImgPopupWindow(view);
            }
        });
    }

    private void showImgPopupWindow(View anchor) {
        if (mImgPopupWindow == null) {
            ImageView view = new ImageView(this);
            view.setImageDrawable(getDrawable(R.drawable.img_popup));
            mImgPopupWindow = new PopupWindow(view, anchor.getMeasuredWidth(), anchor.getMeasuredHeight(), true);
            mImgPopupWindow.setAnimationStyle(R.style.popup_anim_style);
        }
        if (mImgPopupWindow.isShowing()) {
            mImgPopupWindow.dismiss();
        } else {
            mImgPopupWindow.showAsDropDown(anchor);
        }
    }
}
