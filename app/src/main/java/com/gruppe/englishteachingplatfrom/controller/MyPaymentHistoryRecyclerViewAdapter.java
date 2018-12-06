package com.gruppe.englishteachingplatfrom.controller;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.gruppe.englishteachingplatfrom.R;
import com.gruppe.englishteachingplatfrom.model.PaymentDummyBackend;
import com.gruppe.englishteachingplatfrom.view.PaymentHistoryFragment;

import java.util.List;

public class MyPaymentHistoryRecyclerViewAdapter extends RecyclerView.Adapter<MyPaymentHistoryRecyclerViewAdapter.PaymentViewHolder> {

//    private List<PaymentDummyBackend.TeacherDummy> teacherPaymentHistoryList;
    private PaymentHistoryFragment.OnFragmentInteractionListener mListener;
    PaymentDummyBackend p = PaymentDummyBackend.getInstance();


    public MyPaymentHistoryRecyclerViewAdapter(List<PaymentDummyBackend.TeacherDummy> teacherPaymentRequestList, PaymentHistoryFragment.OnFragmentInteractionListener listener) {
//        this.teacherPaymentHistoryList = teacherPaymentRequestList;
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
//        PaymentDummyBackend.TeacherDummy teacherProfile = teacherPaymentHistoryList.get(i);

        paymentViewHolder.textViewName.setText(p.getTeacherDummiesHistory().get(i).getName());
        paymentViewHolder.textViewPrice.setText(Integer.toString(p.getTeacherDummiesHistory().get(i).getPrice())+" DKK");
        paymentViewHolder.textViewDate.setText(p.getTeacherDummiesHistory().get(i).getDate());
        paymentViewHolder.imageView.setImageResource(R.mipmap.ic_launcher_student_round);
        paymentViewHolder.textViewStatus.setText("Payed");
    }


    @Override
    public int getItemCount() {
        return p.getTeacherDummiesHistory().size();
    }

    class PaymentViewHolder extends RecyclerView.ViewHolder {

        ImageView imageView;
        TextView textViewName, textViewPrice, textViewDate, textViewStatus;
        public PaymentViewHolder(@NonNull View itemView) {
            super(itemView);

            imageView = itemView.findViewById(R.id.imageView4);
            textViewName = itemView.findViewById(R.id.teacherName);
            textViewPrice = itemView.findViewById(R.id.amount);
            textViewDate = itemView.findViewById(R.id.date);
            textViewStatus = itemView.findViewById(R.id.statusTextView);
        }
    }
}
