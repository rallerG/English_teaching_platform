package com.gruppe.englishteachingplatfrom.controller;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.gruppe.englishteachingplatfrom.R;
import com.gruppe.englishteachingplatfrom.model.Singleton;
import com.gruppe.englishteachingplatfrom.model.TeacherProfile;
import com.gruppe.englishteachingplatfrom.view.PaymentActiveFragment;

import java.util.ArrayList;
import java.util.List;

public class MyPaymentRecyclerViewAdapter extends RecyclerView.Adapter<MyPaymentRecyclerViewAdapter.PaymentViewHolder> {

    private List<TeacherProfile> teacherPaymentRequestList;
    private PaymentActiveFragment.OnFragmentInteractionListener mListener;

    public MyPaymentRecyclerViewAdapter(ArrayList<TeacherProfile> teacherDummies, PaymentActiveFragment.OnFragmentInteractionListener listener) {
        this.teacherPaymentRequestList = teacherDummies;
        this.mListener = listener;
    }

    @NonNull
    @Override
    public PaymentViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_payment_request_item, parent, false);
        return new PaymentViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final PaymentViewHolder paymentViewHolder, final int i) {
//        Singleton.TeacherDummy teacherProfile = teacherPaymentRequestList.get(i);

        paymentViewHolder.textViewName.setText(teacherPaymentRequestList.get(i).getName());
        paymentViewHolder.textViewPrice.setText(Integer.toString(teacherPaymentRequestList.get(i).getPrice())+" DKK");
        //TODO Make get date again
        paymentViewHolder.textViewDate.setText(teacherPaymentRequestList.get(i).getId());
        paymentViewHolder.imageView.setImageResource(R.mipmap.ic_launcher_student_round);

        paymentViewHolder.acceptButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.out.println("Knap " + i);
                Toast.makeText(v.getContext(),"Accept nr. " + i, Toast.LENGTH_SHORT).show();
                //TODO Find fix for tranfer between lists.
//                p.addToHistory(teacherPaymentRequestList.get(i));
                teacherPaymentRequestList.remove(i);
                notifyDataSetChanged();
            }
        });

        paymentViewHolder.rejectButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.out.println("Knap " + i);
                Toast.makeText(v.getContext(),"Reject nr. " + i, Toast.LENGTH_SHORT).show();
                teacherPaymentRequestList.remove(i);
                notifyDataSetChanged();
            }
        });

    }


    @Override
    public int getItemCount() {
        return teacherPaymentRequestList.size();
    }

    class PaymentViewHolder extends RecyclerView.ViewHolder {

        ImageView imageView;
        TextView textViewName, textViewPrice, textViewDate;
        Button acceptButton, rejectButton;
        public PaymentViewHolder(@NonNull View itemView) {
            super(itemView);

            imageView = itemView.findViewById(R.id.imageView4);
            textViewName = itemView.findViewById(R.id.teacherName);
            textViewPrice = itemView.findViewById(R.id.amount);
            textViewDate = itemView.findViewById(R.id.date);

            acceptButton = (Button) itemView.findViewById(R.id.accept_btn);
            rejectButton = (Button) itemView.findViewById(R.id.reject_btn);


        }
    }
}
