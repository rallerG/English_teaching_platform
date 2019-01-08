package com.gruppe.englishteachingplatfrom.view;

import android.os.Bundle;
import android.os.Parcelable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.gruppe.englishteachingplatfrom.R;
import com.gruppe.englishteachingplatfrom.model.TeacherProfile;
import com.gruppe.englishteachingplatfrom.controller.ViewPagerAdapter;
import com.gruppe.englishteachingplatfrom.model.SingletonData;


public class ViewPagerFragment extends Fragment implements View.OnClickListener {

    private ViewPager mViewPager;
    private ViewPagerAdapter mAdapter;
    private SingletonData info;

    Parcelable state;
 //   LinearLayout information;

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
 //       information = view.findViewById(R.id.information);
//        information.setVisibility(View.GONE);

        mViewPager.setOnClickListener(this);

        System.out.println("størrelse af teacher " +info.getTeacher().size());
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

    @Override
    public void onClick(View v) {
        if (v == mViewPager){
            expand();
        }
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

/*    public void onSaveInstanceState(Bundle state) {
        super.onSaveInstanceState(state);

        // Save list state
        mListState = RecyclerView.LayoutManager.onSaveInstanceState();
        state.putParcelable(LIST_STATE_KEY, mListState);
    }*/

    @Override
    public void onPause() {
        // Save ListView state @ onPause
        state = mViewPager.onSaveInstanceState();
        super.onPause();
    }

    public void expand(){

    }

}
