package com.gruppe.englishteachingplatfrom.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.gruppe.englishteachingplatfrom.R;
import com.gruppe.englishteachingplatfrom.backend.implementations.StudentsDocumentImpl;
import com.gruppe.englishteachingplatfrom.backend.implementations.TeacherFeedbackDocumentImpl;
import com.gruppe.englishteachingplatfrom.backend.implementations.TeachersDocumentImpl;
import com.gruppe.englishteachingplatfrom.backend.interfaces.Callback;
import com.gruppe.englishteachingplatfrom.backend.interfaces.CallbackList;
import com.gruppe.englishteachingplatfrom.backend.interfaces.StudentsDocument;
import com.gruppe.englishteachingplatfrom.backend.interfaces.TeacherFeedbackDocument;
import com.gruppe.englishteachingplatfrom.backend.interfaces.TeachersDocument;
import com.gruppe.englishteachingplatfrom.model.DocumentObject;
import com.gruppe.englishteachingplatfrom.model.FeedbackProfile;
import com.gruppe.englishteachingplatfrom.model.Singleton;
import com.gruppe.englishteachingplatfrom.model.StudentProfile;
import com.gruppe.englishteachingplatfrom.model.TeacherProfile;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class LoginOrSignupActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Singleton.downloadAllTeachersProfiles();
        Singleton.downloadAllStudentProfiles();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_login_or_signup);

        //Setting ClickListeninger on the buttons
        final Button loginFacebook =  findViewById(R.id.loginFacebook);
        loginFacebook.setOnClickListener(this);
        Button loginWeChat =  findViewById(R.id.loginWeChat);
        loginWeChat.setOnClickListener(this);
        Button login =  findViewById(R.id.login);
        login.setOnClickListener(this);
        Button signup =  findViewById(R.id.signup);
        signup.setOnClickListener(this);

        TeacherFeedbackDocument teacherFeedbackDocument = new TeacherFeedbackDocumentImpl("1");
        teacherFeedbackDocument.get("1", new Callback<FeedbackProfile>() {
            @Override
            public void onCallback(FeedbackProfile object) {
            }
        });
    }
    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.loginFacebook :
                final Intent intent1 = new Intent(this, MainActivity.class);
                //loading bar
                startActivity(intent1);
                finish();
                System.out.println("Testing");
                break;
            case R.id.loginWeChat :
                final Intent intent2 = new Intent(this, TeacherMainActivity.class);
                //loading bar
                startActivity(intent2);
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
