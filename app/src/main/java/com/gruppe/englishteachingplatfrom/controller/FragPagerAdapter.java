package com.gruppe.englishteachingplatfrom.controller;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;

import com.gruppe.englishteachingplatfrom.model.SingletonData;
import com.gruppe.englishteachingplatfrom.model.TeacherProfile;
import com.gruppe.englishteachingplatfrom.view.Page_frag;

import java.util.ArrayList;

public class FragPagerAdapter extends FragmentPagerAdapter implements View.OnClickListener {

    private SingletonData  info = SingletonData.getInstance();
    private ArrayList<TeacherProfile> contents = info.getTeacher();


    public FragPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        return new Page_frag(position);
    }

    @Override
    public int getCount() {
        return contents.size();
    }

    @Override
    public void onClick(View v) {

    }
}
