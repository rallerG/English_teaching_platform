package com.gruppe.englishteachingplatfrom;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.gruppe.englishteachingplatfrom.Teacher_slider.ViewPagerFragment;


public class DialogBox extends Fragment implements View.OnClickListener {

    Button cancelButton, sendButton;
    ImageView teacherImage;
    TextView teacherInfo, confirmationText;
    RatingBar ratingBar;
    private int pos;
    private int pic;
    private SingletonData info;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View vw = inflater.inflate(R.layout.dialog_box_fragment, container, false);

        info = SingletonData.getInstance();

        cancelButton = vw.findViewById(R.id.CancelButton);
        sendButton = vw.findViewById(R.id.SendButton);
        teacherImage = vw.findViewById(R.id.TeacherImage);
        teacherInfo = vw.findViewById(R.id.TeacherInfo);
        confirmationText = vw.findViewById(R.id.ConfirmationMessage);
        ratingBar = vw.findViewById(R.id.RatingBar);

        Bundle bundle = this.getArguments();
        if (bundle != null) {
            pos = bundle.getInt("position", 0);
            pic = bundle.getInt("pic", 0);
        }


        teacherInfo.setText(info.getNames().get(pos)+ "\nPro. Teacher\n180 DKK/hr");
        confirmationText.setText("Are you sure you want to send a request to " + info.getNames().get(pos) + "?");
        teacherImage.setImageResource(pic);

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
                bundle.putInt("position", pos);
                bundle.putInt("pic", pic);
                ConfirmationBox fragment2 = new ConfirmationBox();
                fragment2.setArguments(bundle);
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
                getFragmentManager().beginTransaction().replace(R.id.fragmentContent, new ViewPagerFragment()).addToBackStack(null).commit();
            }





    }
}
