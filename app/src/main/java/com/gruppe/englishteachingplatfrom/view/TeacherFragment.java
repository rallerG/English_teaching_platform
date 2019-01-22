package com.gruppe.englishteachingplatfrom.view;

import android.content.Context;
import android.os.Bundle;
import android.os.SystemClock;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.TextView;

import com.gruppe.englishteachingplatfrom.R;
import com.gruppe.englishteachingplatfrom.backend.implementations.StudentsDocumentImpl;
import com.gruppe.englishteachingplatfrom.backend.implementations.TeacherReviewDocumentImpl;
import com.gruppe.englishteachingplatfrom.backend.implementations.TeacherMatchesDocumentImpl;
import com.gruppe.englishteachingplatfrom.backend.interfaces.Callback;
import com.gruppe.englishteachingplatfrom.backend.interfaces.CallbackList;
import com.gruppe.englishteachingplatfrom.backend.interfaces.StudentsDocument;
import com.gruppe.englishteachingplatfrom.backend.interfaces.TeacherReviewDocument;
import com.gruppe.englishteachingplatfrom.backend.interfaces.TeacherMatchesDocument;
import com.gruppe.englishteachingplatfrom.model.Review;
import com.gruppe.englishteachingplatfrom.model.Singleton;
import com.gruppe.englishteachingplatfrom.model.StudentProfile;

import java.util.ArrayList;
import java.util.List;


public class TeacherFragment extends Fragment implements View.OnClickListener {

    public static final ArrayList<Review> list = new ArrayList<Review>();
    LinearLayout inbox, pay, schedule;
    LinearLayout feed;
    RatingBar rating;
    ImageView profilePic;
    TextView reviews, students;
    Singleton p = Singleton.getInstance();
    int rate = 0;
    int totalReviews = 0;
    private long mLastClickTime = 0;

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
            list.clear();

        }
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_teacher, container, false);

        feed = view.findViewById(R.id.teacher_feedback);
        feed.setOnClickListener(this);
        inbox = view.findViewById(R.id.inbox);
        inbox.setOnClickListener(this);
        schedule = view.findViewById(R.id.schedule);
        schedule.setOnClickListener(this);
        pay = view.findViewById(R.id.pay);
        pay.setOnClickListener(this);
        rating = view.findViewById(R.id.ratingbar_teacher);
        reviews = view.findViewById(R.id.reviews);
        students = view.findViewById(R.id.students);
        profilePic = view.findViewById(R.id.imageView8);
        profilePic.setImageResource(p.getCurrrentTeacher().getProfilePic());

            if(savedInstanceState == null) {
                System.out.println("TeacherFragment.java: Start of savedInstanceState");

                TeacherReviewDocument feedbackDocument = new TeacherReviewDocumentImpl(p.getCurrrentTeacher().getId());
                feedbackDocument.getAll(new CallbackList<Review>() {
                    @Override
                    public void onCallback(List<Review> listOfObjects) {
                        rate = 0;
                        for (Review review : listOfObjects) {
                            list.clear();
                            list.add(review);
                            rate += review.getRating();
                            totalReviews = list.size();
                        }
                        //    students.setText("" + p.getCurrrentTeacher().getMatchProfiles().size());
                        if (totalReviews == 0) {
                            rating.setRating(0);
                            reviews.setText(0 + " Reviews");
                        } else {
                            rating.setRating((float) rate / totalReviews);
                            reviews.setText(totalReviews + " Reviews");
                        }
                    }
                });
                //students.setText("" + p.getCurrrentTeacher().getMatchProfiles().size());
            }

        System.out.println("TeacherFragment.java: Rating is " + p.getCurrrentTeacher().getRating());

        Context context = view.getContext();

        return view;
    }


    @Override
    public void onClick(View v) {
        if (SystemClock.elapsedRealtime() - mLastClickTime < 1000) {
            return;
        }
        mLastClickTime = SystemClock.elapsedRealtime();

        if(v == pay){
            FragmentTransaction ft = getFragmentManager().beginTransaction();
            ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
            ft.replace(R.id.fragmentContent, new SendRequestPaymentFragment()); // replace with payment fragment
            ft.addToBackStack(null);
            ft.commit();
        }
        else if (v == feed) {
                FragmentTransaction ft = getFragmentManager().beginTransaction();
                ft.replace(R.id.fragmentContent, new ReviewFragment());
                ft.addToBackStack(null);
                ft.commit();
        }
        else if (v == inbox) {
                FragmentTransaction ft = getFragmentManager().beginTransaction();
                ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
                ft.replace(R.id.fragmentContent, new RequestFragment());
                ft.addToBackStack(null);
                ft.commit();
        }
        else if (v == schedule) {
                FragmentTransaction ft = getFragmentManager().beginTransaction();
                ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
                ft.replace(R.id.fragmentContent, new ScheduleTeacherFragment());
                ft.addToBackStack(null);
                ft.commit();
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }
}
