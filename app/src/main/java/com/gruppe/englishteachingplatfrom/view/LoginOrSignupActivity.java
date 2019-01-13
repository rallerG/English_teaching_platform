package com.gruppe.englishteachingplatfrom.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.gruppe.englishteachingplatfrom.R;
import com.gruppe.englishteachingplatfrom.backend.implementations.StudentsDocumentImpl;
import com.gruppe.englishteachingplatfrom.backend.interfaces.StudentsDocument;
import com.gruppe.englishteachingplatfrom.model.StudentProfile;

public class LoginOrSignupActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_login_or_signup);

        //Setting ClickListeninger on the buttons
        Button loginFacebook =  findViewById(R.id.loginFacebook);
        loginFacebook.setOnClickListener(this);
        Button loginWeChat =  findViewById(R.id.loginWeChat);
        loginWeChat.setOnClickListener(this);
        Button login =  findViewById(R.id.login);
        login.setOnClickListener(this);
        Button signup =  findViewById(R.id.signup);
        signup.setOnClickListener(this);

        StudentsDocument studentsDocument = new StudentsDocumentImpl();
        System.out.println(((StudentProfile) studentsDocument.get("1")).getName());

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.loginFacebook :
                startActivity(new Intent(this, MainActivity.class));
                finish();
                System.out.println("Testing");
                break;
            case R.id.loginWeChat :
                startActivity(new Intent(this, TeacherMainActivity.class));
                finish();
                System.out.println("Testing");
                break;
            case R.id.login :
                startActivity(new Intent(this, LoginActivity.class));
                break;
            case R.id.signup :
                startActivity(new Intent(this, WhatAreYouActivity.class));
                break;
        }
    }
}
