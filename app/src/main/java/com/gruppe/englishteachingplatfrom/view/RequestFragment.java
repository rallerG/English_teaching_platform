package com.gruppe.englishteachingplatfrom.view;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ToggleButton;

import com.gruppe.englishteachingplatfrom.R;
import com.gruppe.englishteachingplatfrom.controller.MyRequestRecyclerViewAdapter;
import com.gruppe.englishteachingplatfrom.dummy.DummyContent.DummyItem;
import com.gruppe.englishteachingplatfrom.model.MailProfile;

import static com.gruppe.englishteachingplatfrom.view.request_mail.mail;


public class RequestFragment extends Fragment {

    private RecyclerView.LayoutManager layoutManager;
    private static final String ARG_COLUMN_COUNT = "column-count";
    private int mColumnCount = 1;
    private RecyclerView recycler;

    ToggleButton toggleStar;

    public RequestFragment() {
    }

    public static RequestFragment newInstance(int columnCount) {
        RequestFragment fragment = new RequestFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_COLUMN_COUNT, columnCount);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mail.add(new MailProfile("Chang", "Hello, I would like you to teach me English. Can we schedule time and date?", false, false));
        mail.add(new MailProfile("Huang", "Hello, I would like you to teach me English. Can we schedule time and date?", false, false));
        mail.add(new MailProfile("Zao", "Hello, I would like you to teach me English. Can we schedule time and date?", false, false));
        mail.add(new MailProfile("Jin", "Hello, I would like you to teach me English. Can we schedule time and date?", false, false));
        mail.add(new MailProfile("Xin", "Hello, I would like you to teach me English. Can we schedule time and date?", false, false));

        if (getArguments() != null) {
            mColumnCount = getArguments().getInt(ARG_COLUMN_COUNT);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_request_list, container, false);
        View view1 = inflater.inflate(R.layout.fragment_request, container, false);
        toggleStar = view1.findViewById(R.id.toggleStar);
        recycler = view.findViewById(R.id.list);

        layoutManager = new LinearLayoutManager(getContext());
        recycler.setLayoutManager(layoutManager);

        // Set the adapter
        if (view instanceof RecyclerView) {
            Context context = view.getContext();
            RecyclerView recyclerView = (RecyclerView) view;
            if (mColumnCount <= 1) {
                recyclerView.setLayoutManager(new LinearLayoutManager(context));
            } else {
                recyclerView.setLayoutManager(new GridLayoutManager(context, mColumnCount));
            }
            recyclerView.setAdapter(new MyRequestRecyclerViewAdapter(getContext(), mail));
        }
        return view;
    }


    public interface OnListFragmentInteractionListener {
        // TODO: Update argument type and name
        void onListFragmentInteraction(DummyItem item);
    }
}
