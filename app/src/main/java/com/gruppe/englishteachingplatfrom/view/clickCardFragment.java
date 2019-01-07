package com.gruppe.englishteachingplatfrom.view;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.gruppe.englishteachingplatfrom.R;
import com.gruppe.englishteachingplatfrom.controller.ViewPagerAdapter;
import com.gruppe.englishteachingplatfrom.model.ViewPagerModel;

import java.util.ArrayList;

public class clickCardFragment extends Fragment {

    private ViewPager mViewPager;

    private ViewPagerAdapter mAdapter;

    private ArrayList<ViewPagerModel> mContents;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_viewpager_list, container, false);

        mViewPager = (ViewPager) view.findViewById(R.id.ViewPager);

        mContents = new ArrayList<>();

        int images[] = {R.drawable.profile1, R.drawable.profile2, R.drawable.profile3 };

        String names[] = {"Smith", "Johnson", "David", "Adam"};

        String desig[] = {"English Teacher"};

        String place[] = {"USA", "DENMARK", "SWEDEN"};

        for (int i = 0; i < images.length; i++){

            ViewPagerModel viewPagerModel = new ViewPagerModel();

            viewPagerModel.images = images[i];

            viewPagerModel.names = names[i];

            viewPagerModel.desig = desig[0];

            viewPagerModel.place = place[i];

            mContents.add(viewPagerModel);

        }

        mAdapter = new ViewPagerAdapter(mContents, getActivity());
        mViewPager.setOffscreenPageLimit(3);

        mViewPager.setAdapter(mAdapter);

        return view;
    }
}


