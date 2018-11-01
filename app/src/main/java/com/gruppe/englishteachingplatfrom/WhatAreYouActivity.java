package com.gruppe.englishteachingplatfrom;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;

public class WhatAreYouActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_whatAreYou);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        Button teacher = (Button) findViewById(R.id.teacher);
        teacher.setOnClickListener(this);
        Button student = (Button) findViewById(R.id.student);
        student.setOnClickListener(this);
        Button institute = (Button) findViewById(R.id.institute);
        institute.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.teacher :
                startActivity(new Intent(this, SignupActivity.class));
                break;
            case R.id.student :
                startActivity(new Intent(this, SignupActivity.class));
                break;
            case R.id.institute :
                //startActivity(new Intent(this, LoginActivity.class));
                System.out.println("not done yet");
                break;
        }
    }
}
