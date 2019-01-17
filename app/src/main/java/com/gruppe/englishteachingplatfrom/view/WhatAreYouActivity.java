package com.gruppe.englishteachingplatfrom.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.gruppe.englishteachingplatfrom.R;
import com.gruppe.englishteachingplatfrom.view.SignupActivity;

public class WhatAreYouActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_what_are_you);

        Button teacher = (Button) findViewById(R.id.teacher);
        teacher.setOnClickListener(this);
        Button student = (Button) findViewById(R.id.student);
        student.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.teacher :
                Intent intentTeacher = new Intent(this, SignupActivity.class);
                intentTeacher.putExtra("role","teacher");
                startActivity(intentTeacher);
                break;
            case R.id.student :
                Intent intentStudent = new Intent(this, SignupActivity.class);
                intentStudent.putExtra("role","teacher");
                startActivity(intentStudent);
                break;
        }
    }
}
