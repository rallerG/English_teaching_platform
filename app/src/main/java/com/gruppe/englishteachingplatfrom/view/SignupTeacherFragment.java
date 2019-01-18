package com.gruppe.englishteachingplatfrom.view;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.gruppe.englishteachingplatfrom.R;
import com.gruppe.englishteachingplatfrom.backend.implementations.TeachersDocumentImpl;
import com.gruppe.englishteachingplatfrom.backend.interfaces.CallbackError;
import com.gruppe.englishteachingplatfrom.backend.interfaces.CallbackSuccess;
import com.gruppe.englishteachingplatfrom.backend.interfaces.TeachersDocument;
import com.gruppe.englishteachingplatfrom.model.TeacherProfile;

public class SignupTeacherFragment extends Fragment implements View.OnClickListener {
    private ProgressDialog pDialog;
    private EditText name, email, price;

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
        price = view.findViewById(R.id.price_input);

        return view;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.signup_button_teacher :
                pDialog = new ProgressDialog(getActivity());
                pDialog.setMessage("Please wait...");
                pDialog.setCancelable(true);
                pDialog.show();

                final Activity activity = getActivity();
                final Intent intent = new Intent(getActivity(), LoginActivity.class);
                TeachersDocument teachersDocument = new TeachersDocumentImpl();
                final TeacherProfile teacherProfile = new TeacherProfile();
                teacherProfile.setName(name.getText().toString());
                teacherProfile.setEmail(email.getText().toString());
                teacherProfile.setPrice(Integer.parseInt(price.getText().toString()));
                teachersDocument.add(teacherProfile, new CallbackSuccess() {
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
                break;
        }
    }

}
