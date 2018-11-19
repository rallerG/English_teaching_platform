package com.gruppe.englishteachingplatfrom;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ScrollView;
import android.widget.TableLayout;
import android.widget.Toast;


public class Teacher_fragment extends Fragment implements View.OnClickListener {

    TableLayout inbox;
    ScrollView scroll;

    public Teacher_fragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.fragment_teacher_fragment, container, false);

        scroll = view.findViewById(R.id.scroll);
        inbox = view.findViewById(R.id.inbox);

        inbox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentTransaction ft = getFragmentManager().beginTransaction();
                ft.replace(R.id.fragmentContent, new RequestFragment());
                ft.addToBackStack(null);
                ft.commit();

                System.out.println("Du trykkede på inbox");
            }
        });
        return view;
    }


    @Override
    public void onClick(View v) {

    }
}
