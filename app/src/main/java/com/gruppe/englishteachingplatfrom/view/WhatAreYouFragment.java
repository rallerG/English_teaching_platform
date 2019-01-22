package com.gruppe.englishteachingplatfrom.view;

import android.os.Bundle;
import android.os.SystemClock;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.gruppe.englishteachingplatfrom.R;

public class WhatAreYouFragment extends Fragment implements View.OnClickListener {

    private long mLastClickTime = 0;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.fragment_what_are_you, container, false);

        Button teacher = (Button) view.findViewById(R.id.teacher);
        teacher.setOnClickListener(this);
        Button student = (Button) view.findViewById(R.id.student);
        student.setOnClickListener(this);
        return view;
    }

    @Override
    public void onClick(View view) {
        if (SystemClock.elapsedRealtime() - mLastClickTime < 1000) {
            return;
        }
        mLastClickTime = SystemClock.elapsedRealtime();

        switch (view.getId()) {
            case R.id.teacher :
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.fragment_content, new SignupTeacherFragment()).addToBackStack(null).commit();
                break;
            case R.id.student :
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.fragment_content, new SignupStudentFragment()).addToBackStack(null).commit();
                break;
        }
    }
}
