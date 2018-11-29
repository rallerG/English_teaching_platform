package com.gruppe.englishteachingplatfrom.testNewPager;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.view.View;

public class FragPagerAdapter extends FragmentPagerAdapter implements View.OnClickListener {

    private static final int Num_Pages = 3;


    public FragPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int i) {
        return new Page_frag();
    }

    @Override
    public int getCount() {
        return Num_Pages;
    }

    @Override
    public void onClick(View v) {

    }
}
