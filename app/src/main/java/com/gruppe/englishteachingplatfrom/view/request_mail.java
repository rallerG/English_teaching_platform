package com.gruppe.englishteachingplatfrom.view;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.ToggleButton;

import com.gruppe.englishteachingplatfrom.R;
import com.gruppe.englishteachingplatfrom.model.MailProfile;

import java.util.ArrayList;
import java.util.List;

public class request_mail extends AppCompatActivity {

    public static final ArrayList<MailProfile> mail = new ArrayList<MailProfile>();
    TextView name;
    ToggleButton favorite;
    TextView feedContent;
    List<MailProfile> items;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_request_mail);

        name = findViewById(R.id.mailName);
        favorite = findViewById(R.id.mailStar);
        feedContent = findViewById(R.id.mailContent);

        Intent intent = getIntent();
        Bundle intentData = intent.getExtras();

        String studName = (String) intentData.get("studName");
        name.setText(studName);

        boolean studFavorite = (boolean) intentData.get("favorite");
        favorite.setChecked(studFavorite);

        String studContent = (String) intentData.get("content");
        feedContent.setText(studContent);


    }
}
