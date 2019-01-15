package com.gruppe.englishteachingplatfrom.view;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.TextView;

import com.gruppe.englishteachingplatfrom.model.Feedback;
import com.gruppe.englishteachingplatfrom.controller.MyFeedbackRecyclerViewAdapter;
import com.gruppe.englishteachingplatfrom.R;

import java.util.ArrayList;


public class FeedbackFragment extends Fragment implements View.OnClickListener {

    private static final String ARG_COLUMN_COUNT = "column-count";
    private int mColumnCount = 1;
    RecyclerView feedback;
    ArrayList<Feedback> list;
    TextView star1, star2, star3, star4, star5, ratings, totalRate;
    LinearLayout oneStar, twoStar, threeStar, fourStar, fiveStar, all, prevBtn;
    RatingBar totalRating;
    double totAvgRating;


    public FeedbackFragment() {
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        list = new ArrayList<>();
        /*list.add(new Feedback("Xian", 3, "Good teacher"));
        list.add(new Feedback("Geng", 5, "AMAZING LESSON! learned a lot for just 2 hours of study"));
        list.add(new Feedback("Chuang", 1, "Bad teacher"));
        list.add(new Feedback("Jin", 2, "Couldn't follow his lesson"));
        list.add(new Feedback("Li", 5, "Cool teacher"));
        list.add(new Feedback("Xia", 3, "Nice"));
        list.add(new Feedback("Huan", 5, "I recommend this teacher, he is very kind and can help overtime if needed"));
        */

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

        double totStar1 = 0;
        double totStar2 = 0;
        double totStar3 = 0;
        double totStar4 = 0;
        double totStar5 = 0;
        double totalStar = 0;

        for (Feedback feed : list) {
            if(feed.getRating() < 1) {
                totStar1++;
                totalStar += feed.getRating();
            }
            else if (1 > feed.getRating() && feed.getRating() < 2) {
                totStar2++;
                totalStar += feed.getRating();
            }
            else if (2 > feed.getRating() && feed.getRating() < 3) {
                totStar3++;
                totalStar += feed.getRating();
            }
             else if (3 > feed.getRating() && feed.getRating() < 4) {
                totStar4++;
                totalStar += feed.getRating();
            }
            else if (4 > feed.getRating() && feed.getRating() < 5) {
                totStar5++;
                totalStar += feed.getRating();
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
        totalRating.setRating((float) totAvgRating);


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

    public ArrayList<Feedback> getFeedbackList() {
        return list;
    }

    public void setFeedbackList(ArrayList<Feedback> list) {
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
        ArrayList<Feedback> tempList = new ArrayList<Feedback>();
        tempList.clear();

        this.prevBtn = prevBtn;
        prevBtn.setBackgroundResource(R.drawable.border);
        btn.setBackgroundResource(R.drawable.selectedborder);
        for (Feedback f : list) {
            if (f.getRating() == rating) {
                tempList.add(new Feedback(f.getStudentProfile(), f.getRating(), f.getContent()));
            }
        }
        MyFeedbackRecyclerViewAdapter recycleAdapter = new MyFeedbackRecyclerViewAdapter(getContext(), tempList);
        feedback.setLayoutManager(new LinearLayoutManager(getActivity()));
        feedback.setAdapter(recycleAdapter);

    }

}
