package com.gruppe.englishteachingplatfrom.view;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.gruppe.englishteachingplatfrom.R;
import com.gruppe.englishteachingplatfrom.model.SingletonData;
import com.gruppe.englishteachingplatfrom.model.TeacherProfile;

import java.util.ArrayList;


public class Page_frag extends Fragment implements View.OnClickListener {


    private CardView card;
    private TextView txt;
    private ImageView imageView;
    private SingletonData info = SingletonData.getInstance();
    private ArrayList<TeacherProfile> contents = info.getTeacher();
    private int position;


    public Page_frag() {

    }

    public Page_frag(int i) {
        position = i;

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


        txt = rootview.findViewById(R.id.sup_text);
     //   txt.setVisibility(View.INVISIBLE);

        return rootview;
    }


    @Override
    public void onClick(View v) {
        if(v == card) {
            //getChildFragmentManager().beginTransaction().replace(R.id.fragmentContent, new InfoCardTest()).addToBackStack(null).commit();
            if(txt.getVisibility() == View.VISIBLE){
                txt.setVisibility(View.INVISIBLE);
            }else{
                txt.setVisibility(View.VISIBLE);
            }
        }
    }
}
