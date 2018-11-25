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

    private FeedbackFragment.OnListFragmentInteractionListener mListener;
    private static final String ARG_COLUMN_COUNT = "column-count";
    private int mColumnCount = 1;




    public Teacher_fragment() {
        // Required empty public constructor
    }

    @SuppressWarnings("unused")
    public static Teacher_fragment newInstance(int columnCount) {
        Teacher_fragment fragment = new Teacher_fragment();
        Bundle args = new Bundle();
        args.putInt(ARG_COLUMN_COUNT, columnCount);
        fragment.setArguments(args);
        return fragment;
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



        String[] myArray = {"biscuits", "laptops"};


        list.add(new FeedbackProfile("Paul",3, "Good teacher"));
        list.add(new FeedbackProfile("Paul",5, "AMAZING LESSON! learned alot for just 2 hours of study"));

       // ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_expandable_list_item_1, myArray);
     //   if (view instanceof RecyclerView) {
            Context context = view.getContext();
        MyFeedbackRecyclerViewAdapter recycleAdapter = new MyFeedbackRecyclerViewAdapter(getContext(),list);
        feedback.setLayoutManager(new LinearLayoutManager(getActivity()));
        feedback.setAdapter(recycleAdapter);


     //       RecyclerView recyclerView = (RecyclerView) view;
/*            if (mColumnCount <= 1) {
                feedback.setLayoutManager(new LinearLayoutManager(context));
            } else {
                feedback.setLayoutManager(new GridLayoutManager(context, mColumnCount));
            }*/
           // MyFeedbackRecyclerViewAdapter adapter = new MyFeedbackRecyclerViewAdapter(list, mListener);
          //  MyFeedbackRecyclerViewAdapter adapter = new MyFeedbackRecyclerViewAdapter(FeedbackProfile.createFeedback(5),mListener);
           // recyclerView.setAdapter(adapter);
         //   recyclerView.setAdapter(new MyFeedbackRecyclerViewAdapter(list, mListener));
     //   }

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
                ft.replace(R.id.fragmentContent, new RequestFragment());
                ft.addToBackStack(null);
                ft.commit();

                System.out.println("Du trykkede på inbox");
            }
        });
        return view;
    }


    @Override
    public void onClick(View v) {
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
