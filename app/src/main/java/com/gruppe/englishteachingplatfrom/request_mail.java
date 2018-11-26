package com.gruppe.englishteachingplatfrom;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;

public class request_mail extends AppCompatActivity {

    public static final ArrayList<MailProfile> mail = new ArrayList<MailProfile>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_request_mail);
    }
}
