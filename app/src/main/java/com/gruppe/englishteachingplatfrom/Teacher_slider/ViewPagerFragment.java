package com.gruppe.englishteachingplatfrom.Teacher_slider;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.gruppe.englishteachingplatfrom.MyFavoriteRecyclerViewAdapter;
import com.gruppe.englishteachingplatfrom.R;
import com.gruppe.englishteachingplatfrom.SingletonData;
import com.gruppe.englishteachingplatfrom.TeacherProfile;

import java.util.ArrayList;


public class ViewPagerFragment extends Fragment {

    private ViewPager mViewPager;

    private ViewPagerAdapter mAdapter;

    private ArrayList<Integer> img = new ArrayList<Integer>();

    private ArrayList<ViewPagerModel> mContents;

    private SingletonData info;

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

        int images[] = {R.drawable.profile1, R.drawable.profile2, R.drawable.profile3 };

        img.add(R.drawable.profile1);
        img.add(R.drawable.profile2);
        img.add(R.drawable.profile3);

       // String names[] = {"Smith", "Johnson", "David", "Adam"};

        info.getNames().add("Smith");
        info.getNames().add("Johnson");
        info.getNames().add("David");
        info.getNames().add("Adam");

       // String desig[] = {"English Teacher"};
        info.getProf().add("English Teacher");

       // String place[] = {"USA", "DENMARK", "SWEDEN"};

        info.getNation().add("USA");
        info.getNation().add("Denmark");
        info.getNation().add("Sweden");

        ArrayList<String> names = info.getNames();
        ArrayList<String> prof = info.getProf();
        ArrayList<String> nation = info.getNation();
        for (int i = 0; i < images.length; i++){

            ViewPagerModel viewPagerModel = new ViewPagerModel();

            viewPagerModel.images = images[i];

            viewPagerModel.names = names.get(i);

            viewPagerModel.desig = prof.get(0);

            viewPagerModel.place = nation.get(i);

            mContents.add(viewPagerModel);

        }

        mAdapter = new ViewPagerAdapter(mContents, getActivity());
        mViewPager.setPageTransformer(true, new ViewPagerStack());
        mViewPager.setOffscreenPageLimit(3);


        mViewPager.setAdapter(mAdapter);

        return view;
    }

    public int getCurrentPosition(){
        return mViewPager.getCurrentItem();
    }

    public int getCurrentPic(){

        return img.get(mViewPager.getCurrentItem());
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
