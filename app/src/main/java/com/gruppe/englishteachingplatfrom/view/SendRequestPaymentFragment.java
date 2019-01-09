package com.gruppe.englishteachingplatfrom.view;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.ebanx.swipebtn.OnActiveListener;
import com.ebanx.swipebtn.OnStateChangeListener;
import com.ebanx.swipebtn.SwipeButton;
import com.gruppe.englishteachingplatfrom.R;
import com.gruppe.englishteachingplatfrom.model.Singleton;
import com.gruppe.englishteachingplatfrom.model.StudentProfile;

import java.util.ArrayList;

import gr.escsoft.michaelprimez.searchablespinner.SearchableSpinner;


public class SendRequestPaymentFragment extends Fragment {
    Singleton p = Singleton.getInstance();
    Spinner ss;
    ListAdapter adapter;
    ArrayList<String> names = new ArrayList<>();
    SwipeButton enableButton;
    StudentProfile chosenStudent;

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

        ss = view.findViewById(R.id.spinner);
        enableButton = (SwipeButton) view.findViewById(R.id.swipe_btn);

        p.createList();
        names.clear();
        for (StudentProfile student : p.getTeacherDummies().get(0).getMatchProfiles()) {
            names.add(student.getName());
        }
//        names.add("Peter");
//        names.add("Hans");
//        names.add("Jens");
//        names.add("Christian");
//        names.add("Simon");
//        names.add("Lars");
//        names.add("Patrick");
        ArrayAdapter adapter = new ArrayAdapter<String>(getActivity(), R.layout.searchable_spinner_layout_item, R.id.nameView, names){
            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
                View view = super.getView(position, convertView, parent);
                TextView nameRow = view.findViewById(R.id.nameView);
                nameRow.setText(names.get(position));
                chosenStudent = p.getTeacherDummies().get(0).getMatchProfiles().get(position);
                return view;
            }
        };
        ss.setAdapter(adapter);
        //Add search function?




        enableButton.setOnStateChangeListener(new OnStateChangeListener() {
            @Override
            public void onStateChange(boolean active) {
                //TODO take chosen student and create payment
                //Toast.makeText(getContext(), "State: " + active, Toast.LENGTH_SHORT).show();
                Toast.makeText(getContext(), "Student: " + chosenStudent.getName(), Toast.LENGTH_SHORT).show();

            }
        });



        return view;
    }

}
