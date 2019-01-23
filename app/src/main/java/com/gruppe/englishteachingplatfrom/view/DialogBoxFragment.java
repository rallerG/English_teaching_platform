package com.gruppe.englishteachingplatfrom.view;

import android.graphics.Color;
import android.os.Bundle;
import android.os.SystemClock;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
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
import android.widget.Toast;

import com.gruppe.englishteachingplatfrom.R;
import com.gruppe.englishteachingplatfrom.backend.implementations.StudentFavoritesDocumentImpl;
import com.gruppe.englishteachingplatfrom.backend.implementations.StudentPendingsDocumentImpl;
import com.gruppe.englishteachingplatfrom.backend.implementations.TeacherPendingsDocumentImpl;
import com.gruppe.englishteachingplatfrom.backend.implementations.TeachersDocumentImpl;
import com.gruppe.englishteachingplatfrom.backend.interfaces.CallbackSuccess;
import com.gruppe.englishteachingplatfrom.backend.interfaces.StudentFavoritesDocument;
import com.gruppe.englishteachingplatfrom.backend.interfaces.StudentPendingsDocument;
import com.gruppe.englishteachingplatfrom.backend.interfaces.TeacherPendingsDocument;
import com.gruppe.englishteachingplatfrom.backend.interfaces.TeachersDocument;
import com.gruppe.englishteachingplatfrom.model.Singleton;


public class DialogBoxFragment extends Fragment implements View.OnClickListener {

    Button cancelButton, sendButton;
    ImageView teacherImage;
    TextView teacherInfo, confirmationText, rateD;
    RatingBar ratingBar;
    private int pos;
    private int picture;
    private Singleton p = Singleton.getInstance();
    private String tLang, tName;
    private int tPrice;
    private float tRate;
    private boolean calledByTeacherInfoFragment;
    private boolean fav;
    private String id;
    private String from;
    private long mLastClickTime = 0;

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
            fav = bundle.getBoolean("isFav",false);
            picture = bundle.getInt("pic");
            id = bundle.getString("id","");
            calledByTeacherInfoFragment = bundle.getBoolean("isTeacherInfoFragment");
            from = bundle.getString("from", "");
        }


        teacherInfo.setText(tName+ "\n" + tLang + "\n" + tPrice + " DKK/hr");
        confirmationText.setText("Are you sure you want to send a request to " + tName + "?");




        rateD.setText(Float.toString(tRate));
        ratingBar.setRating(tRate);
        ratingBar.setIsIndicator(true);
        sendButton.setOnClickListener(this);
        cancelButton.setOnClickListener(this);
        teacherImage.setImageResource(picture);



        return vw;
    }

    @Override
    public void onClick(View v) {
        if (SystemClock.elapsedRealtime() - mLastClickTime < 1000) {
            return;
        }
        mLastClickTime = SystemClock.elapsedRealtime();
            if(v == cancelButton){
                getActivity().getSupportFragmentManager().popBackStack();
            }

            if(v == sendButton){

                if(fav) {
                    StudentFavoritesDocument studentFavoritesDocument = new StudentFavoritesDocumentImpl(p.getCurrrentStudent().getId());
                    studentFavoritesDocument.deleteEqualTo(id, true);
                }

                StudentPendingsDocument studentPendingsDocument = new StudentPendingsDocumentImpl(p.getCurrrentStudent().getId());
                studentPendingsDocument.add(id, true, new CallbackSuccess() {
                    @Override
                    public void onCallback() {
                        TeacherPendingsDocument teacherPendingsDocument = new TeacherPendingsDocumentImpl(id);
                        teacherPendingsDocument.add(p.getCurrrentStudent().getId(), false, new CallbackSuccess() {
                            @Override
                            public void onCallback() {
                                Bundle bundle = new Bundle();
                                bundle.putString("name", tName);
                                bundle.putInt("price", tPrice);
                                bundle.putFloat("rate", tRate);
                                bundle.putString("language", tLang);
                                bundle.putInt("position", pos);
                                bundle.putInt("pic", picture);
                                bundle.putBoolean("isTeacherInfoFragment", calledByTeacherInfoFragment);
                                ConfirmationBoxFragment fragment2 = new ConfirmationBoxFragment();
                                fragment2.setArguments(bundle);
                                if(from.equals("swipe")){
                                    FragPager.removeTeacher(FragPager.getFragman());
                                }
                                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                                fragmentManager.popBackStack();
                                FragmentTransaction fragmentTransaction = fragmentManager
                                        .beginTransaction();


                                fragmentTransaction.replace(R.id.fragmentContent, fragment2).addToBackStack(null);
                                fragmentTransaction.commit();
                            }
                        });
                    }
                });
            }





    }
}
