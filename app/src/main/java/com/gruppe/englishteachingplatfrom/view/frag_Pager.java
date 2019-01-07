package com.gruppe.englishteachingplatfrom.view;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.gruppe.englishteachingplatfrom.R;
import com.gruppe.englishteachingplatfrom.controller.FragPagerAdapter;

public class frag_Pager extends Fragment{

        ViewPager mPager;
        FragPagerAdapter mAdapter;

        public frag_Pager() {
            // Required empty public constructor
        }


        @Override
        public void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);

        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View view =  inflater.inflate(R.layout.pager_test, container, false);


            mPager = (ViewPager) view.findViewById(R.id.pager);
            mAdapter = new FragPagerAdapter(getChildFragmentManager());
            mPager.setAdapter(mAdapter);
            return view;
        }


    }


