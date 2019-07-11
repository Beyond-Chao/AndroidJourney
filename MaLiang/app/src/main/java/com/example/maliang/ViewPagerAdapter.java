package com.example.maliang;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;


import java.util.ArrayList;
import java.util.List;

/**
 * Copyright (C), 2014-2019, Yutou Technology (Hangzhou) Co, Ltd.
 * FileName: ViewPagerAdapter
 * Author: BeyondChao
 * Date: 2019-07-11
 */
public class ViewPagerAdapter extends FragmentPagerAdapter {

    private List<Fragment> mFragments= new ArrayList<>();

    public ViewPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        return mFragments.get(position);
    }


    @Override
    public int getCount() {
        return mFragments.size();
    }

    public void addFragment(Fragment fragment) {
        mFragments.add(fragment);
    }
}
