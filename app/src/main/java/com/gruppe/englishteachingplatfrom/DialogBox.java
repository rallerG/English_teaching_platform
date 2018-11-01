package com.gruppe.englishteachingplatfrom;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
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

        cancelButton = vw.findViewById(R.id.CancelButton);
        sendButton = vw.findViewById(R.id.SendButton);
        teacherImage = vw.findViewById(R.id.TeacherImage);
        teacherInfo = vw.findViewById(R.id.TeacherInfo);
        confirmationText = vw.findViewById(R.id.ConfirmationMessage);
        ratingBar = vw.findViewById(R.id.RatingBar);

        cancelButton.setOnClickListener(this);


        return vw;
    }


    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        Button sendButton = getView().findViewById(R.id.SendButton);
        sendButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                FragmentManager fragmentManager = getFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager
                        .beginTransaction();

                ConfirmationBox fragment2 = new ConfirmationBox();
                fragmentTransaction.replace(R.id.fragmentContent, fragment2);
//provide the fragment ID of your first fragment which you have given in
//fragment_layout_example.xml file in place of first argument
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();

            }
        });

    }

    @Override
    public void onClick(View v) {


            getFragmentManager().beginTransaction().replace(R.id.fragmentContent, new ConfirmationBox()).addToBackStack(null).commit();





    }
}
