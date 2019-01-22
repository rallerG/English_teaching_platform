package com.gruppe.englishteachingplatfrom.view;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.gruppe.englishteachingplatfrom.R;

public class SignupActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        getSupportFragmentManager().beginTransaction().add(R.id.fragment_content, new WhatAreYouFragment()).commit();
    }
}
