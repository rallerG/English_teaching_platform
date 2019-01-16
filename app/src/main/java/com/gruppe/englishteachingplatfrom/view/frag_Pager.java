package com.gruppe.englishteachingplatfrom.view;

import android.content.res.Resources;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.load.engine.Resource;
import com.gruppe.englishteachingplatfrom.R;
import com.gruppe.englishteachingplatfrom.controller.FragPagerAdapter;
import com.gruppe.englishteachingplatfrom.model.Singleton;
import com.gruppe.englishteachingplatfrom.model.TeacherProfile;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

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
        private int checker = 0;
        private Map<Integer,Integer> hm = new HashMap<Integer,Integer>();
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

            for(int i = 0; i < mAdapter.getCount(); i++){
                hm.put(i,0);
            }

            mPager.addOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener() {
                @Override
                public void onPageSelected(int position) {
                    System.out.println("frag_Pager.java: page changed " + position);
                    // code for action on page change
                    if(hm.get(position) != null){
                        if( hm.get(position) == 1){
                            System.out.println(""+ hm.get(position));
                            floating_Fav.setImageResource(R.drawable.favourite_full);
                        }
                        else if (hm.get(position) == 0)floating_Fav.setImageResource(R.drawable.favourite_empty);
                    }

                }
            });
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

                page.setScaleX(1.0f -0.03f * position);
               // page.setScaleY(0.8f);

                page.setTranslationX(-page.getWidth() * position);

                page.setTranslationY(10 * position);
            }
        }
    }

    @Override
    public void onClick(View v) {

        if (v == floating_Send) {
            position1 = mPager.getCurrentItem();
            Bundle bundle = new Bundle();
            bundle.putInt("position", position1);
           // bundle.putInt("pic", pic1);
            Fragment F = new DialogBox();
            F.setArguments(bundle);
            getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.fragmentContent, F).
                    addToBackStack(null).commit();
        }
        if(v == floating_Fav){
            // checker whether the teacher is favorited by the student and set the image accordingly
            // should have the standard heart images for favorite (empty and filled)
            Resources res = getContext().getResources();
            final int newColor = res.getColor(R.color.Heart);


            if(checker == 0) {
                ((FloatingActionButton) v).setImageResource(R.drawable.favourite_full);
                ((FloatingActionButton) v).setBackgroundColor(Color.parseColor("#FF0023"));
                if(hm.get(mPager.getCurrentItem()) != null){
                    hm.remove(mPager.getCurrentItem());
                    hm.put(mPager.getCurrentItem(), 1);
                } else{ hm.put(mPager.getCurrentItem(), 1); }

                checker = 1;
            } else  {
                ((FloatingActionButton) v).setImageResource(R.drawable.favourite_empty);
                if(hm.get(mPager.getCurrentItem()) != null){
                    hm.remove(mPager.getCurrentItem());
                    hm.put(mPager.getCurrentItem(), 0);
                } else{ hm.put(mPager.getCurrentItem(), 0); }

                checker = 0;
            }
        }

    }
}


