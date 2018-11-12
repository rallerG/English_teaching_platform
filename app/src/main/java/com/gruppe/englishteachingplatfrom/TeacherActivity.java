package com.gruppe.englishteachingplatfrom;

import android.app.Activity;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.constraint.ConstraintLayout;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.os.Bundle;
import android.view.View;
import android.view.animation.TranslateAnimation;
import android.support.v4.app.FragmentManager;
import android.widget.TableLayout;
import android.widget.Toast;

public class TeacherActivity extends AppCompatActivity implements View.OnClickListener {

    TableLayout inbox;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teacher);

        inbox = findViewById(R.id.inbox);
        inbox.setOnClickListener(this);

        /*
        TranslateAnimation animation = new TranslateAnimation(0.0f, 0.0f, -800.0f, 0.0f); // new TranslateAnimation (float fromXDelta,float toXDelta, float fromYDelta, float toYDelta)

        animation.setDuration(1400); // animation duration, change accordingly
        animation.setRepeatCount(0); // animation repeat count
        animation.setFillAfter(false);

     //   FragmentManager fragmentManager = getSupportFragmentManager();

        FragmentManager fragmentManager = currentActivity.getFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();


        fragmentTransaction.setCustomAnimations(R.anim.slide_down_in,R.anim.slide_up_out);

        VoicePopUpActivity fragment = new VoicePopUpActivity();

        fragmentTransaction.add(R.id.teacher_layout,fragment);
        fragmentTransaction.commit();
*/
    }


    @Override
    public void onClick(View v) {
        if(v == inbox){
            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            ft.add(R.id.teacher_layout, new RequestFragment());
            ft.addToBackStack(null);
            ft.commit();

            System.out.println("Du trykkede på inbox");
            Toast.makeText(TeacherActivity.this,
                    "You will be directed to your inbox", Toast.LENGTH_LONG).show();




        }
    }
}
