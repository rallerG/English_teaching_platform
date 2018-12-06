package com.gruppe.englishteachingplatfrom.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.gruppe.englishteachingplatfrom.R;
import com.gruppe.englishteachingplatfrom.view.LoginActivity;
import com.gruppe.englishteachingplatfrom.view.LoginOrSignupActivity;

public class SignupActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        Button signup = (Button) findViewById(R.id.signup);
        signup.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.signup :
                startActivity(new Intent(this, LoginActivity.class));
                break;
            case R.id.back :
                startActivity(new Intent(this, LoginOrSignupActivity.class));
                break;
        }
    }

}
