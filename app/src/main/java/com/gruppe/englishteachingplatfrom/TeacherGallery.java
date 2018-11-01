package com.gruppe.englishteachingplatfrom;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.Gallery;
import android.widget.Toast;

public class TeacherGallery extends AppCompatActivity implements OnItemClickListener {

  @Override
  public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);

    String[] lande = {"Danmark", "Norge", "Sverige", "Finland", "Holland", "Italien", "Tyskland",
            "Frankrig", "Spanien", "Portugal", "Nepal", "Indien", "Kina", "Japan", "Thailand"};

    Gallery gallery = new Gallery(this);
    gallery.setOnItemClickListener(this);
    gallery.setSpacing(25); // 25 punkter

    gallery.setAdapter(new ArrayAdapter(this, R.layout.list_element, R.id.textView4, lande));
    setContentView(gallery);

  }

  public void onItemClick(AdapterView<?> l, View v, int position, long id) {
    Toast.makeText(this, "Klik på " + position, Toast.LENGTH_SHORT).show();
  }
}
