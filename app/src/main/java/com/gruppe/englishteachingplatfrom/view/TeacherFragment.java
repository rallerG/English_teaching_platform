package com.gruppe.englishteachingplatfrom.view;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.ScrollView;
import android.widget.TextView;

import com.gruppe.englishteachingplatfrom.R;
import com.gruppe.englishteachingplatfrom.backend.implementations.TeacherFeedbackDocumentImpl;
import com.gruppe.englishteachingplatfrom.backend.interfaces.CallbackList;
import com.gruppe.englishteachingplatfrom.backend.interfaces.TeacherFeedbackDocument;
import com.gruppe.englishteachingplatfrom.model.Feedback;
import com.gruppe.englishteachingplatfrom.model.Singleton;

import java.util.ArrayList;
import java.util.List;


public class TeacherFragment extends Fragment implements View.OnClickListener {

    public static final ArrayList<Feedback> list = new ArrayList<Feedback>();
    LinearLayout inbox, pay;
    LinearLayout feed;
    RatingBar rating;
    TextView reviews;
    Singleton p = Singleton.getInstance();
    int rate = 0;
    int totalReviews = 0;

    private static final String ARG_COLUMN_COUNT = "column-count";
    private int mColumnCount = 1;


    public TeacherFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mColumnCount = getArguments().getInt(ARG_COLUMN_COUNT);
        }
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_teacher, container, false);

        feed = view.findViewById(R.id.teacher_feedback);
        inbox = view.findViewById(R.id.inbox);
        rating = view.findViewById(R.id.ratingbar_teacher);
        reviews = view.findViewById(R.id.reviews);
        pay = view.findViewById(R.id.pay);
        pay.setOnClickListener(this);

        TeacherFeedbackDocument feedbackDocument = new TeacherFeedbackDocumentImpl(p.getCurrrentTeacher().getId());
        feedbackDocument.getAll(new CallbackList<Feedback>() {
            @Override
            public void onCallback(List<Feedback> listOfObjects) {
                for (Feedback feedback : listOfObjects) {
                    list.add(feedback);
                    rate += feedback.getRating();
                    totalReviews++;
                }
                if (totalReviews == 0) {
                    rating.setRating(0);
                    reviews.setText(0 + " Reviews");
                } else {
                    rating.setRating((float) rate / totalReviews);
                    reviews.setText(totalReviews+ " Reviews");
                }
            }
        });

        System.out.println("TeacherFragment.java: Rating is " + p.getCurrrentTeacher().getRating());


        Context context = view.getContext();

        feed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentTransaction ft = getFragmentManager().beginTransaction();
                ft.replace(R.id.fragmentContent, new FeedbackFragment());
                ft.addToBackStack(null);
                ft.commit();
            }
        });

        inbox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentTransaction ft = getFragmentManager().beginTransaction();
              //  ft.setCustomAnimations(R.anim.left_to_right,R.anim.left_to_right,R.anim.right_to_left,R.anim.left_to_right)
                ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
                ft.replace(R.id.fragmentContent, new RequestFragment());
                ft.addToBackStack(null);
                ft.commit();


                System.out.println("Teachers_fragment.java: Du trykkede på inbox");
            }
        });
        return view;
    }


    @Override
    public void onClick(View v) {
        if(v == pay){
            FragmentTransaction ft = getFragmentManager().beginTransaction();
            ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
            ft.replace(R.id.fragmentContent, new SendRequestPaymentFragment()); // replace with payment fragment
            ft.addToBackStack(null);
            ft.commit();
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }
}
