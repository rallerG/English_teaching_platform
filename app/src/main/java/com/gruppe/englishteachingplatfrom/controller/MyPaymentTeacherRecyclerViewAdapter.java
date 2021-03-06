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
import com.gruppe.englishteachingplatfrom.view.PaymentActiveFragment;

import java.util.ArrayList;
import java.util.List;

public class MyPaymentTeacherRecyclerViewAdapter extends RecyclerView.Adapter<MyPaymentTeacherRecyclerViewAdapter.PaymentViewHolder> {

    private List<Payment> activePaymentList;
    private PaymentActiveFragment.OnFragmentInteractionListener mListener;

    public MyPaymentTeacherRecyclerViewAdapter(ArrayList<Payment> paymentDummies, PaymentActiveFragment.OnFragmentInteractionListener listener) {
        this.activePaymentList = paymentDummies;
        this.mListener = listener;
    }

    @NonNull
    @Override
    public PaymentViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_payment_request_teacher_item, parent, false);
        return new PaymentViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final PaymentViewHolder paymentViewHolder, final int i) {

        paymentViewHolder.textViewName.setText(activePaymentList.get(paymentViewHolder.getAdapterPosition()).getStudent().getName());
        paymentViewHolder.textViewPrice.setText(Integer.toString(activePaymentList.get(paymentViewHolder.getAdapterPosition()).getPrice()) + " DKK");
        //TODO Make get date again
        paymentViewHolder.textViewDate.setText(activePaymentList.get(paymentViewHolder.getAdapterPosition()).getRequestDate());
        paymentViewHolder.imageView.setImageResource(activePaymentList.get(paymentViewHolder.getAdapterPosition()).getStudent().getProfilePicture());
    }


    @Override
    public int getItemCount() {
        return activePaymentList.size();
    }

    class PaymentViewHolder extends RecyclerView.ViewHolder {

        ImageView imageView;
        TextView textViewName, textViewPrice, textViewDate;

        public PaymentViewHolder(@NonNull View itemView) {
            super(itemView);

            imageView = itemView.findViewById(R.id.studentProfilePicture);
            textViewName = itemView.findViewById(R.id.studentName);
            textViewPrice = itemView.findViewById(R.id.amount);
            textViewDate = itemView.findViewById(R.id.date);
        }
    }
}