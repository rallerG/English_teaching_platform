package com.gruppe.englishteachingplatfrom.view;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.Preference;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.gruppe.englishteachingplatfrom.R;
import com.gruppe.englishteachingplatfrom.backend.implementations.StudentsDocumentImpl;
import com.gruppe.englishteachingplatfrom.backend.implementations.TeachersDocumentImpl;
import com.gruppe.englishteachingplatfrom.backend.interfaces.Callback;
import com.gruppe.englishteachingplatfrom.backend.interfaces.StudentsDocument;
import com.gruppe.englishteachingplatfrom.backend.interfaces.TeachersDocument;
import com.gruppe.englishteachingplatfrom.model.Singleton;
import com.gruppe.englishteachingplatfrom.model.StudentProfile;
import com.gruppe.englishteachingplatfrom.model.TeacherProfile;

public class LoginOrSignupActivity extends AppCompatActivity implements View.OnClickListener {
    Singleton p = Singleton.getInstance();
    private ProgressDialog pDialog;
    private SharedPreferences preference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_or_signup);

        p.setPreferences(PreferenceManager.getDefaultSharedPreferences(getApplication()));
        preference = Singleton.getInstance().getPreferences();

        if (preference.contains("current_teacher_id")) {
            pDialog = new ProgressDialog(this);
            pDialog.setMessage("Please wait...");
            pDialog.setCancelable(true);
            pDialog.show();

            final Intent intent2 = new Intent(this, TeacherMainActivity.class);
            System.out.println("magnus: "+(preference.getString("current_teacher_id","")));
            TeachersDocument teachersDocument = new TeachersDocumentImpl();
            teachersDocument.get((preference.getString("current_teacher_id","")), new Callback<TeacherProfile>() {
                @Override
                public void onCallback(TeacherProfile object) {
                    p.setCurrrentTeacher(object);
                    p.rememberTeacher();
                    //loading bar
                    if (pDialog.isShowing()){
                        pDialog.dismiss();
                    }
                    startActivity(intent2);
                    finish();
                }
            });
        }

        else if (preference.contains("current_student_id")) {
            pDialog = new ProgressDialog(this);
            pDialog.setMessage("Logging in...");
            pDialog.setCancelable(true);
            pDialog.show();

            final Intent intent1 = new Intent(this, MainActivity.class);
            StudentsDocument studentDocument = new StudentsDocumentImpl();
            studentDocument.get((preference.getString("current_student_id","")), new Callback<StudentProfile>() {
                @Override
                public void onCallback(StudentProfile object) {
                    p.setCurrrentStudent(object);
                    p.rememberStudent();
                    //loading bar
                    if (pDialog.isShowing()){
                        pDialog.dismiss();
                    }
                    startActivity(intent1);
                    finish();
                }
            });
        }

        //Setting ClickListeninger on the buttons
        final Button loginFacebook =  findViewById(R.id.loginFacebook);
        loginFacebook.setOnClickListener(this);
        Button loginWeChat =  findViewById(R.id.loginWeChat);
        loginWeChat.setOnClickListener(this);
        Button login =  findViewById(R.id.login);
        login.setOnClickListener(this);
        Button signup =  findViewById(R.id.signup);
        signup.setOnClickListener(this);
}
    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.loginFacebook :
                // Showing progress dialog to user
                pDialog = new ProgressDialog(this);
                pDialog.setMessage("Please wait...");
                pDialog.setCancelable(true);
                pDialog.show();

                final Intent intent1 = new Intent(this, IntroductionStudent.class);
                StudentsDocument studentDocument = new StudentsDocumentImpl();
                studentDocument.get("1", new Callback<StudentProfile>() {
                    @Override
                    public void onCallback(StudentProfile object) {
                        p.setCurrrentStudent(object);
                        p.rememberStudent();
                        //loading bar
                        if (pDialog.isShowing()){
                            pDialog.dismiss();
                        }
                        startActivity(intent1);
                        finish();
                    }
                });
                break;
            case R.id.loginWeChat :
                // Showing progress dialog to user
                pDialog = new ProgressDialog(this);
                pDialog.setMessage("Please wait...");
                pDialog.setCancelable(true);
                pDialog.show();

                final Intent intent2 = new Intent(this, IntroductionTeacher.class);
                TeachersDocument teachersDocument = new TeachersDocumentImpl();
                teachersDocument.get("1", new Callback<TeacherProfile>() {
                    @Override
                    public void onCallback(TeacherProfile object) {
                        p.setCurrrentTeacher(object);
                        p.rememberTeacher();

                        //loading bar
                        if (pDialog.isShowing()){
                            pDialog.dismiss();
                        }
                        startActivity(intent2);
                        finish();
                    }
                });
                break;
            case R.id.login :
                startActivity(new Intent(this, LoginActivity.class));
                break;
            case R.id.signup :
                startActivity(new Intent(this, SignupActivity.class));
                break;
        }
    }
}
