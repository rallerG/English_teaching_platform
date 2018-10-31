package com.gruppe.englishteachingplatfrom;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;


public class ConfirmationBox extends Fragment implements View.OnClickListener {

    Button okayButton;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View vw = inflater.inflate(R.layout.fragment_confirmation_box, container, false);
        okayButton = vw.findViewById(R.id.OkayButton);
        okayButton.setOnClickListener(this);

        return vw;
    }


    @Override
    public void onClick(View v) {

    }
}
