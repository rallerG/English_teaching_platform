package com.gruppe.englishteachingplatfrom.controller;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

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
        Bundle bundle = new Bundle();
        bundle.putInt("position", position);
        Fragment page = new PageFragment();
        page.setArguments(bundle);
        return page;
    }

//    @Override
//    public int getItemPosition(@NonNull Object object) {
//        return POSITION_NONE;
//    }


    @Override
    public int getCount() {
        return p.getTeacherDummies().size();
    }


    @Override
    public void onClick(View v) {

    }
}
