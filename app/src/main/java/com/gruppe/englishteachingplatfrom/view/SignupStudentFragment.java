package com.gruppe.englishteachingplatfrom.view;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.gruppe.englishteachingplatfrom.R;
import com.gruppe.englishteachingplatfrom.backend.implementations.StudentsDocumentImpl;
import com.gruppe.englishteachingplatfrom.backend.interfaces.CallbackError;
import com.gruppe.englishteachingplatfrom.backend.interfaces.CallbackSuccess;
import com.gruppe.englishteachingplatfrom.backend.interfaces.StudentsDocument;
import com.gruppe.englishteachingplatfrom.model.StudentProfile;

public class SignupStudentFragment extends Fragment implements View.OnClickListener {

    private ProgressDialog pDialog;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.fragment_signup_student, container, false);

        Button signup = (Button) view.findViewById(R.id.signup_button);
        signup.setOnClickListener(this);
        return view;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.signup_button :
                pDialog = new ProgressDialog(getActivity());
                pDialog.setMessage("Please wait...");
                pDialog.setCancelable(true);
                pDialog.show();

                final Activity activity = getActivity();
                final Intent intent = new Intent(getActivity(), LoginActivity.class);
                StudentsDocument studentsDocument = new StudentsDocumentImpl();
                final StudentProfile studentProfile = new StudentProfile();
                studentProfile.setEmail("kqly@gmail.com");
                studentProfile.setName("Kqly");
                studentsDocument.add(studentProfile, new CallbackSuccess() {
                    @Override
                    public void onCallback() {
                        //loading bar
                        if (pDialog.isShowing()){
                            pDialog.dismiss();
                        }
                        activity.finish();
                        startActivity(intent);
                    }

                }, new CallbackError() {
                    @Override
                    public void onCallback() {
                        System.out.println("error adding teacher");
                    }
                });
                break;
        }
    }

}
