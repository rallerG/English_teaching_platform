package com.gruppe.englishteachingplatfrom.controller;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.util.SparseArray;
import android.view.View;


import com.gruppe.englishteachingplatfrom.model.Singleton;
import com.gruppe.englishteachingplatfrom.view.PageFragment;

import java.util.HashMap;
import java.util.Map;

public class FragPagerAdapter extends FragmentStatePagerAdapter implements View.OnClickListener {

    private Singleton p = Singleton.getInstance();
    private Map<Integer, Fragment> fragmentMap = new HashMap<>();
    private SparseArray<Fragment> fragmentSparseArray = new SparseArray<Fragment>();
//    private ArrayList<TeacherProfile> contents = p.getTeacherDummies();


    public FragPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        Fragment page = PageFragment.newInstance(position);
        return page;
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
        return p.getSwipeTeachers().size();
    }


    public Fragment getFragment(int position){
        return fragmentSparseArray.get(position);
    }

    @Override
    public void onClick(View v) {

    }
}
