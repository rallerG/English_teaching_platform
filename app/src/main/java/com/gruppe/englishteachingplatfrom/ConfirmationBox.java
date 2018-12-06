package com.gruppe.englishteachingplatfrom;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.gruppe.englishteachingplatfrom.Teacher_slider.ViewPagerAdapterController;
import com.gruppe.englishteachingplatfrom.Teacher_slider.ViewPagerFragmentView;
import com.gruppe.englishteachingplatfrom.Teacher_slider.ViewPagerModel;

import java.util.ArrayList;


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
        teacherInfo.setText(info.getNames().get(pos)+ "\nPro. Teacher\n180 DKK/hr");
        okayButton.setOnClickListener(this);

        return vw;
    }


    @Override
    public void onClick(View v) {
        getFragmentManager().beginTransaction().replace(R.id.fragmentContent, new ViewPagerFragmentView()).addToBackStack(null).commit();

    }

    public static class clickCardFragment extends Fragment {

        private ViewPager mViewPager;

        private ViewPagerAdapterController mAdapter;

        private ArrayList<ViewPagerModel> mContents;

        @Override
        public void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);

        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View view = inflater.inflate(R.layout.fragment_viewpager_list, container, false);

            mViewPager = (ViewPager) view.findViewById(R.id.ViewPager);

            mContents = new ArrayList<>();

            int images[] = {R.drawable.profile1, R.drawable.profile2, R.drawable.profile3 };

            String names[] = {"Smith", "Johnson", "David", "Adam"};

            String desig[] = {"English Teacher"};

            String place[] = {"USA", "DENMARK", "SWEDEN"};

            for (int i = 0; i < images.length; i++){

                ViewPagerModel viewPagerModel = new ViewPagerModel();

                viewPagerModel.images = images[i];

                viewPagerModel.names = names[i];

                viewPagerModel.desig = desig[0];

                viewPagerModel.place = place[i];

                mContents.add(viewPagerModel);

            }

            mAdapter = new ViewPagerAdapterController(mContents, getActivity());
            mViewPager.setOffscreenPageLimit(3);

            mViewPager.setAdapter(mAdapter);

            return view;
        }
    }
}
