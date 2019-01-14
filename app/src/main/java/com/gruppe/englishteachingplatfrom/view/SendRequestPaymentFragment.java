package com.gruppe.englishteachingplatfrom.view;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.ebanx.swipebtn.OnActiveListener;
import com.ebanx.swipebtn.OnStateChangeListener;
import com.ebanx.swipebtn.SwipeButton;
import com.gruppe.englishteachingplatfrom.R;
import com.gruppe.englishteachingplatfrom.model.Payment;
import com.gruppe.englishteachingplatfrom.model.Singleton;
import com.gruppe.englishteachingplatfrom.model.StudentProfile;
import com.gruppe.englishteachingplatfrom.model.TeacherProfile;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import gr.escsoft.michaelprimez.searchablespinner.SearchableSpinner;


public class SendRequestPaymentFragment extends Fragment {
    Singleton p = Singleton.getInstance();

    Spinner ss;
    SwipeButton enableButton;
    EditText inputPrice;

    ListAdapter adapter;
    ArrayList<String> names = new ArrayList<>();
    StudentProfile chosenStudent;
    TeacherProfile currentTeacher;
    int chosenPrice;

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
        inputPrice = view.findViewById(R.id.AmountEditText);
        enableButton = (SwipeButton) view.findViewById(R.id.swipe_btn);

        //TODO Fix teacher id

//        p.createList();
        currentTeacher = p.getTeacherDummies().get(0);
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
                if (inputPrice.getText().toString().isEmpty() || Integer.parseInt(inputPrice.getText().toString()) < 1) {
                    Toast.makeText(getContext(), "Please enter a positive price", Toast.LENGTH_LONG).show();
                } else {
                    //TODO take chosen student and create payment
                    //Toast.makeText(getContext(), "State: " + active, Toast.LENGTH_SHORT).show();
                    chosenPrice = Integer.parseInt(inputPrice.getText().toString());
                    Toast.makeText(getContext(), "Student: " + chosenStudent.getName() + " \nPrice: " + chosenPrice, Toast.LENGTH_LONG).show();

                    DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
                    Date date = new Date();
                    String theRequestDate = (dateFormat.format(date)).toString();
                    //TODO fix teacher index
//                    currentTeacher.getActivePaymentDummies().add(Payment.newTransaction(chosenStudent, currentTeacher, chosenPrice));
//                    chosenStudent.getActivePaymentDummies().add(Payment.newTransaction(chosenStudent, currentTeacher, chosenPrice));
                    Payment.newTransaction(currentTeacher.getId(),chosenStudent, currentTeacher, chosenPrice);

                    //TODO Start intent to teacher payment overview

                }
            }
        });



        return view;
    }

}
