package com.gruppe.englishteachingplatfrom.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.gruppe.englishteachingplatfrom.R;
import com.gruppe.englishteachingplatfrom.model.Singleton;
import com.gruppe.englishteachingplatfrom.model.TeacherProfile;

import net.cachapa.expandablelayout.ExpandableLayout;

import java.util.ArrayList;


public class Page_frag extends Fragment implements View.OnClickListener {


    private CardView card;
    private TextView name, bio, Language, Rate, Price;
    private ImageView imageView;
    private Singleton teacher = Singleton.getInstance();
    private ArrayList<TeacherProfile> contents = teacher.getTeacherDummies();
    private int pos, pic, tPrice;
    RatingBar Rating;
    float tRating, tRate;
    private String tName, tLang;
    private ExpandableLayout expander;

    public Page_frag() {

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(savedInstanceState != null){
            expander.collapse();
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootview = inflater.inflate(R.layout.fragment_page_frag, container, false);

        Bundle bundle = this.getArguments();
        if (bundle != null) {
            pos = bundle.getInt("position", 0);
        }
        pic = contents.get(pos).getProfilePic();
        card = rootview.findViewById(R.id.card);
        card.setOnClickListener(this);

//        imageView =  rootview.findViewById(R.id.teacherPic);
//        imageView.setImageResource(pic);

        expander = rootview.findViewById(R.id.information);
        bio = rootview.findViewById(R.id.bio);

        name = rootview.findViewById(R.id.name);
        tName = contents.get(pos).getName();
        name.setText(tName);

        Language = rootview.findViewById(R.id.language);
        tLang = contents.get(pos).getLanguage();
        Language.setText(tLang);

 /*       Rating = rootview.findViewById(R.id.teacherRating);
        tRating = (float) contents.get(pos).getRating();
        Rating.setRating(tRating);*/

        Rate = rootview.findViewById(R.id.rate);
        tRate = (float) contents.get(pos).getRating();
        Rate.setText("" + tRate);

        Price = rootview.findViewById(R.id.price);
        tPrice = contents.get(pos).getPrice();
        Price.setText(""+tPrice);

     //   txt.setVisibility(View.INVISIBLE);'
        return rootview;
    }

    public static boolean clicked = false;

    @Override
    public void onClick(View v) {
        if(!clicked)
            if(v == card) {
                //getChildFragmentManager().beginTransaction().replace(R.id.fragmentContent, new InfoCardTest()).addToBackStack(null).commit();
                /*if(expander.isExpanded()) {
                    expander.collapse();
                } else {
                    expander.expand();
                }*/
                Intent i = new Intent(getActivity(), TeacherInfoActivity.class);
                i.putExtra("name", tName);
                i.putExtra("price", tPrice);
                i.putExtra("rate", tRate);
                i.putExtra("language", tLang);
                //remember information and description text (when objects are used)
                startActivity(i);
                clicked = true;
            }
    }
}
