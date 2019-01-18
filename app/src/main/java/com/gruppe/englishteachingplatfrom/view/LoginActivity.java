package com.gruppe.englishteachingplatfrom.view;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

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
    private EditText username, password;
    private Singleton p = Singleton.getInstance();
    private ProgressDialog pDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        username = (EditText) findViewById(R.id.username);
        password = (EditText) findViewById(R.id.password);

        Button signup = (Button) findViewById(R.id.login);
        signup.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.login :
                pDialog = new ProgressDialog(this);
                pDialog.setMessage("Logging in...");
                pDialog.setCancelable(true);
                pDialog.show();

                final Intent intentTeacher = new Intent(this, TeacherMainActivity.class);
                final Intent intentStudent = new Intent(this, MainActivity.class);
                TeachersDocument teachersDocument = new TeachersDocumentImpl();
                teachersDocument.getAll(new CallbackList<TeacherProfile>() {
                    @Override
                    public void onCallback(List<TeacherProfile> listOfObjects) {
                        for (TeacherProfile teacherProfile : listOfObjects) {
                            if (teacherProfile.getEmail().equals(username.getText().toString())) {
                                if (teacherProfile.getPassword().equals(password.getText().toString())) {
                                    p.setCurrrentTeacher(teacherProfile);

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
                                            if (studentProfile.getEmail().equals(username.getText().toString())) {
                                                if (studentProfile.getPassword().equals(password.getText().toString())) {
                                                    p.setCurrrentStudent(studentProfile);

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
        }
    }

    private void wrongUseranmePasswordError() {
        username.setError("Username and/or password is incorrect");
        password.setError("Username and/or password is incorrect");
    }
}
