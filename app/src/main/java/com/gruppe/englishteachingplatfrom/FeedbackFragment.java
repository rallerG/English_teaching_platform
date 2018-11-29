package com.gruppe.englishteachingplatfrom;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.DrawableRes;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.TextView;

import com.gruppe.englishteachingplatfrom.dummy.DummyContent;
import com.gruppe.englishteachingplatfrom.dummy.DummyContent.DummyItem;

import java.util.ArrayList;
import java.util.List;


public class FeedbackFragment extends Fragment implements View.OnClickListener {

    private static final String ARG_COLUMN_COUNT = "column-count";
    private int mColumnCount = 1;
    RecyclerView feedback;
    ArrayList<FeedbackProfile> list;
    TextView star1, star2, star3, star4, star5, ratings, totalRate;
    LinearLayout oneStar, twoStar, threeStar, fourStar, fiveStar, all, prevBtn;
    RatingBar totalRating;
    float totAvgRating;


    public FeedbackFragment() {
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        list = new ArrayList<>();
        list.add(new FeedbackProfile("Xian", 3, "Good teacher"));
        list.add(new FeedbackProfile("Geng", 5, "AMAZING LESSON! learned a lot for just 2 hours of study"));
        list.add(new FeedbackProfile("Chuang", 1, "Bad teacher"));
        list.add(new FeedbackProfile("Jin", 2, "Couldn't follow his lesson"));
        list.add(new FeedbackProfile("Li", 5, "Cool teacher"));
        list.add(new FeedbackProfile("Xia", 3, "Nice"));
        list.add(new FeedbackProfile("Huan", 5, "I recommend this teacher, he is very kind and can help overtime if needed"));


        if (getArguments() != null) {
            mColumnCount = getArguments().getInt(ARG_COLUMN_COUNT);
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_feedback_list, container, false);

        feedback = view.findViewById(R.id.list);
        star1 = view.findViewById(R.id.star1);
        star2 = view.findViewById(R.id.star2);
        star3 = view.findViewById(R.id.star3);
        star4 = view.findViewById(R.id.star4);
        star5 = view.findViewById(R.id.star5);
        ratings = view.findViewById(R.id.ratings);
        totalRating = view.findViewById(R.id.totalRating);
        totalRate = view.findViewById(R.id.totalRate);
        oneStar = view.findViewById(R.id.oneStar);
        twoStar = view.findViewById(R.id.twoStar);
        threeStar = view.findViewById(R.id.threeStar);
        fourStar = view.findViewById(R.id.fourStar);
        fiveStar = view.findViewById(R.id.fiveStar);
        all = view.findViewById(R.id.all);
        oneStar.setOnClickListener(this);
        twoStar.setOnClickListener(this);
        threeStar.setOnClickListener(this);
        fourStar.setOnClickListener(this);
        fiveStar.setOnClickListener(this);
        all.setOnClickListener(this);

        int totStar1 = 0;
        int totStar2 = 0;
        int totStar3 = 0;
        int totStar4 = 0;
        int totStar5 = 0;
        int totalStar = 0;

        for (FeedbackProfile feed : list) {
            switch (feed.getRating()) {
                case 1:
                    totStar1++;
                    totalStar += feed.getRating();
                    break;
                case 2:
                    totStar2++;
                    totalStar += feed.getRating();
                    break;
                case 3:
                    totStar3++;
                    totalStar += feed.getRating();
                    break;
                case 4:
                    totStar4++;
                    totalStar += feed.getRating();
                    break;
                case 5:
                    totStar5++;
                    totalStar += feed.getRating();
                    break;
                default:
            }
        }
        star1.setText(String.valueOf(totStar1));
        star2.setText(String.valueOf(totStar2));
        star3.setText(String.valueOf(totStar3));
        star4.setText(String.valueOf(totStar4));
        star5.setText(String.valueOf(totStar5));
        ratings.setText(String.valueOf(list.size()) + " ratings");

        totAvgRating = totalStar / list.size();
        totalRate.setText(String.valueOf(totAvgRating));
        totalRating.setRating(totAvgRating);


        all.setBackgroundResource(R.drawable.selectedborder);
        prevBtn = all;
        // Set the adapter
        //   if (view instanceof RecyclerView) {
        Context context = view.getContext();
        MyFeedbackRecyclerViewAdapter recycleAdapter = new MyFeedbackRecyclerViewAdapter(getContext(), list);
        feedback.setLayoutManager(new LinearLayoutManager(getActivity()));
        feedback.setAdapter(recycleAdapter);
        return view;
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

    public ArrayList<FeedbackProfile> getFeedbackList() {
        return list;
    }

    public void setFeedbackList(ArrayList<FeedbackProfile> list) {
        this.list = list;
    }

    @Override
    public void onClick(View v) {

        if (v == oneStar) {
            sortList(1, oneStar, prevBtn);
            prevBtn = oneStar;
        } else if (v == twoStar) {
            sortList(2, twoStar, prevBtn);
            prevBtn = twoStar;
        } else if (v == threeStar) {
            sortList(3, threeStar, prevBtn);
            prevBtn = threeStar;
        } else if (v == fourStar) {
            sortList(4, fourStar, prevBtn);
            prevBtn = fourStar;
        } else if (v == fiveStar) {
            sortList(5, fiveStar, prevBtn);
            prevBtn = fiveStar;
        } else if (v == all) {
            MyFeedbackRecyclerViewAdapter recycleAdapter = new MyFeedbackRecyclerViewAdapter(getContext(), list);
            feedback.setLayoutManager(new LinearLayoutManager(getActivity()));
            feedback.setAdapter(recycleAdapter);
            prevBtn.setBackgroundResource(R.drawable.border);
            all.setBackgroundResource(R.drawable.selectedborder);
            prevBtn = all;
        }
    }

    public void sortList(int rating, LinearLayout btn, LinearLayout prevBtn) {
        ArrayList<FeedbackProfile> tempList = new ArrayList<FeedbackProfile>();
        tempList.clear();

        this.prevBtn = prevBtn;
        prevBtn.setBackgroundResource(R.drawable.border);
        btn.setBackgroundResource(R.drawable.selectedborder);
        for (FeedbackProfile f : list) {
            if (f.getRating() == rating) {
                tempList.add(new FeedbackProfile(f.getStudName(), f.getRating(), f.getContent()));
            }
        }
        MyFeedbackRecyclerViewAdapter recycleAdapter = new MyFeedbackRecyclerViewAdapter(getContext(), tempList);
        feedback.setLayoutManager(new LinearLayoutManager(getActivity()));
        feedback.setAdapter(recycleAdapter);

    }

}
