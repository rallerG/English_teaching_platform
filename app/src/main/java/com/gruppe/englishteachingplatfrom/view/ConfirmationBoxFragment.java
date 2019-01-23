package com.gruppe.englishteachingplatfrom.view;

import android.os.Bundle;
import android.os.SystemClock;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.gruppe.englishteachingplatfrom.R;
import com.gruppe.englishteachingplatfrom.model.Singleton;


public class ConfirmationBoxFragment extends Fragment implements View.OnClickListener {

    private Button okayButton;
    private ImageView teacherImage;
    private TextView teacherInfo, confirmationText,rateD;
    private RatingBar ratingBar;
    private int picture;
    private int pos;
    private Singleton info;
    private String tLang, tName;
    private int tPrice;
    private float tRate;
    private boolean calledByTeacherInfoFragment;
    private long mLastClickTime = 0;

    public ConfirmationBoxFragment(){

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View vw = inflater.inflate(R.layout.fragment_confirmation_box, container, false);

        info = Singleton.getInstance();

        Bundle bundle = this.getArguments();
        if (bundle != null) {
            tName = bundle.getString("name");
            tPrice = bundle.getInt("price", 0);
            tRate = bundle.getFloat("rate",0);
            tLang = bundle.getString("language");
            picture = bundle.getInt("pic");
            pos = bundle.getInt("position", 0);
            calledByTeacherInfoFragment = bundle.getBoolean("isTeacherInfoFragment");
        }

        teacherImage= vw.findViewById(R.id.TeacherImage2);
        teacherInfo = vw.findViewById(R.id.TeacherInfo2);
        confirmationText = vw.findViewById(R.id.ConfirmationMessage2);
        ratingBar = vw.findViewById(R.id.RatingBar2);
        rateD = vw.findViewById(R.id.rateD2);

        okayButton = vw.findViewById(R.id.OkayButton);
        rateD.setText(Float.toString(tRate));
        ratingBar.setRating(tRate);
        ratingBar.setIsIndicator(true);
        teacherInfo.setText(tName+ "\n" + tLang + "\n" + tPrice + " DKK/hr");
        okayButton.setOnClickListener(this);
        teacherImage.setImageResource(picture);

        return vw;
    }


    @Override
    public void onClick(View v) {
        if (SystemClock.elapsedRealtime() - mLastClickTime < 500) {
            return;
        }
        mLastClickTime = SystemClock.elapsedRealtime();
        getActivity().getSupportFragmentManager().popBackStack();
        if (calledByTeacherInfoFragment){
            getActivity().getSupportFragmentManager().popBackStack();
        }

    }
}
