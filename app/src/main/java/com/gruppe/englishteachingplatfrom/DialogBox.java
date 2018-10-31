package com.gruppe.englishteachingplatfrom;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;


public class DialogBox extends Fragment implements View.OnClickListener {

    Button cancelButton, sendButton;
    ImageView teacherImage;
    TextView teacherInfo, confirmationText;
    RatingBar ratingBar;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View vw = inflater.inflate(R.layout.dialog_box_fragment, container, false);

        cancelButton = vw.findViewById(R.id.OkayButton);
        sendButton = vw.findViewById(R.id.SendButton);
        teacherImage = vw.findViewById(R.id.TeacherImage2);
        teacherInfo = vw.findViewById(R.id.TeacherInfo2);
        confirmationText = vw.findViewById(R.id.ConfirmationMessage);
        ratingBar = vw.findViewById(R.id.RatingBar);

        cancelButton.setOnClickListener(this);
        sendButton.setOnClickListener(this);


        return vw;
    }


    @Override
    public void onClick(View v) {

        if(v == sendButton){
            getFragmentManager().beginTransaction().replace(R.id.fragment, new ConfirmationBox()).addToBackStack(null).commit();
        }
        else{

        }


    }
}
