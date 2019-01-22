package com.gruppe.englishteachingplatfrom.view;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.gruppe.englishteachingplatfrom.R;
import com.gruppe.englishteachingplatfrom.backend.implementations.StudentsDocumentImpl;
import com.gruppe.englishteachingplatfrom.backend.implementations.TeachersDocumentImpl;
import com.gruppe.englishteachingplatfrom.backend.interfaces.CallbackList;
import com.gruppe.englishteachingplatfrom.backend.interfaces.StudentsDocument;
import com.gruppe.englishteachingplatfrom.backend.interfaces.TeachersDocument;
import com.gruppe.englishteachingplatfrom.model.Singleton;
import com.gruppe.englishteachingplatfrom.model.StudentProfile;
import com.gruppe.englishteachingplatfrom.model.TeacherProfile;

import java.util.List;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {
    private EditText email, password;
    private TextView signup;
    private Singleton p = Singleton.getInstance();
    private ProgressDialog pDialog;
    private long mLastClickTime = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        email = (EditText) findViewById(R.id.email);
        password = (EditText) findViewById(R.id.password);
        Button login = (Button) findViewById(R.id.login);
        login.setOnClickListener(this);
        signup = (TextView) findViewById(R.id.signup);
        signup.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if (SystemClock.elapsedRealtime() - mLastClickTime < 1000) {
            return;
        }
        mLastClickTime = SystemClock.elapsedRealtime();
        switch (view.getId()) {
            case R.id.login :
                pDialog = new ProgressDialog(this);
                pDialog.setMessage("Logging in...");
                pDialog.setCancelable(true);
                pDialog.show();

                final Intent intentTeacher = new Intent(this, IntroductionTeacher.class);
                final Intent intentStudent = new Intent(this, IntroductionStudent.class);
                TeachersDocument teachersDocument = new TeachersDocumentImpl();
                teachersDocument.getAll(new CallbackList<TeacherProfile>() {
                    @Override
                    public void onCallback(List<TeacherProfile> listOfObjects) {
                        for (TeacherProfile teacherProfile : listOfObjects) {
                            if (teacherProfile.getEmail().equals(email.getText().toString())) {
                                if (teacherProfile.getPassword().equals(password.getText().toString())) {
                                    p.setCurrrentTeacher(teacherProfile);
                                    p.rememberTeacher();

                                    if (pDialog.isShowing()){
                                        pDialog.dismiss();
                                    }
                                    startActivity(intentTeacher);
                                    finishAffinity();
                                }
                                else {
                                    if (pDialog.isShowing()){
                                        pDialog.dismiss();
                                    }
                                    wrongUseranmePasswordError();
                                }
                            }
                            else {
                                StudentsDocument studentsDocument = new StudentsDocumentImpl();
                                studentsDocument.getAll(new CallbackList<StudentProfile>() {
                                    @Override
                                    public void onCallback(List<StudentProfile> listOfObjects) {
                                        for (StudentProfile studentProfile : listOfObjects) {
                                            if (studentProfile.getEmail().equals(email.getText().toString())) {
                                                if (studentProfile.getPassword().equals(password.getText().toString())) {
                                                    p.setCurrrentStudent(studentProfile);
                                                    p.rememberStudent();

                                                    if (pDialog.isShowing()){
                                                        pDialog.dismiss();
                                                    }
                                                    startActivity(intentStudent);
                                                    finishAffinity();
                                                }
                                                else {
                                                    if (pDialog.isShowing()){
                                                        pDialog.dismiss();
                                                    }
                                                    wrongUseranmePasswordError();
                                                }
                                            }
                                            else {
                                                if (pDialog.isShowing()){
                                                    pDialog.dismiss();
                                                }
                                                wrongUseranmePasswordError();
                                            }
                                        }
                                    }
                                });
                            }
                        }
                    }
                });
                break;
            case R.id.signup :
                startActivity(new Intent(this, SignupActivity.class));
                break;
        }
    }

    private void wrongUseranmePasswordError() {
        email.setError("Username and/or password is incorrect");
        password.setError("Username and/or password is incorrect");
    }
}
