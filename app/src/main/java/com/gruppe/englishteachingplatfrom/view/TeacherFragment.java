package com.gruppe.englishteachingplatfrom.view;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.gruppe.englishteachingplatfrom.R;
import com.gruppe.englishteachingplatfrom.model.Feedback;

import java.util.ArrayList;


public class TeacherFragment extends Fragment implements View.OnClickListener {

    public static final ArrayList<Feedback> list = new ArrayList<Feedback>();
    LinearLayout inbox, pay;
    LinearLayout feed;
    ScrollView scroll;
    TextView viewAll;

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
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.fragment_teacher, container, false);

        feed = view.findViewById(R.id.teacher_feedback);
        inbox = view.findViewById(R.id.inbox);
    /*    viewAll = view.findViewById(R.id.viewAll);*/
        pay = view.findViewById(R.id.pay);
//        viewAll.setOnClickListener(this);
        pay.setOnClickListener(this);


        //list.add(new Feedback("Xian",3, "Good teacher"));
        //list.add(new Feedback("Geng",5, "AMAZING LESSON! learned a lot for just 2 hours of study"));

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

//                ft.replace(R.id.fragmentContent, new RequestFragment());
//                ft.commit();

                System.out.println("Teachers_fragment.java: Du trykkede på inbox");
            }
        });
        return view;
    }


    @Override
    public void onClick(View v) {
/*        if(v == viewAll){
            FragmentTransaction ft = getFragmentManager().beginTransaction();
            ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
            ft.replace(R.id.fragmentContent, new FeedbackFragment());
            ft.addToBackStack(null);
            ft.commit();
        }*/
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