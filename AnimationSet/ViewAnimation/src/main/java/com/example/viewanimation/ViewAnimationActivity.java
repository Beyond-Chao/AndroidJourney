package com.example.viewanimation;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class ViewAnimationActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_animation);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        findViewById(R.id.btn1).setOnClickListener(this);
        findViewById(R.id.btn2).setOnClickListener(this);
        findViewById(R.id.btn3).setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        int i = view.getId();

        if (R.id.btn1 == i) {
            startActivity(new Intent(this, CommonViewAnimationActivity.class));
        } else if (R.id.btn2 == i) {
            startActivity(new Intent(this, PopupWindowViewAnimationActivity.class));
        } else if (R.id.btn3 == i) {
            startActivity(new Intent(this, ListViewAnimationActivity.class));
        }
    }
}
