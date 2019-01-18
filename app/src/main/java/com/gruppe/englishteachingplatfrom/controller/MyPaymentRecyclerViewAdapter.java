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
import com.gruppe.englishteachingplatfrom.model.Payment;
import com.gruppe.englishteachingplatfrom.view.PaymentActiveFragment;

import java.util.ArrayList;
import java.util.List;

public class MyPaymentRecyclerViewAdapter extends RecyclerView.Adapter<MyPaymentRecyclerViewAdapter.PaymentViewHolder> {

    private List<Payment> activePaymentList;
    private PaymentActiveFragment.OnFragmentInteractionListener mListener;

    public MyPaymentRecyclerViewAdapter(ArrayList<Payment> paymentDummies, PaymentActiveFragment.OnFragmentInteractionListener listener) {
        this.activePaymentList = paymentDummies;
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
//        Singleton.TeacherDummy teacherProfile = activePaymentList.get(i);

        paymentViewHolder.textViewName.setText(activePaymentList.get(paymentViewHolder.getAdapterPosition()).getTeacher().getName());
        paymentViewHolder.textViewPrice.setText(Integer.toString(activePaymentList.get(paymentViewHolder.getAdapterPosition()).getPrice())+" DKK");
        //TODO Make get date again
        paymentViewHolder.textViewDate.setText(activePaymentList.get(paymentViewHolder.getAdapterPosition()).getRequestDate());
        paymentViewHolder.imageView.setImageResource(R.mipmap.ic_launcher_student_round);

        paymentViewHolder.acceptButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.out.println("MyPaymentRecyclerViewAdapter.java: Knap " + paymentViewHolder.getAdapterPosition());
                Toast.makeText(v.getContext(),"Accept nr. " + paymentViewHolder.getAdapterPosition() + " som hedder: " + activePaymentList.get(paymentViewHolder.getAdapterPosition()).getTeacher().getName(), Toast.LENGTH_SHORT).show();
                Payment.payTransaction(activePaymentList.get(paymentViewHolder.getAdapterPosition()));
                notifyDataSetChanged();
            }
        });

        paymentViewHolder.rejectButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.out.println("MyPaymentRecyclerViewAdapter.java: Knap " + paymentViewHolder.getAdapterPosition());
                Toast.makeText(v.getContext(),"Reject nr. " + paymentViewHolder.getAdapterPosition() + " som hedder: " + activePaymentList.get(paymentViewHolder.getAdapterPosition()).getTeacher().getName(), Toast.LENGTH_SHORT).show();

                Payment.deleteTransaction(activePaymentList.get(paymentViewHolder.getAdapterPosition()));
                notifyDataSetChanged();
            }
        });

    }


    @Override
    public int getItemCount() {
        return activePaymentList.size();
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
