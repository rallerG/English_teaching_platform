package com.gruppe.englishteachingplatfrom.view;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.gruppe.englishteachingplatfrom.R;
import com.gruppe.englishteachingplatfrom.model.SingletonData;


public class ConfirmationBox extends Fragment implements View.OnClickListener {

    private Button okayButton;
    private ImageView teacherImage;
    private TextView teacherInfo, confirmationText;
    private RatingBar ratingBar;
    private int pic;
    private int pos;
    private SingletonData info;

    public ConfirmationBox(){

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View vw = inflater.inflate(R.layout.fragment_confirmation_box, container, false);

        info = SingletonData.getInstance();

        Bundle bundle = this.getArguments();
        if (bundle != null) {
            pos = bundle.getInt("position", 0);
            pic = bundle.getInt("pic", 0);
        }

        teacherImage= vw.findViewById(R.id.TeacherImage2);
        teacherInfo = vw.findViewById(R.id.TeacherInfo2);
        confirmationText = vw.findViewById(R.id.ConfirmationMessage2);
        ratingBar = vw.findViewById(R.id.RatingBar2);

        okayButton = vw.findViewById(R.id.OkayButton);
        teacherImage.setImageResource(pic);
        teacherInfo.setText(info.getTeacher().get(pos).getmName()+ "\nPro. Teacher\n180 DKK/hr");
        okayButton.setOnClickListener(this);

        return vw;
    }


    @Override
    public void onClick(View v) {
        getFragmentManager().beginTransaction().replace(R.id.fragmentContent, new ViewPagerFragment()).addToBackStack(null).commit();

    }
}
