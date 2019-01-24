package com.gruppe.englishteachingplatfrom.view;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.SystemClock;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.gruppe.englishteachingplatfrom.R;
import com.gruppe.englishteachingplatfrom.backend.implementations.TeachersDocumentImpl;
import com.gruppe.englishteachingplatfrom.backend.interfaces.CallbackError;
import com.gruppe.englishteachingplatfrom.backend.interfaces.CallbackList;
import com.gruppe.englishteachingplatfrom.backend.interfaces.CallbackSuccess;
import com.gruppe.englishteachingplatfrom.backend.interfaces.TeachersDocument;
import com.gruppe.englishteachingplatfrom.model.TeacherProfile;

import java.util.List;

public class SignupTeacherFragment extends Fragment implements View.OnClickListener {
    private ProgressDialog pDialog;
    private EditText name, email, price, password;
    private long mLastClickTime = 0;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.fragment_signup_teacher, container, false);

        Button signup = (Button) view.findViewById(R.id.signup_button_teacher);
        signup.setOnClickListener(this);

        name = view.findViewById(R.id.name_input);
        email = view.findViewById(R.id.email_input);
        password = view.findViewById(R.id.password_input);
        price = view.findViewById(R.id.price_input);

        return view;
    }

    @Override
    public void onClick(View view) {
        if (SystemClock.elapsedRealtime() - mLastClickTime < 1000) {
            return;
        }
        mLastClickTime = SystemClock.elapsedRealtime();

        switch (view.getId()) {
            case R.id.signup_button_teacher :
                if (email.getText().toString().equals("") || password.getText().toString().equals("")|| name.getText().toString().equals("") || price.getText().toString().equals(""))
                    emptyFields();
                else {
                    pDialog = new ProgressDialog(getActivity());
                    pDialog.setMessage("Please wait...");
                    pDialog.setCancelable(true);
                    pDialog.show();

                    TeachersDocument teachersDocument = new TeachersDocumentImpl();
                    teachersDocument.getAll(new CallbackList<TeacherProfile>() {
                        @Override
                        public void onCallback(List<TeacherProfile> listOfObjects) {
                            boolean exits = false;
                            for (TeacherProfile teacherProfile: listOfObjects) {
                                if (teacherProfile.getEmail().equals(email.getText().toString()))
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
                                TeachersDocument teachersDocument2 = new TeachersDocumentImpl();
                                final TeacherProfile newTeacherProfile = new TeacherProfile();
                                newTeacherProfile.setName(name.getText().toString());
                                String emailInput = (email.getText().toString()).toLowerCase();
                                newTeacherProfile.setEmail(emailInput);
                                newTeacherProfile.setPassword(password.getText().toString());
                                newTeacherProfile.setPrice(Integer.parseInt(price.getText().toString()));
                                teachersDocument2.add(newTeacherProfile, new CallbackSuccess() {
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
        price.setError("You need to insert price to sign up");
    }

    private void emailAlreadyExits () {
        email.setError("The inserted Email already exits");
    }
}
