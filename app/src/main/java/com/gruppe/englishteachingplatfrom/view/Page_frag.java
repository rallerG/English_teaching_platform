package com.gruppe.englishteachingplatfrom.view;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.gruppe.englishteachingplatfrom.R;


public class Page_frag extends Fragment implements View.OnClickListener {


    CardView card;

    public Page_frag() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootview = inflater.inflate(R.layout.fragment_page_frag, container, false);
        card = rootview.findViewById(R.id.card);
        card.setOnClickListener(this);


        return rootview;
    }


    @Override
    public void onClick(View v) {
        if(v == card) {
            //getChildFragmentManager().beginTransaction().replace(R.id.fragmentContent, new InfoCardTest()).addToBackStack(null).commit();
        }
    }
}
