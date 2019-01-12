package com.gruppe.englishteachingplatfrom.view;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.gruppe.englishteachingplatfrom.R;
import com.gruppe.englishteachingplatfrom.controller.FragPagerAdapter;
import com.gruppe.englishteachingplatfrom.model.Singleton;
import com.gruppe.englishteachingplatfrom.model.TeacherProfile;

import java.util.ArrayList;

public class frag_Pager extends Fragment implements View.OnClickListener {

        ViewPager mPager;
        FragPagerAdapter mAdapter;
        private Singleton teacher = Singleton.getInstance();
        private FloatingActionButton floating_Send;
        private FloatingActionButton floating_Fav;
        private ImageView imageView;
        private ArrayList<TeacherProfile> contents = teacher.getTeacherDummies();
        private int position1;
        private int pic1;

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
            View view =  inflater.inflate(R.layout.fragment_viewpager_list, container, false);




            teacher = Singleton.getInstance();

            floating_Fav = view.findViewById(R.id.floating_fav);
            floating_Send = view.findViewById(R.id.floating_send);

            floating_Send.setOnClickListener(this);
            floating_Fav.setOnClickListener(this);


            mPager = view.findViewById(R.id.ViewPager);
            mPager.setPageTransformer(true, new ViewPagerStack());
            mPager.setOffscreenPageLimit(3);
            mAdapter = new FragPagerAdapter(getChildFragmentManager() );
            mPager.setAdapter(mAdapter);

            // Need to find out how to have different pictures
            return view;
        }

    public int getCurrentPosition(){
        return mPager.getCurrentItem();
    }

    public int getCurrentPic(){
        return teacher.getTeacherDummies().get(getCurrentPosition()).getProfilePic();
    }

    private class ViewPagerStack implements ViewPager.PageTransformer {

        @Override
        public void transformPage(View page, float position) {

            if (position >= 0) {

             /*   page.setScaleX(0.8f - 0.05f * position);
                page.setScaleY(0.8f);*/

                page.setTranslationX(-page.getWidth() * position);

                page.setTranslationY(-30 * position);
            }
        }
    }

    @Override
    public void onClick(View v) {

        if (v == floating_Send) {
            Fragment frag = getActivity().getSupportFragmentManager().findFragmentById(R.id.fragmentContent);
            if (frag instanceof ViewPagerFragment) {
                position1 = ((ViewPagerFragment) frag).getCurrentPosition();
                pic1 = ((ViewPagerFragment) frag).getCurrentPic();
            }
            Bundle bundle = new Bundle();
            bundle.putInt("position", position1);
            bundle.putInt("pic", pic1);
            Fragment F = new DialogBox();
            F.setArguments(bundle);
            getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.fragmentContent, F).
                    addToBackStack(null).commit();
        }
        if(v == floating_Fav){
            // checker whether the teacher is favorited by the student and set the image accordingly
            // should have the standard heart images for favorite (empty and filled)
            ((FloatingActionButton) v).setImageResource(R.drawable.ic_if_filter_1608702);
        }

    }
}


