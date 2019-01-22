package com.gruppe.englishteachingplatfrom.view;

import android.graphics.Color;
import android.os.SystemClock;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.gruppe.englishteachingplatfrom.R;
import com.gruppe.englishteachingplatfrom.backend.implementations.StudentFavoritesDocumentImpl;
import com.gruppe.englishteachingplatfrom.backend.implementations.StudentMatchesDocumentImpl;
import com.gruppe.englishteachingplatfrom.backend.implementations.StudentPendingsDocumentImpl;
import com.gruppe.englishteachingplatfrom.backend.implementations.TeacherMatchesDocumentImpl;
import com.gruppe.englishteachingplatfrom.backend.implementations.TeacherPendingsDocumentImpl;
import com.gruppe.englishteachingplatfrom.backend.interfaces.CallbackSuccess;
import com.gruppe.englishteachingplatfrom.backend.interfaces.StudentFavoritesDocument;
import com.gruppe.englishteachingplatfrom.backend.interfaces.StudentMatchesDocument;
import com.gruppe.englishteachingplatfrom.backend.interfaces.StudentPendingsDocument;
import com.gruppe.englishteachingplatfrom.backend.interfaces.TeacherMatchesDocument;
import com.gruppe.englishteachingplatfrom.backend.interfaces.TeacherPendingsDocument;
import com.gruppe.englishteachingplatfrom.model.Singleton;

public class TeacherInfoFragment extends Fragment implements View.OnClickListener {

    private TextView name, language, rate, price, information;
    private RatingBar rateBar;
    private ImageView image;
    private Singleton p = Singleton.getInstance();
    //    private ArrayList<TeacherProfile> contents = teacher.getTeacherDummies();
    private int pos, tPrice, picture;
    private RatingBar rating;
    private float tRating, tRate;
    private String tName, tLang;
    private boolean fav = false;
    private String id, from;
    private FloatingActionButton floating_Send_teacherInfo, floating_Fav_teacherInfo;
    private long mLastClickTime = 0;

    public TeacherInfoFragment(){

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle bundle = this.getArguments();
        if (bundle != null) {
            tName = bundle.getString("name");
            tPrice = bundle.getInt("price", 0);
            tRate = bundle.getFloat("rate",0);
            tLang = bundle.getString("language");
            pos = bundle.getInt("position", 0);
            id = bundle.getString("id", "");
            picture = bundle.getInt("pic");
            from = bundle.getString("from", "swipe");
        }

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        View rootview = inflater.inflate(R.layout.fragment_teacher_info, container, false);

        name = rootview.findViewById(R.id.info_teachername);
        language = rootview.findViewById(R.id.info_teacherlanguage);
        rate = rootview.findViewById(R.id.info_teacherrating);
        price = rootview.findViewById(R.id.info_teacherprice);
       // information = rootview.findViewById(R.id.information_text);
        rateBar = rootview.findViewById(R.id.info_teacherratingstars);
        image = rootview.findViewById(R.id.info_teacherpicture);

        floating_Fav_teacherInfo = rootview.findViewById(R.id.floating_fav_teacherInfo);
        floating_Send_teacherInfo = rootview.findViewById(R.id.floating_send_teacherInfo);

        if(from.equals("matches")){
            floating_Send_teacherInfo.setImageResource(R.drawable.ic_baseline_feedback_24px);
            floating_Fav_teacherInfo.setImageResource(R.drawable.baseline2x_close_black_18dp);
        }
        if(from.equals("favorites")){
            floating_Fav_teacherInfo.setImageResource(R.drawable.favourite_full);
            fav = true;
        }
        if(from.equals("pending")){
            floating_Send_teacherInfo.hide();
            floating_Fav_teacherInfo.setImageResource(R.drawable.baseline2x_close_black_18dp);
        }

        floating_Send_teacherInfo.setOnClickListener(this);
        floating_Fav_teacherInfo.setOnClickListener(this);

        rating = rootview.findViewById(R.id.info_teacherratingstars);

        System.out.println("TeacherInfoFragment.java: " + tName + " " + tPrice + " " + tRate + " " + tLang);


        language.setText(tLang);
        name.setText(tName);
        rate.setText(Float.toString(tRate));
        price.setText(Integer.toString(tPrice) + " DKK/hr");
        rateBar.setRating(tRate);
        rateBar.setIsIndicator(true);
        image.setImageResource(picture);

//        information.setText(p.getTeacherDummies().get(pos).getDescription());
//        information.setText("I am available every monday and thursday from 15pm to 20 pm UTC+1. I primarily use Skype videochat, but can also use Discord if necessary. I have been tutoring for the last 3 years, and have 1 year left of my masters degree in Business studies.");
        return rootview;
    }

