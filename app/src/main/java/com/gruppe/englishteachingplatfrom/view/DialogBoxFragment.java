package com.gruppe.englishteachingplatfrom.view;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.gruppe.englishteachingplatfrom.R;
import com.gruppe.englishteachingplatfrom.model.Singleton;


public class DialogBoxFragment extends Fragment implements View.OnClickListener {

    Button cancelButton, sendButton;
    ImageView teacherImage;
    TextView teacherInfo, confirmationText, rateD;
    RatingBar ratingBar;
    private int pos;
    private int pic;
    private Singleton p = Singleton.getInstance();
    private String tLang, tName;
    private int tPrice;
    private float tRate;
    private boolean calledByTeacherInfoFragment;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View vw = inflater.inflate(R.layout.dialog_box_fragment, container, false);



        cancelButton = vw.findViewById(R.id.CancelButton);
        sendButton = vw.findViewById(R.id.SendButton);
        teacherImage = vw.findViewById(R.id.TeacherImage);
        teacherInfo = vw.findViewById(R.id.TeacherInfo);
        confirmationText = vw.findViewById(R.id.ConfirmationMessage);
        rateD = vw.findViewById(R.id.rateD);
        ratingBar = vw.findViewById(R.id.RatingBar);

        Bundle bundle = this.getArguments();
        if (bundle != null) {
            tName = bundle.getString("name");
            tPrice = bundle.getInt("price", 0);
            tRate = bundle.getFloat("rate",0);
            tLang = bundle.getString("language");
            pos = bundle.getInt("position", 0);
            calledByTeacherInfoFragment = bundle.getBoolean("isTeacherInfoFragment");
        }


        teacherInfo.setText(tName+ "\n" + tLang + "\n" + tPrice + " DKK/hr");
        confirmationText.setText("Are you sure you want to send a request to " + tName + "?");




        rateD.setText(Float.toString(tRate));
        ratingBar.setRating(tRate);
        ratingBar.setIsIndicator(true);

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
                Bundle bundle = new Bundle();
                bundle.putString("name", tName);
                bundle.putInt("price", tPrice);
                bundle.putFloat("rate", tRate);
                bundle.putString("language", tLang);
                bundle.putInt("position", pos);
                bundle.putBoolean("isTeacherInfoFragment", calledByTeacherInfoFragment);
                ConfirmationBoxFragment fragment2 = new ConfirmationBoxFragment();
                fragment2.setArguments(bundle);
                getActivity().getSupportFragmentManager().popBackStack();
                FragmentManager fragmentManager = getFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager
                        .beginTransaction();


                fragmentTransaction.replace(R.id.fragmentContent, fragment2).addToBackStack(null);
                fragmentTransaction.commit();

            }
        });

    }

    @Override
    public void onClick(View v) {

            if(v == cancelButton){
                getActivity().getSupportFragmentManager().popBackStack();
            }





    }
}
