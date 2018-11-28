package com.gruppe.englishteachingplatfrom;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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
    LinearLayout oneStar, twoStar, threeStar, fourStar, fiveStar, all;
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

        totAvgRating = totalStar / list.size();
        totalRate.setText(String.valueOf(totAvgRating));
        totalRating.setRating(totAvgRating);

        // Set the adapter
        //   if (view instanceof RecyclerView) {
        Context context = view.getContext();
        MyFeedbackRecyclerViewAdapter recycleAdapter = new MyFeedbackRecyclerViewAdapter(getContext(), list);
        feedback.setLayoutManager(new LinearLayoutManager(getActivity()));
        feedback.setAdapter(recycleAdapter);
/*            if (mColumnCount <= 1) {
                recyclerView.setLayoutManager(new LinearLayoutManager(context));
            } else {
                recyclerView.setLayoutManager(new GridLayoutManager(context, mColumnCount));
            }*/


        //   recyclerView.setAdapter(new MyFeedbackRecyclerViewAdapter(FeedbackProfile.createFeedback(2), mListener));
        //        MyFeedbackRecyclerViewAdapter adapter = new MyFeedbackRecyclerViewAdapter(list,mListener);
        // feedback.setAdapter(new MyFeedbackRecyclerViewAdapter(list, mListener));
        //             feedback.setAdapter(adapter);
//            feedback.setLayoutManager(new LinearLayoutManager(context));
        //  }
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
            sortList(1);
        } else if (v == twoStar) {
            sortList(2);
        } else if (v == threeStar) {
            sortList(3);
        } else if (v == fourStar) {
            sortList(4);
        } else if (v == fiveStar) {
            sortList(5);
        } else if (v == all){
            MyFeedbackRecyclerViewAdapter recycleAdapter = new MyFeedbackRecyclerViewAdapter(getContext(), list);
            feedback.setLayoutManager(new LinearLayoutManager(getActivity()));
            feedback.setAdapter(recycleAdapter);
        }
    }

    public void sortList(int rating) {
        ArrayList<FeedbackProfile> tempList = new ArrayList<FeedbackProfile>();
        tempList.clear();
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
