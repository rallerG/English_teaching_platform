package com.gruppe.englishteachingplatfrom.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.gruppe.englishteachingplatfrom.R;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {
    private EditText username, password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        username = (EditText) findViewById(R.id.username);
        password = (EditText) findViewById(R.id.password);

        Button signup = (Button) findViewById(R.id.login);
        signup.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.login :
                if (username.getText().toString().equals("teacher")) {
                    startActivity(new Intent(this, TeacherMainActivity.class));
                    finishAffinity();
                    System.out.println("LoginActivity.java: not done yet");
                }
                else if (username.getText().toString().equals("student")) {
                    startActivity(new Intent(this, MainActivity.class));
                    finishAffinity();
                    System.out.println("LoginActivity.java: not done yet");
                }
                else {
                    wrongUseranmePasswordError();
                }
                break;
        }
    }

    private void wrongUseranmePasswordError() {
        username.setError("Forkert username, for testing skriv teacher eller student");
    }
}