    @Override
    public void onClick(View v) {
        if (SystemClock.elapsedRealtime() - mLastClickTime < 1000) {
            return;
        }
        mLastClickTime = SystemClock.elapsedRealtime();

        if (v == floating_Send_teacherInfo && from.equals("swipe")) {
            PageFragment.clicked = false;
            Bundle bundle = new Bundle();
            bundle.putString("name", tName);
            bundle.putInt("price", tPrice);
            bundle.putFloat("rate", tRate);
            bundle.putString("language", tLang);
            bundle.putInt("position", pos);
            bundle.putBoolean("isTeacherInfoFragment", true);
            bundle.putBoolean("isFav", fav);
            bundle.putInt("pic", picture);
            bundle.putString("id",id);
            Fragment F = new DialogBoxFragment();
            F.setArguments(bundle);
            getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.fragmentContent, F).
                    addToBackStack(null).commit();
        }

        if (v == floating_Send_teacherInfo && from.equals("favorites")) {
            PageFragment.clicked = false;
            Bundle bundle = new Bundle();
            bundle.putString("name", tName);
            bundle.putInt("price", tPrice);
            bundle.putFloat("rate", tRate);
            bundle.putString("language", tLang);
            bundle.putInt("position", pos);
            bundle.putBoolean("isTeacherInfoFragment", true);
            bundle.putBoolean("isFav", fav);
            bundle.putString("from", from);
            bundle.putInt("pic", picture);
            bundle.putString("id",id);
            // remove from favorite when send is clicked
            Fragment F = new DialogBoxFragment();
            F.setArguments(bundle);
            getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.fragmentContent, F).
                    addToBackStack(null).commit();
        }

        if (v == floating_Send_teacherInfo && from.equals("matches")) {
            // Review button
            PageFragment.clicked = false;
            Fragment F = ReviewStudentFragment.newInstance(id,id);
            getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.fragmentContent, F).
                    addToBackStack(null).commit();
        }

        if (v == floating_Fav_teacherInfo && from.equals("swipe")) {
            // checker whether the teacher is favorited by the student and set the image accordingly
            // should have the standard heart images for favorite (empty and filled)
                if(!fav) {
                    ((FloatingActionButton) v).setImageResource(R.drawable.favourite_full);
                    ((FloatingActionButton) v).setBackgroundColor(Color.parseColor("#FF0023"));
                    StudentFavoritesDocument studentFavoritesDocument = new StudentFavoritesDocumentImpl(p.getCurrrentStudent().getId());
                    studentFavoritesDocument.add(id, true, new CallbackSuccess() {
                        @Override
                        public void onCallback() {
                            FragPager.removeTeacher(FragPager.getFragman());
                            getActivity().getSupportFragmentManager().popBackStack();
                        }
                    });
                    Toast.makeText(getContext(),name + " er blevet tilføjet til favoriter",Toast.LENGTH_SHORT).show();
                }
        }

        if (v == floating_Fav_teacherInfo && from.equals("favorites")) {
            // checker whether the teacher is favorited by the student and set the image accordingly
            // should have the standard heart images for favorite (empty and filled)
            if(fav) {
                ((FloatingActionButton) v).setImageResource(R.drawable.favourite_empty);
                ((FloatingActionButton) v).setBackgroundColor(Color.parseColor("#FF0023"));
                //FragPager.removeTeacher(FragPager.getFragman());
                //Toast.makeText(getContext(),name + " er blevet tilføjet til favoriter",Toast.LENGTH_SHORT).show();
                StudentFavoritesDocument studentFavoritesDocument = new StudentFavoritesDocumentImpl(p.getCurrrentStudent().getId());
                studentFavoritesDocument.deleteEqualTo(id, true, new CallbackSuccess() {
                    @Override
                    public void onCallback() {
                        fav = false;
                    }
                });
            }
        }

        if (v == floating_Fav_teacherInfo && from.equals("pending")) {
            // checker whether the teacher is favorited by the student and set the image accordingly
            // should have the standard heart images for favorite (empty and filled)
            StudentPendingsDocument studentPendingsDocument = new StudentPendingsDocumentImpl(p.getCurrrentStudent().getId());
            studentPendingsDocument.deleteEqualTo(id, true, new CallbackSuccess() {
                @Override
                public void onCallback() {
                    Toast.makeText(getContext(),"removed from pending", Toast.LENGTH_SHORT).show();
                    getActivity().getSupportFragmentManager().popBackStack();
                }
            });

            TeacherPendingsDocument teacherPendingsDocument = new TeacherPendingsDocumentImpl(id);
            teacherPendingsDocument.deleteEqualTo(p.getCurrrentStudent().getId(), false);
        }

        if (v == floating_Fav_teacherInfo && from.equals("matches")) {
            StudentMatchesDocument studentMatchesDocument = new StudentMatchesDocumentImpl(p.getCurrrentStudent().getId());
            studentMatchesDocument.deleteEqualTo(id, true, new CallbackSuccess() {
                @Override
                public void onCallback() {

                }
            });

            TeacherMatchesDocument teacherMatchesDocument = new TeacherMatchesDocumentImpl(id);
            teacherMatchesDocument.deleteEqualTo(p.getCurrrentStudent().getId(), false);
        }

    }
}
