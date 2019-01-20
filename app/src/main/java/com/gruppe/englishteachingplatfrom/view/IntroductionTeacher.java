package com.gruppe.englishteachingplatfrom.view;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.gruppe.englishteachingplatfrom.R;
import com.gruppe.englishteachingplatfrom.view.FragPager;


/*
    This code is from android hive

    Credit:
    "Android How to Build Intro Slider for your App"
    Author: Ravi Tamada
    Created: 12-07-2017
    Last checked: 18-01-2019
    Link: https://www.androidhive.info/2016/05/android-build-intro-slider-app/
 */
//  Our code is modified to work with our own structure

public class IntroductionTeacher extends AppCompatActivity {

    private ViewPager viewPager;
    private MyViewPagerAdapter myViewPagerAdapter;
    private LinearLayout dotsLayout;
    private TextView[] dots;
    private int[] layouts;
    private Button skip,next;
    private Boolean visitedLast = false;
    private float layoutWidth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        if (Build.VERSION.SDK_INT >= 21) {
            getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_STABLE | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);
        }

        setContentView(R.layout.introduction_slider);

        viewPager =  findViewById(R.id.view_pager);
        dotsLayout =  findViewById(R.id.layoutDots);
        skip =  findViewById(R.id.skip);
        next = findViewById(R.id.next);

        layouts = new int[]{
                R.layout.intro_page_teacher1,
                R.layout.intro_page_teacher2,
                R.layout.intro_page_teacher3,
                R.layout.intro_page_teacher4};

        addBottomDots(0);

        //layoutWidth = viewPager.getWidth();
        layoutWidth = viewPager.getWidth()/2;
        //layoutWidth = ( layoutWidth / 2) - (skip.getWidth() / 2) ;

        myViewPagerAdapter = new MyViewPagerAdapter();
        viewPager.setAdapter(myViewPagerAdapter);
        viewPager.addOnPageChangeListener(viewPagerPageChangeListener);

        skip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getStarted();
            }
        });

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // checking for last page
                // if last page home screen will be launched
                int current = getItem(+1);
                if (current < layouts.length) {
                    // move to next screen
                    viewPager.setCurrentItem(current);
                } else {
                    getStarted();
                }
            }
        });

    }

    private void addBottomDots(int currentPage) {
        dots = new TextView[layouts.length];

        int[] colorsActive = getResources().getIntArray(R.array.array_dot_active);
        int[] colorsInactive = getResources().getIntArray(R.array.array_dot_inactive);

        dotsLayout.removeAllViews();
        for (int i = 0; i < dots.length; i++) {
            dots[i] = new TextView(this);
            dots[i].setText(Html.fromHtml("&#8226;"));
            dots[i].setTextSize(35);
            dots[i].setTextColor(colorsInactive[currentPage]);
            dotsLayout.addView(dots[i]);
        }

        if (dots.length > 0)
            dots[currentPage].setTextColor(colorsActive[currentPage]);
    }

    private int getItem(int i) {
        return viewPager.getCurrentItem() + i;
    }

    private void getStarted() {
        startActivity(new Intent(this, TeacherMainActivity.class));
        finish();
    }

    ViewPager.OnPageChangeListener viewPagerPageChangeListener = new ViewPager.OnPageChangeListener() {

        @Override
        public void onPageSelected(int position) {
            addBottomDots(position);

            if (position == layouts.length - 1) {
                next.setVisibility(View.GONE);


                skip.animate().translationX(viewPager.getWidth()/3).start();
         /*       Animation anim = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.to_middle);
                anim.setInterpolator((new AccelerateDecelerateInterpolator()));
                anim.setFillAfter(true);
                next.animate().translationY(50).start();
                skip.setAnimation(anim);*/
                visitedLast = true;

            } else {
                //btnSkip.setVisibility(View.INVISIBLE);
                next.setVisibility(View.VISIBLE);

                if(visitedLast == true) {
                    skip.animate().translationX(layoutWidth).start();
                /*    skip.animate()
                            .translationX((viewPager.getWidth() - ((skip.getWidth()) / 2)+5))
                           // .translationY((viewPager.getHeight() - skip.getHeight()) / 2)
                            .setInterpolator(new AccelerateInterpolator())
                            .setDuration(500);*/



                   /* Animation anim = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.to_left);
                    anim.setInterpolator((new AccelerateDecelerateInterpolator()));
                    anim.setFillAfter(true);
                    skip.setAnimation(anim);
                    visitedLast = false;*/
                }
            }
        }

        @Override
        public void onPageScrolled(int arg0, float arg1, int arg2) { }

        @Override
        public void onPageScrollStateChanged(int arg0) { }
    };

    public class MyViewPagerAdapter extends PagerAdapter {
        private LayoutInflater layoutInflater;

        public MyViewPagerAdapter() { }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            layoutInflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);

            View view = layoutInflater.inflate(layouts[position], container, false);
            container.addView(view);

            return view;
        }

        @Override
        public int getCount() {
            return layouts.length;
        }

        @Override
        public boolean isViewFromObject(View view, Object obj) {
            return view == obj;
        }


        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            View view = (View) object;
            container.removeView(view);
        }
    }
}