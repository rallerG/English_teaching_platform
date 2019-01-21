package com.gruppe.englishteachingplatfrom.view;

import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.gruppe.englishteachingplatfrom.R;
import com.gruppe.englishteachingplatfrom.backend.implementations.StudentFavoritesDocumentImpl;
import com.gruppe.englishteachingplatfrom.backend.interfaces.CallbackSuccess;
import com.gruppe.englishteachingplatfrom.backend.interfaces.StudentFavoritesDocument;
import com.gruppe.englishteachingplatfrom.controller.FragPagerAdapter;
import com.gruppe.englishteachingplatfrom.model.Singleton;
import com.gruppe.englishteachingplatfrom.model.TeacherProfile;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class FragPager extends Fragment implements View.OnClickListener {

        private static ViewPager mPager;
        private FragPagerAdapter mAdapter;
        private Singleton p = Singleton.getInstance();
        private FloatingActionButton floating_Send;
        private FloatingActionButton floating_Fav;
        private int position1;
        private static FragmentManager fragmentMan;



        public FragPager() {
            // Required empty public constructor
        }

        public static FragmentManager getFragman(){
            return fragmentMan;
        }

        @Override
        public void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            fragmentMan = getChildFragmentManager();

        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View view =  inflater.inflate(R.layout.fragment_viewpager_list, container, false);
            fragmentMan = getChildFragmentManager();
            floating_Fav = view.findViewById(R.id.floating_fav);
            floating_Send = view.findViewById(R.id.floating_send);
            floating_Send.setOnClickListener(this);
            floating_Fav.setOnClickListener(this);


            mPager = view.findViewById(R.id.ViewPager);
            mPager.setPageTransformer(true, new ViewPagerStack());
            mPager.setOffscreenPageLimit(3);
            mAdapter = new FragPagerAdapter(getChildFragmentManager() );
            mPager.setAdapter(mAdapter);
            mPager.setSaveFromParentEnabled(false);

//            for(int i = 0; i < mAdapter.getCount(); i++){
//                hm.put(i,0);
//            }

            mPager.addOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener() {
                @Override
                public void onPageSelected(int position) {
                    System.out.println("FragPager.java: page changed " + position);
                    // code for action on page change
//                    if(hm.get(position) != null){
//                        if( hm.get(position) == 1){
//                            System.out.println("FragPager.java: "+ hm.get(position));
//                            floating_Fav.setImageResource(R.drawable.favourite_full);
//                        }
//                        else if (hm.get(position) == 0)floating_Fav.setImageResource(R.drawable.favourite_empty);
//                    }

                }
            });
            // Need to find out how to have different pictures
            return view;
        }

        public static void removeTeacher(FragmentManager fm){
            int pos = mPager.getCurrentItem();

            if(pos < mPager.getChildCount()){
                Singleton.getInstance().getTeacherDummies().remove(pos);
                FragPagerAdapter mAdapt = new FragPagerAdapter(fm);
                mPager.setAdapter(mAdapt);
                mPager.setSaveFromParentEnabled(false);
                mPager.setCurrentItem(pos, true);
            }
            else if(pos == mPager.getChildCount() && pos!=0 ){
                Singleton.getInstance().getTeacherDummies().remove(pos);
                FragPagerAdapter mAdapt = new FragPagerAdapter(fm);
                mPager.setAdapter(mAdapt);
                mPager.setCurrentItem(pos-1, true);
            }
            else if(pos == 0) {
                Singleton.getInstance().getTeacherDummies().remove(pos);
                FragPagerAdapter mAdapt = new FragPagerAdapter(fm);
                mPager.setAdapter(mAdapt);
                mPager.setCurrentItem(pos, true);
            }


        }


         public int getCurrentPosition(){
        return mPager.getCurrentItem();
    }

    private class ViewPagerStack implements ViewPager.PageTransformer {

        @Override
        public void transformPage(View page, float position) {

            if (position >= 0) {

                page.setScaleX(1.0f -0.03f * position);
               // page.setScaleY(0.8f);

                page.setTranslationX(-page.getWidth() * position);

                page.setTranslationY(-15 * position);
            }
        }
    }

    @Override
    public void onClick(View v) {

        position1 = mPager.getCurrentItem();
        String name =  p.getTeacherDummies().get(position1).getName();
        if (v == floating_Send) {
            Bundle bundle = new Bundle();
            bundle.putString("name", name);
            bundle.putInt("price", p.getTeacherDummies().get(position1).getPrice());
            bundle.putFloat("rate", (float) p.getTeacherDummies().get(position1).getRating());
            bundle.putString("language", p.getTeacherDummies().get(position1).getLanguage());
            bundle.putInt("position", position1);
            bundle.putBoolean("isTeacherInfoFragment", false);
            Fragment F = new DialogBoxFragment();
            F.setArguments(bundle);
            getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.fragmentContent, F).
                    addToBackStack(null).commit();
        }
        if(v == floating_Fav){
            // checker whether the teacher is favorited by the student and set the image accordingly
            // should have the standard heart images for favorite (empty and filled)
//                ((FloatingActionButton) v).setImageResource(R.drawable.favourite_full);
//                ((FloatingActionButton) v).setBackgroundColor(Color.parseColor("#FF0023"));
//                StudentFavoritesDocument studentFavoritesDocument = new StudentFavoritesDocumentImpl(p.getCurrrentStudent().getId());
//                studentFavoritesDocument.add((p.getTeacherDummies().get(mPager.getCurrentItem())).getId(), true, new CallbackSuccess() {
//                    @Override
//                    public void onCallback() {
//                        p.getTeacherDummies().remove(mPager.getCurrentItem());
//                        mAdapter.notifyDataSetChanged();
//                    }
//                });
                removeTeacher(getFragman());
                Toast.makeText(getContext(),name + " er blevet tilføjet til favoriter",Toast.LENGTH_SHORT).show();


//                if(hm.get(mPager.getCurrentItem()) != null){
//                    hm.remove(mPager.getCurrentItem());
//                    hm.put(mPager.getCurrentItem(), 1);
//                } else{ hm.put(mPager.getCurrentItem(), 1); }
//
//                checker = 1;
//            } else  {
//                ((FloatingActionButton) v).setImageResource(R.drawable.favourite_empty);
//                if(hm.get(mPager.getCurrentItem()) != null){
//                    hm.remove(mPager.getCurrentItem());
//                    hm.put(mPager.getCurrentItem(), 0);
//                } else{ hm.put(mPager.getCurrentItem(), 0); }



        }

    }
}


