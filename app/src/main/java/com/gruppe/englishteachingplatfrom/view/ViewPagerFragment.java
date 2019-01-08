package com.gruppe.englishteachingplatfrom.view;

import android.os.Bundle;
import android.os.Parcelable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.gruppe.englishteachingplatfrom.R;
import com.gruppe.englishteachingplatfrom.model.TeacherProfile;
import com.gruppe.englishteachingplatfrom.controller.ViewPagerAdapter;
import com.gruppe.englishteachingplatfrom.model.SingletonData;


public class ViewPagerFragment extends Fragment {

    private ViewPager mViewPager;

    private ViewPagerAdapter mAdapter;

    private SingletonData info;

//    int img[] = {R.drawable.profile1, R.drawable.profile2, R.drawable.profile3,R.drawable.profile3,R.drawable.profile3,R.drawable.profile3,R.drawable.profile3,R.drawable.profile3,R.drawable.profile3 };




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
        if(savedInstanceState != null) {
            mViewPager.onRestoreInstanceState(savedInstanceState);
        }
        if(savedInstanceState == null) {
            info.getTeacher().add(new TeacherProfile("0", "3", "English Teacher", "1", R.drawable.profile1));
            info.getTeacher().add(new TeacherProfile("1", "4", "English Teacher", "2", R.drawable.profile2));
            info.getTeacher().add(new TeacherProfile("2", "1", "English Teacher", "3", R.drawable.profile3));
            info.getTeacher().add(new TeacherProfile("3", "5", "English Teacher", "4", R.drawable.profile3));
            info.getTeacher().add(new TeacherProfile("4", "5", "English Teacher", "5", R.drawable.profile3));
            info.getTeacher().add(new TeacherProfile("5", "5", "English Teacher", "6", R.drawable.profile3));
            info.getTeacher().add(new TeacherProfile("6", "5", "English Teacher", "7", R.drawable.profile3));
            info.getTeacher().add(new TeacherProfile("7", "5", "English Teacher", "8", R.drawable.profile3));
            info.getTeacher().add(new TeacherProfile("8", "5", "English Teacher", "9", R.drawable.profile3));
        }

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
        return info.getTeacher().get(getCurrentPosition()).getmPicture();
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

/*    public void onSaveInstanceState(Bundle state) {
        super.onSaveInstanceState(state);

        // Save list state
        mListState = RecyclerView.LayoutManager.onSaveInstanceState();
        state.putParcelable(LIST_STATE_KEY, mListState);
    }*/


}
