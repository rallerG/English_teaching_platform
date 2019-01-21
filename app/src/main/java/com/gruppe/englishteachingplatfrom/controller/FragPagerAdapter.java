package com.gruppe.englishteachingplatfrom.controller;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.view.View;


import com.gruppe.englishteachingplatfrom.model.Singleton;
import com.gruppe.englishteachingplatfrom.view.PageFragment;

public class FragPagerAdapter extends FragmentStatePagerAdapter implements View.OnClickListener {

    private Singleton p = Singleton.getInstance();
//    private ArrayList<TeacherProfile> contents = p.getTeacherDummies();


    public FragPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {

        return PageFragment.newInstance(position);
    }

//    @Override
//    public int getItemPosition(@NonNull Object object) {
//        return POSITION_NONE;
//    }


//    @Override
//    public long getItemId(int position){
//
//        return position;
//    }

    @Override
    public int getCount() {
        return p.getTeacherDummies().size();
    }


    @Override
    public void onClick(View v) {

    }
}
