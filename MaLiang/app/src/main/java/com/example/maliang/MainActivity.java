package com.example.maliang;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    BottomNavigationView mBottomNavigationView;
    private ViewPager mViewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initSubviews();
        initViewPager();
    }

    private void initSubviews() {
        mBottomNavigationView = findViewById(R.id.bottom_navigation_view);
        mViewPager = findViewById(R.id.view_pager);

        mBottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()) {
                    case R.id.main_favorites:
                        mViewPager.setCurrentItem(0);
                        return true;

                    case R.id.main_manage:
                        mViewPager.setCurrentItem(1);
                        return true;

                    case R.id.main_myplaces:
                        mViewPager.setCurrentItem(2);
                        return true;

                    case R.id.main_other:
                        mViewPager.setCurrentItem(3);
                        return true;
                }

                return false;
            }
        });

        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                mBottomNavigationView.getMenu().getItem(position).setChecked(true);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    private void initViewPager() {

        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        adapter.addFragment(new ManageFragment());
        adapter.addFragment(new ManageFragment());
        adapter.addFragment(new ManageFragment());
        adapter.addFragment(new ManageFragment());

        mViewPager.setAdapter(adapter);
    }
}
