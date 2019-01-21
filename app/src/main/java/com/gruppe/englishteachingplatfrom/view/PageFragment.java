package com.gruppe.englishteachingplatfrom.view;

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

import java.util.ArrayList;


public class PageFragment extends Fragment implements View.OnClickListener {


    private CardView card;
    private TextView name, language, Rate, Price;
    private ImageView imageView;
    private Singleton teacher = Singleton.getInstance();
    private RatingBar rating;
    public static boolean clicked = false;
    private int pos, pic, tPrice;
    private float  tRate;
    private String tName;
    private String tLang;
    private String id;

    public PageFragment() {

    }

    public static PageFragment newInstance(int num) {
        Bundle bundle = new Bundle();
        bundle.putInt("position", num);
        PageFragment page = new PageFragment();
        page.setArguments(bundle);
        return page;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            pos = getArguments().getInt("position", 0);
        }
        System.out.println("fragment " + pos + " created");
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootview = inflater.inflate(R.layout.fragment_page_frag, container, false);

//        Bundle bundle = this.getArguments();
//        if (bundle != null) {
//            pos = bundle.getInt("position", 0);
//        }
        Singleton teacher = Singleton.getInstance();
        ArrayList<TeacherProfile> contents = teacher.getTeacherDummies();
//        pic = contents.get(pos).getProfilePic();
        card = rootview.findViewById(R.id.card);
        card.setOnClickListener(this);

        id = contents.get(pos).getId();

//        imageView =  rootview.findViewById(R.id.teacherPic);
//        imageView.setImageResource(pic);

        name = rootview.findViewById(R.id.name);
        tName = contents.get(pos).getName();
        name.setText(tName);

        language = rootview.findViewById(R.id.language);
        tLang = contents.get(pos).getLanguage();
        language.setText(tLang);

 /*       Rating = rootview.findViewById(R.id.teacherRating);
        tRating = (float) contents.get(pos).getRating();
        Rating.setRating(tRating);*/

        rating = rootview.findViewById(R.id.teacherRating);
        rating.setRating(tRate);
        Rate = rootview.findViewById(R.id.rate);
        tRate = (float) contents.get(pos).getRating();
        Rate.setText("" + tRate);
        System.out.println("rating for "+ tName+ " " + tRate);

        Price = rootview.findViewById(R.id.price);
        tPrice = contents.get(pos).getPrice();
        Price.setText(""+tPrice + " DKK/hr");
        rating.setRating(tRate);
        rating.setIsIndicator(true);
     //   txt.setVisibility(View.INVISIBLE);
        System.out.println("fragment" + tName + "was created");
        return rootview;
    }

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

                Bundle bundle = new Bundle();
                bundle.putString("name", tName);
                bundle.putInt("price", tPrice);
                bundle.putFloat("rate", tRate);
                bundle.putString("language", tLang);
                bundle.putInt("position", pos);
                bundle.putString("id", id);
                Fragment F = new TeacherInfoFragment();
                F.setArguments(bundle);
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.fragmentContent, F).
                        addToBackStack(null).commit();
//                Intent i = new Intent(getActivity(), TeacherInfoFragment.class);
//                i.putExtra("name", tName);
//                i.putExtra("price", tPrice);
//                i.putExtra("rate", tRate);
//                i.putExtra("language", tLang);
//                i.putExtra("pos", pos);
//                //remember information and description text (when objects are used)
//                startActivity(i);
                clicked = true;
            }
    }
}
