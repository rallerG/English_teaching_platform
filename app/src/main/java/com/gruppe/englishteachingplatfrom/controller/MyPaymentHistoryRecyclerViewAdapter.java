package com.gruppe.englishteachingplatfrom.controller;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.gruppe.englishteachingplatfrom.R;
import com.gruppe.englishteachingplatfrom.model.Payment;
import com.gruppe.englishteachingplatfrom.view.PaymentHistoryFragment;

import java.util.ArrayList;

public class MyPaymentHistoryRecyclerViewAdapter extends RecyclerView.Adapter<MyPaymentHistoryRecyclerViewAdapter.PaymentViewHolder> {

    private ArrayList<Payment> paymentHistoryList;
    //    private List<Singleton.TeacherDummy> paymentHistoryList;
    private PaymentHistoryFragment.OnFragmentInteractionListener mListener;


    public MyPaymentHistoryRecyclerViewAdapter(ArrayList<Payment> paymentHistory, PaymentHistoryFragment.OnFragmentInteractionListener listener) {
        this.paymentHistoryList = paymentHistory;
        this.mListener = listener;
    }

    @NonNull
    @Override
    public PaymentViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_payment_history_item, parent, false);
        return new PaymentViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PaymentViewHolder paymentViewHolder, int i) {
//        Singleton.TeacherDummy teacherProfile = paymentHistoryList.get(i);

        paymentViewHolder.textViewName.setText(paymentHistoryList.get(i).getTeacher().getName());
        paymentViewHolder.textViewPrice.setText(paymentHistoryList.get(i).getPrice() +" DKK");
        paymentViewHolder.textViewDate.setText(paymentHistoryList.get(i).getPaymentDate());
        paymentViewHolder.imageView.setImageResource(R.mipmap.ic_launcher_student_round);
        paymentViewHolder.textViewStatus.setText("Payed");
    }


    @Override
    public int getItemCount() {
        return paymentHistoryList.size();
    }

    class PaymentViewHolder extends RecyclerView.ViewHolder {

        ImageView imageView;
        TextView textViewName, textViewPrice, textViewDate, textViewStatus;
        public PaymentViewHolder(@NonNull View itemView) {
            super(itemView);

            imageView = itemView.findViewById(R.id.imageView4);
            textViewName = itemView.findViewById(R.id.studentName);
            textViewPrice = itemView.findViewById(R.id.amount);
            textViewDate = itemView.findViewById(R.id.date);
            textViewStatus = itemView.findViewById(R.id.statusTextView);
        }
    }
}
