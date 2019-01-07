package com.gruppe.englishteachingplatfrom.view;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.gruppe.englishteachingplatfrom.R;
import com.gruppe.englishteachingplatfrom.model.TeacherProfile;
import com.gruppe.englishteachingplatfrom.controller.ViewPagerAdapter;
import com.gruppe.englishteachingplatfrom.model.SingletonData;

import java.util.ArrayList;


public class ViewPagerFragment extends Fragment {

    private ViewPager mViewPager;

    private ViewPagerAdapter mAdapter;

    private ArrayList<TeacherProfile> mContents;

    private SingletonData info;

    int img[] = {R.drawable.profile1, R.drawable.profile2, R.drawable.profile3,R.drawable.profile3,R.drawable.profile3,R.drawable.profile3,R.drawable.profile3,R.drawable.profile3,R.drawable.profile3 };


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_viewpager_list, container, false);

        info = SingletonData.getInstance();

        mViewPager = (ViewPager) view.findViewById(R.id.ViewPager);

        mContents = new ArrayList<>();


        info.getTeacher().add(new TeacherProfile("Smith","3", "English Teacher", "1" ));
        info.getTeacher().add(new TeacherProfile("Johnson","4", "English Teacher", "2" ));
        info.getTeacher().add(new TeacherProfile("David","1", "English Teacher", "3" ));
        info.getTeacher().add(new TeacherProfile("Adam","5", "English Teacher", "4" ));
        info.getTeacher().add(new TeacherProfile("Adam","5", "English Teacher", "5" ));
        info.getTeacher().add(new TeacherProfile("Adam","5", "English Teacher", "6" ));
        info.getTeacher().add(new TeacherProfile("Adam","5", "English Teacher", "7" ));
        info.getTeacher().add(new TeacherProfile("Adam","5", "English Teacher", "8" ));
        info.getTeacher().add(new TeacherProfile("Adam","5", "English Teacher", "9" ));

        mAdapter = new ViewPagerAdapter(info.getTeacher(), getActivity());
        mViewPager.setPageTransformer(true, new ViewPagerStack());
        mViewPager.setOffscreenPageLimit(3);

        mViewPager.setAdapter(mAdapter);

        return view;
    }

    public int getCurrentPosition(){
        return mViewPager.getCurrentItem();
    }

    public int getCurrentPic(){
        return img[getCurrentPosition()];
    }

    private class ViewPagerStack implements ViewPager.PageTransformer {

        @Override
        public void transformPage(View page, float position) {

            if (position >= 0) {

                page.setScaleX(0.8f - 0.05f * position);
                page.setScaleY(0.8f);

                page.setTranslationX(-page.getWidth() * position);

                page.setTranslationY(-30 * position);
            }
        }
    }
}
