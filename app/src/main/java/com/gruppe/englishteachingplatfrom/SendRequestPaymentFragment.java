package com.gruppe.englishteachingplatfrom;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import gr.escsoft.michaelprimez.searchablespinner.SearchableSpinner;


public class SendRequestPaymentFragment extends Fragment {
    SearchableSpinner ss;
    ListAdapter adapter;
    ArrayList<String> names = new ArrayList<>();

    public SendRequestPaymentFragment() {
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
        View view = inflater.inflate(R.layout.fragment_send_request_payment, container, false);

        ss = view.findViewById(R.id.SearchableSpinner);

        names.add("Kqly1");
        names.add("Kqly2");

        ArrayAdapter adapter = new ArrayAdapter<String>(getActivity(), R.layout.searchable_spinner_layout_item, R.id.nameView, names){
            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
                View view = super.getView(position, convertView, parent);
                TextView nameRow = view.findViewById(R.id.nameView);
                nameRow.setText(names.get(position));
                return view;
            }
        };
        ss.setAdapter(adapter);

        //Add search function?


        return view;
    }

}
