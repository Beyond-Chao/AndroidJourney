package com.example.animationset;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.net.Uri;
import android.os.Bundle;
import android.transition.Slide;
import android.view.Gravity;

import com.facebook.drawee.view.SimpleDraweeView;

public class MainActivity extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private MyRecyclerViewAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setupWindowAnimations();

        String headerPath = "res://" + getPackageName() + "/" + R.drawable.header_bg;
        ((SimpleDraweeView) findViewById(R.id.view_header)).setImageURI(Uri.parse(headerPath));

        mRecyclerView = findViewById(R.id.recycler);
        mRecyclerView.setFocusable(false);
        mRecyclerView.setLayoutManager(new GridLayoutManager(this, 2));
        mRecyclerView.setNestedScrollingEnabled(false);

        int[] gifArray = new int[]{ R.drawable.view_gif,
                                    R.drawable.drawable_gif,
                                    R.drawable.property_gif,
                                    R.drawable.ripple_gif,
                                    R.drawable.reveal_effect_gif,
                                    R.drawable.transition_gif,
                                    R.drawable.view_state_gif,
                                    R.drawable.vector_gif
        };

        mAdapter = new MyRecyclerViewAdapter(gifArray, getResources().getStringArray(R.array.itemName), this);
        mRecyclerView.setAdapter(mAdapter);
    }

    private void setupWindowAnimations() {
        Slide slide = new Slide();
        slide.setSlideEdge(Gravity.TOP);
        slide.setDuration(2000);
        getWindow().setReenterTransition(slide);
        getWindow().setExitTransition(slide);
    }
}
