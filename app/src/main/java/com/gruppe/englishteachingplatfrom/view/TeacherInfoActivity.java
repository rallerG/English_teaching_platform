package com.gruppe.englishteachingplatfrom.view;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.gruppe.englishteachingplatfrom.R;
import com.gruppe.englishteachingplatfrom.model.Singleton;
import com.gruppe.englishteachingplatfrom.model.TeacherProfile;

import java.util.ArrayList;

public class TeacherInfoActivity extends AppCompatActivity {

    private TextView name, language, rate, price, information, description;
    private ImageView image;
    private Singleton p = Singleton.getInstance();
//    private ArrayList<TeacherProfile> contents = teacher.getTeacherDummies();
    private int pos, pic, tPrice;
    private RatingBar rating;
    float tRating, tRate;
    private String tName, tLang;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teacher_info);

        name = findViewById(R.id.info_teachername);
        language = findViewById(R.id.info_teacherlanguage);
        rate = findViewById(R.id.info_teacherrating);
        price = findViewById(R.id.info_teacherprice);
        information = findViewById(R.id.information_text);
        description = findViewById(R.id.description_text);

        rating = findViewById(R.id.info_teacherratingstars);
        Intent i = getIntent();
        tName = i.getStringExtra("name");
        tPrice = i.getIntExtra("price",0);
        tRate = i.getFloatExtra("rate", 0);
        tLang = i.getStringExtra("language");

        System.out.println("TeacherInfoActivity.java: " + tName + " " + tPrice + " " + tRate + " " + tLang);


        language.setText(tLang);
        name.setText(tName);
        rate.setText(Float.toString(tRate));
        price.setText(Integer.toString(tPrice));


        information.setText("I am available every monday and thursday from 15pm to 20 pm UTC+1. I primarily use Skype videochat, but can also use Discord if necessary. I have been tutoring for the last 3 years, and have 1 year left of my masters degree in Business studies.");
    }

    @Override
    public void onBackPressed() {
        Page_frag.clicked = false;
        ListFragment.clicked = false;
        super.onBackPressed();
    }
}
