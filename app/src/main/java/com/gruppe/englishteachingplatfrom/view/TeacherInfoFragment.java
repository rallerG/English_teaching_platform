package com.gruppe.englishteachingplatfrom.view;

import android.graphics.Color;
import android.os.SystemClock;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v7.widget.CardView;
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
import java.math.BigDecimal;
public class TeacherInfoFragment extends Fragment implements View.OnClickListener {

    private TextView name, language, rate, price, information;
    private RatingBar rateBar;
    private ImageView image;
    private Singleton p = Singleton.getInstance();
    //    private ArrayList<TeacherProfile> contents = teacher.getTeacherDummies();
    private int pos, tPrice, picture;
    private CardView card;
    private float tRate;
    private String tName, tLang, tInformation;
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
            tInformation = bundle.getString("information", "No information provided");
        }

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        View rootview = inflater.inflate(R.layout.fragment_teacher_info, container, false);

        card = rootview.findViewById(R.id.info_card);
        name = rootview.findViewById(R.id.info_teachername);
        language = rootview.findViewById(R.id.info_teacherlanguage);
        rate = rootview.findViewById(R.id.info_teacherrating);
        price = rootview.findViewById(R.id.info_teacherprice);
        information = rootview.findViewById(R.id.information_text);
        rateBar = rootview.findViewById(R.id.info_teacherratingstars);
        image = rootview.findViewById(R.id.info_teacherpicture);

        card.setOnClickListener(this);
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

        System.out.println("TeacherInfoFragment.java: " + tName + " " + tPrice + " " + tRate + " " + tLang);


        language.setText(tLang);
        name.setText(tName);
        rate.setText(Float.toString(tRate));
        price.setText(Integer.toString(tPrice) + " DKK/hr");
        rateBar.setRating(round(tRate,2));
        rateBar.setIsIndicator(true);
        image.setImageResource(picture);
        information.setText(tInformation);

        return rootview;
    }

    @Override
    public void onClick(View v) {
        if (SystemClock.elapsedRealtime() - mLastClickTime < 500) {
            return;
        }
        mLastClickTime = SystemClock.elapsedRealtime();
        System.out.println("teacherinfo clicked");
        if(v == card){
            PageFragment.clicked = false;
            ListFragment.clicked = false;
            getActivity().getSupportFragmentManager().popBackStack();
        }

        if (v == floating_Send_teacherInfo && from.equals("swipe")) {
            PageFragment.clicked = false;
            Bundle bundle = new Bundle();
            bundle.putString("name", tName);
            bundle.putInt("price", tPrice);
            bundle.putFloat("rate", tRate);
            bundle.putString("language", tLang);
            bundle.putString("information",tInformation);
            bundle.putInt("position", pos);
            bundle.putBoolean("isTeacherInfoFragment", true);
            bundle.putBoolean("isFav", fav);
            bundle.putInt("pic", picture);
            bundle.putString("id",id);
            bundle.putString("from", from);
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
            bundle.putString("information",tInformation);
            bundle.putInt("position", pos);
            bundle.putBoolean("isTeacherInfoFragment", true);
            bundle.putBoolean("isFav", fav);
            bundle.putString("from", from);
            bundle.putInt("pic", picture);
            bundle.putString("id",id);
            bundle.putString("from", from);
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
                            Toast.makeText(getContext(),name + " er blevet tilføjet til favoriter",Toast.LENGTH_SHORT).show();
                        }
                    });
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
                        FragPager.addTeacher(FragPager.getFragman(),pos);
                        p.getCurrrentStudent().getFavoriteProfiles().remove(pos);
                        Toast.makeText(getContext(), "fjernet " + name + " fra dine favoriter", Toast.LENGTH_SHORT).show();
                        getActivity().getSupportFragmentManager().popBackStack();
                    }
                });
            }
        }

        if (v == floating_Fav_teacherInfo && from.equals("pending")) {
           // remove button
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
            //remove button
            StudentMatchesDocument studentMatchesDocument = new StudentMatchesDocumentImpl(p.getCurrrentStudent().getId());
            studentMatchesDocument.deleteEqualTo(id, true, new CallbackSuccess() {
                @Override
                public void onCallback() {
                    Toast.makeText(getContext(),"removed from matches", Toast.LENGTH_SHORT).show();
                    getActivity().getSupportFragmentManager().popBackStack();
                }
            });

            TeacherMatchesDocument teacherMatchesDocument = new TeacherMatchesDocumentImpl(id);
            teacherMatchesDocument.deleteEqualTo(p.getCurrrentStudent().getId(), false);
        }

    }
    public static float round(float number, int decimalPlace) {
        BigDecimal bd = new BigDecimal(number);
        bd = bd.setScale(decimalPlace, BigDecimal.ROUND_HALF_UP);
        return bd.floatValue();
    }
}
