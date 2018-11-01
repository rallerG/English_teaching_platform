package com.gruppe.englishteachingplatfrom;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;


public class VoicePopUpActivity extends Fragment {

    Button upload;
    TextView notNow;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView=inflater.inflate(R.layout.fragment_voice_pop_up,container,false);

        upload = rootView.findViewById(R.id.upload);
        upload.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
               //Go to upload activity
            }
        });

        notNow = rootView.findViewById(R.id.notNow);
        notNow.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                getActivity().onBackPressed();
            }
        });

        return rootView;
    }

}