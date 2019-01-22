package com.gruppe.englishteachingplatfrom.view;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.SystemClock;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.gruppe.englishteachingplatfrom.R;
import com.gruppe.englishteachingplatfrom.backend.implementations.StudentsDocumentImpl;
import com.gruppe.englishteachingplatfrom.backend.interfaces.CallbackError;
import com.gruppe.englishteachingplatfrom.backend.interfaces.CallbackList;
import com.gruppe.englishteachingplatfrom.backend.interfaces.CallbackSuccess;
import com.gruppe.englishteachingplatfrom.backend.interfaces.StudentsDocument;
import com.gruppe.englishteachingplatfrom.model.StudentProfile;

import java.util.List;

public class SignupStudentFragment extends Fragment implements View.OnClickListener {

    private ProgressDialog pDialog;
    private EditText name, email, password;
    private long mLastClickTime = 0;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.fragment_signup_student, container, false);

        Button signup = (Button) view.findViewById(R.id.signup_button_student);
        signup.setOnClickListener(this);

        name = view.findViewById(R.id.name_input);
        email = view.findViewById(R.id.email_input);
        password = view.findViewById(R.id.password_input);

        return view;
    }

    @Override
    public void onClick(View view) {
        if (SystemClock.elapsedRealtime() - mLastClickTime < 1000) {
            return;
        }

        mLastClickTime = SystemClock.elapsedRealtime();
        switch (view.getId()) {
            case R.id.signup_button_student:
                if (email.getText().toString().equals("") || password.getText().toString().equals("")|| name.getText().toString().equals(""))
                    emptyFields();
                else {
                    pDialog = new ProgressDialog(getActivity());
                    pDialog.setMessage("Please wait...");
                    pDialog.setCancelable(true);
                    pDialog.show();

                    StudentsDocument studentsDocument = new StudentsDocumentImpl();
                    studentsDocument.getAll(new CallbackList<StudentProfile>() {
                        @Override
                        public void onCallback(List<StudentProfile> listOfObjects) {
                            boolean exits = false;
                            for (StudentProfile studentProfile : listOfObjects) {
                                if (studentProfile.getEmail().equals(email.getText().toString()))
                                    exits = true;
                            }
                            if (exits) {
                                emailAlreadyExits();
                                if (pDialog.isShowing())
                                    pDialog.dismiss();
                            }
                            else {
                                final Activity activity = getActivity();
                                final Intent intent = new Intent(getActivity(), LoginActivity.class);
                                final StudentProfile newStudentProfile = new StudentProfile();
                                newStudentProfile.setName(name.getText().toString());
                                newStudentProfile.setEmail(email.getText().toString());
                                newStudentProfile.setPassword(password.getText().toString());
                                StudentsDocument studentsDocument2 = new StudentsDocumentImpl();
                                studentsDocument2.add(newStudentProfile, new CallbackSuccess() {
                                    @Override
                                    public void onCallback() {
                                        //loading bar
                                        if (pDialog.isShowing())
                                            pDialog.dismiss();
                                        activity.finish();
                                        startActivity(intent);
                                    }

                                }, new CallbackError() {
                                    @Override
                                    public void onCallback() {
                                        System.out.println("error adding teacher");
                                    }
                                });
                            }
                        }
                    });
                }
            break;
        }
    }
    private void emptyFields() {
        email.setError("You need to insert your email to sign up");
        name.setError("You need to insert your name to sign up");
        password.setError("You need to insert your password to sign up");
    }

    private void emailAlreadyExits () {
        email.setError("The inserted Email already exits");
    }

}
