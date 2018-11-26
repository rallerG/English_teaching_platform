package com.gruppe.englishteachingplatfrom;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.DrawableRes;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.ImageButton;
import android.widget.ToggleButton;

import com.gruppe.englishteachingplatfrom.dummy.DummyContent;
import com.gruppe.englishteachingplatfrom.dummy.DummyContent.DummyItem;

import static com.gruppe.englishteachingplatfrom.request_mail.mail;


public class RequestFragment extends Fragment {

//    SharedPreferences fav = getActivity().getPreferences(Context.MODE_PRIVATE);
    private RecyclerView.LayoutManager layoutManager;
    private static final String ARG_COLUMN_COUNT = "column-count";
    private int mColumnCount = 1;
    private OnListFragmentInteractionListener mListener;
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

        mail.add(new MailProfile("Chang", "I would like you to teach me stuff", true, false));
        mail.add(new MailProfile("Huang", "I would like you to teach me stuff x 2", false,false));
        mail.add(new MailProfile("Zao", "I would like you to teach me stuff", false,false));
        mail.add(new MailProfile("Jin", "I would like you to teach me stuff", false,false));
        mail.add(new MailProfile("Xin", "I would like you to teach me stuff", true,false));

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
      //  toggleStar.setBackgroundResource(R.drawable.ic_toggle_star_color1);

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
