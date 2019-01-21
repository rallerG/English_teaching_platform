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
import android.widget.ProgressBar;
import android.widget.RatingBar;
import android.widget.TextView;

import com.gruppe.englishteachingplatfrom.backend.implementations.StudentsDocumentImpl;
import com.gruppe.englishteachingplatfrom.backend.implementations.TeacherFeedbackDocumentImpl;
import com.gruppe.englishteachingplatfrom.backend.interfaces.Callback;
import com.gruppe.englishteachingplatfrom.backend.interfaces.CallbackList;
import com.gruppe.englishteachingplatfrom.backend.interfaces.StudentsDocument;
import com.gruppe.englishteachingplatfrom.backend.interfaces.TeacherFeedbackDocument;
import com.gruppe.englishteachingplatfrom.model.DocumentObject;
import com.gruppe.englishteachingplatfrom.model.Feedback;
import com.gruppe.englishteachingplatfrom.controller.MyFeedbackRecyclerViewAdapter;
import com.gruppe.englishteachingplatfrom.R;
import com.gruppe.englishteachingplatfrom.model.Singleton;
import com.gruppe.englishteachingplatfrom.model.StudentProfile;

import java.util.ArrayList;
import java.util.List;


public class FeedbackFragment extends Fragment implements View.OnClickListener {

    private Singleton p = Singleton.getInstance();
    private static final String ARG_COLUMN_COUNT = "column-count";
    private int mColumnCount = 1;
    RecyclerView feedback;
    ArrayList<Feedback> list;
    TextView star1, star2, star3, star4, star5, ratings, totalRate;
    LinearLayout oneStar, twoStar, threeStar, fourStar, fiveStar, all, prevBtn;
    RatingBar totalRating;
    int totalReviews;
    double totAvgRating, totalStar = 0;
    private ProgressBar loader;
    private MyFeedbackRecyclerViewAdapter recycleAdapter;
    int totStar1 = 0;
    int totStar2 = 0;
    int totStar3 = 0;
    int totStar4 = 0;
    int totStar5 = 0;

    public FeedbackFragment() {
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        list = new ArrayList<>();


        if (getArguments() != null) {
            mColumnCount = getArguments().getInt(ARG_COLUMN_COUNT);
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_feedback_list, container, false);

        loader = view.findViewById(R.id.loader);
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


        ratings.setText(String.valueOf(list.size()) + " ratings");


        all.setBackgroundResource(R.drawable.selectedborder);
        prevBtn = all;


        feedback.setVisibility(View.INVISIBLE);
        TeacherFeedbackDocument feedbackDocument = new TeacherFeedbackDocumentImpl(p.getCurrrentTeacher().getId());
        feedbackDocument.getAll(new CallbackList<Feedback>() {
            @Override
            public void onCallback(List<Feedback> listOfObjects) {
                for (Feedback feedback : listOfObjects) {
                    list.add(feedback);
                    totalStar += feedback.getRating();
                    totalReviews ++;
                }
                totAvgRating = totalStar / list.size();

                if(totalReviews == 0){
                    totalRate.setText("" + 0);
                } else {
                    totalRate.setText(String.valueOf(totAvgRating));
                    totalRating.setRating((float) totAvgRating);
                }

                ratings.setText(totalReviews  + " Ratings");
                recycleAdapter = new MyFeedbackRecyclerViewAdapter(getContext(), list);
                feedback.setLayoutManager(new LinearLayoutManager(getActivity()));
                feedback.setAdapter(recycleAdapter);
                loader.setVisibility(View.INVISIBLE);
                feedback.setVisibility(View.VISIBLE);

                for (int i = 0; i < list.size(); i++) {
                    if(list.get(i).getRating() <= 1) {
                        totStar1++;
                    }
                    else if (1 < list.get(i).getRating() && list.get(i).getRating() <= 2) {
                        totStar2++;
                    }
                    else if (2 < list.get(i).getRating() && list.get(i).getRating() <= 3) {
                        totStar3++;
                    }
                    else if (3 < list.get(i).getRating() && list.get(i).getRating() <= 4) {
                        totStar4++;
                    }
                    else if (4 < list.get(i).getRating() && list.get(i).getRating() <= 5) {
                        totStar5++;
                    }
                }
                star1.setText(String.valueOf(totStar1));
                star2.setText(String.valueOf(totStar2));
                star3.setText(String.valueOf(totStar3));
                star4.setText(String.valueOf(totStar4));
                star5.setText(String.valueOf(totStar5));
            }
        });
        System.out.println("FeedbackFragment.java: " + list);


     //   totAvgRating = totalStar / list.size();
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
            loader.setVisibility(View.VISIBLE);
            feedback.setVisibility(View.INVISIBLE);
            feedback.setAdapter(recycleAdapter);
            prevBtn.setBackgroundResource(R.drawable.border);
            all.setBackgroundResource(R.drawable.selectedborder);
            prevBtn = all;
            feedback.setVisibility(View.VISIBLE);
        }
        loader.setVisibility(View.INVISIBLE);
    }

    public void sortList(int rating, LinearLayout btn, LinearLayout prevBtn) {
        ArrayList<Feedback> tempList = new ArrayList<Feedback>();
        tempList.clear();

        this.prevBtn = prevBtn;
        prevBtn.setBackgroundResource(R.drawable.border);
        btn.setBackgroundResource(R.drawable.selectedborder);
        for (Feedback f : list) {
            if (f.getRating() == rating) {
                tempList.add(f);
            }
        }
        MyFeedbackRecyclerViewAdapter recycleAdapter = new MyFeedbackRecyclerViewAdapter(getContext(), tempList);
        feedback.setLayoutManager(new LinearLayoutManager(getActivity()));
        feedback.setAdapter(recycleAdapter);
    }
}
