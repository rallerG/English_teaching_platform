package com.gruppe.englishteachingplatfrom;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TableLayout;

import java.util.ArrayList;


public class Teacher_fragment extends Fragment implements View.OnClickListener {

    public static final ArrayList<FeedbackProfile> list = new ArrayList<FeedbackProfile>();
    TableLayout inbox;
    LinearLayout feed;
    ScrollView scroll;
    RecyclerView feedback;
    Button viewAll;

    private FeedbackFragment.OnListFragmentInteractionListener mListener;
    private static final String ARG_COLUMN_COUNT = "column-count";
    private int mColumnCount = 1;


    public Teacher_fragment() {
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

        feedback = view.findViewById(R.id.feedback);
        feed = view.findViewById(R.id.feed);
        scroll = view.findViewById(R.id.scroll);
        inbox = view.findViewById(R.id.inbox);
        viewAll = view.findViewById(R.id.viewAll);
        viewAll.setOnClickListener(this);


        list.add(new FeedbackProfile("Xian",3, "Good teacher"));
        list.add(new FeedbackProfile("Geng",5, "AMAZING LESSON! learned a lot for just 2 hours of study"));

            Context context = view.getContext();
        MyFeedbackRecyclerViewAdapter recycleAdapter = new MyFeedbackRecyclerViewAdapter(getContext(),list);
        feedback.setLayoutManager(new LinearLayoutManager(getActivity()));
        feedback.setAdapter(recycleAdapter);

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

                System.out.println("Du trykkede på inbox");
            }
        });
        return view;
    }


    @Override
    public void onClick(View v) {
        if(v == viewAll){
            FragmentTransaction ft = getFragmentManager().beginTransaction();
            ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
            ft.replace(R.id.fragmentContent, new FeedbackFragment());
            ft.addToBackStack(null);
            ft.commit();
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    public interface OnListFragmentInteractionListener {
        // TODO: Update argument type and name
        void onListFragmentInteraction(FeedbackProfile item);
    }
}
